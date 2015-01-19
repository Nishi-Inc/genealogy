package com.nishionline.genealogy.security.providers;

import com.nishionline.genealogy.exceptions.WSError;
import com.nishionline.genealogy.helper.GenealogyConstants;
import com.nishionline.genealogy.helper.GenealogyUtils;
import com.nishionline.genealogy.manager.UserManager;
import com.nishionline.genealogy.model.User;
import com.nishionline.genealogy.security.manager.SecurityManager;
import com.nishionline.genealogy.security.model.Role;
import org.apache.cxf.common.security.SimplePrincipal;
import org.apache.cxf.common.util.Base64Exception;
import org.apache.cxf.common.util.Base64Utility;
import org.apache.cxf.jaxrs.model.ClassResourceInfo;
import org.apache.cxf.message.Message;
import org.apache.cxf.rs.security.oauth2.common.Client;
import org.apache.cxf.rs.security.oauth2.common.UserSubject;
import org.apache.cxf.rs.security.oauth2.filters.OAuthRequestFilter;
import org.apache.cxf.rs.security.oauth2.utils.AuthorizationUtils;
import org.apache.cxf.rs.security.oauth2.utils.OAuthConstants;
import org.apache.cxf.security.SecurityContext;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.security.Principal;
import java.util.ArrayList;

/**
 * @author Alok
 * @since 28-08-2014
 * @version 0.1.0
 */
@Provider
public class CustomRequestHandler extends OAuthRequestFilter {

    @Autowired
    private SecurityManager securityManager;

    @Autowired
    private UserManager userManager;

    @Override
    @SuppressWarnings(GenealogyConstants.UNCHECKED)
    public Response handleRequest(Message m, ClassResourceInfo resourceClass) {
        Client client = null;
        User currentUser = null;
        String[] authParts = AuthorizationUtils.getAuthorizationParts(this.getMessageContext());
        if(authParts[0].equals(OAuthConstants.BASIC_SCHEME)) {
            try {
                byte[] credentialBytes = Base64Utility.decode(authParts[1]);
                String[] credential = new String(credentialBytes).split(GenealogyConstants.COLON);
                currentUser = this.userManager.getUserByUsername(credential[0]);
                if(currentUser == null) {
//                    This may be a clientId
                    client = this.securityManager.findClient(credential[0]).getClient();
                    if(client != null && client.getClientSecret().equals(credential[1])) {
//                        Client is present but not the user
                    } else {
                        this.createFaultResponse(new RuntimeException("clientId/clientSecret or username/password is wrong."));
                    }
                } else {
//                    Match password
                    String computedPassword = GenealogyUtils.encryptPassword(credential[1], currentUser.get_id());
                    if(!currentUser.getPassword().equals(computedPassword)) {
                        this.createFaultResponse(new RuntimeException("Username/password is wrong."));
                    } else {

                    }
                }
            } catch (Base64Exception e) {
                this.createFaultResponse(e);
            }
        } else if(authParts[0].equals(GenealogyConstants.OAUTH)) {

        }

        m.put(SecurityContext.class, this.createSecurityContext(this.createUserSubject(currentUser, client)));

        return null;
    }

    private UserSubject createUserSubject(User currentUser, Client client) {
        UserSubject userSubject = null;
        if(currentUser != null) {
            userSubject = new UserSubject(currentUser.getUsername());
            userSubject.setRoles(new ArrayList<String>());
            for(Role role : currentUser.getRolesMap().values()) {
                userSubject.getRoles().add(role.getName());
            }
        } else if(client != null) {
            userSubject = new UserSubject(client.getClientId());
        }
        return userSubject;
    }

    private SecurityContext createSecurityContext(final UserSubject userSubject) {

        return new SecurityContext() {
            public Principal getUserPrincipal() {
                return userSubject != null ? new SimplePrincipal(userSubject.getLogin()) : null;
            }

            public boolean isUserInRole(String role) {
                return userSubject != null && userSubject.getRoles().contains(role);
            }
        };
    }

    private void createFaultResponse(Throwable throwable) {
        Response response = Response.ok(new WSError(throwable.getMessage(), Response.Status.UNAUTHORIZED, WSError.Code.INVALID_CREDENTIALS))
                .status(Response.Status.UNAUTHORIZED.getStatusCode()).header("isError", true).build();
        throw new WebApplicationException(response);
    }

}
