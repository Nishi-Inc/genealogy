package com.nishionline.genealogy.security.providers;

import com.nishionline.genealogy.manager.UserManager;
import com.nishionline.genealogy.model.User;
import com.nishionline.genealogy.security.model.Role;
import org.apache.cxf.jaxrs.ext.MessageContext;
import org.apache.cxf.rs.security.oauth2.common.UserSubject;
import org.apache.cxf.rs.security.oauth2.provider.OAuthServiceException;
import org.apache.cxf.rs.security.oauth2.provider.SubjectCreator;
import org.apache.cxf.security.SecurityContext;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.ext.Provider;
import java.util.ArrayList;

/**
 * @author Alok
 * @since 26-07-2014
 */
@Provider
public class CustomSubjectCreator implements SubjectCreator {

    @Autowired
    private UserManager userManager;

    @Override
    public UserSubject createUserSubject(MessageContext mc) throws OAuthServiceException {
        String login = ((SecurityContext) mc.get(SecurityContext.class)).getUserPrincipal().getName();
        User user = this.userManager.getUserByUsername(login);

        if(user != null) {
            UserSubject userSubject = new UserSubject(login);
            if(userSubject.getRoles() == null) {
                userSubject.setRoles(new ArrayList<String>());
            }
            for(Role role : user.getRolesMap().values()) {
                userSubject.getRoles().add(role.getName());
            }
        }
        return null;
    }
}
