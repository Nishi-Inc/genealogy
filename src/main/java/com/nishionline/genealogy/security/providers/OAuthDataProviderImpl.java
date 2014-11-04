package com.nishionline.genealogy.security.providers;

import com.nishionline.genealogy.helper.ApplicationConfig;
import com.nishionline.genealogy.security.manager.SecurityManager;
import com.nishionline.genealogy.security.model.ServerAuthorizationCodeGrantWrapper;
import org.apache.cxf.rs.security.oauth2.common.*;
import org.apache.cxf.rs.security.oauth2.grants.code.AuthorizationCodeDataProvider;
import org.apache.cxf.rs.security.oauth2.grants.code.AuthorizationCodeRegistration;
import org.apache.cxf.rs.security.oauth2.grants.code.ServerAuthorizationCodeGrant;
import org.apache.cxf.rs.security.oauth2.provider.OAuthServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;

import javax.ws.rs.ext.Provider;
import java.util.List;

/**
 * @author Alok
 * @since 14-07-2014
 */
@Provider
@DependsOn({"SecurityManager", "ApplicationConfig"})
public class OAuthDataProviderImpl implements AuthorizationCodeDataProvider {

    @Autowired
    private ApplicationConfig applicationConfig;

    @Autowired
    private SecurityManager securityManager;

    @Override
    public ServerAuthorizationCodeGrant createCodeGrant(AuthorizationCodeRegistration authorizationCodeRegistration) throws OAuthServiceException {
        ServerAuthorizationCodeGrant serverAuthorizationCodeGrant = new ServerAuthorizationCodeGrant(authorizationCodeRegistration.getClient(),
                Long.parseLong(this.applicationConfig.getLifetimeOfCodeGrant()));
        serverAuthorizationCodeGrant.setRedirectUri(authorizationCodeRegistration.getRedirectUri());
        serverAuthorizationCodeGrant.setApprovedScopes(authorizationCodeRegistration.getApprovedScope());
        // persist this codeGrant
        this.securityManager.save(new ServerAuthorizationCodeGrantWrapper(serverAuthorizationCodeGrant));
        return serverAuthorizationCodeGrant;
    }

    @Override
    public ServerAuthorizationCodeGrant removeCodeGrant(String code) throws OAuthServiceException {
        return this.securityManager.deleteServerAuthorizationCodeGrant(code).getServerAuthorizationCodeGrant();
    }


    @Override
    public Client getClient(String clientId) throws OAuthServiceException {
        return this.securityManager.findClient(clientId).getClient();
    }

    @Override
    public ServerAccessToken createAccessToken(AccessTokenRegistration accessToken) throws OAuthServiceException {
        return null;
    }

    @Override
    public ServerAccessToken getAccessToken(String accessToken) throws OAuthServiceException {
        return null;
    }

    @Override
    public ServerAccessToken getPreauthorizedToken(Client client, List<String> requestedScopes, UserSubject subject, String grantType) throws OAuthServiceException {
        return null;
    }

    @Override
    public ServerAccessToken refreshAccessToken(Client client, String refreshToken, List<String> requestedScopes) throws OAuthServiceException {
        return null;
    }

    @Override
    public void removeAccessToken(ServerAccessToken accessToken) throws OAuthServiceException {

    }

//    @Override
//    This is a method of CXF v3.0.1
//    public void revokeToken(Client client, String token, String tokenTypeHint) throws OAuthServiceException {
//
//    }

    @Override
    public List<OAuthPermission> convertScopeToPermissions(Client client, List<String> requestedScope) {
        return null;
    }
}
