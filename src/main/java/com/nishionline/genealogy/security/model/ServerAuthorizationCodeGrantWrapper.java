package com.nishionline.genealogy.security.model;

import com.nishionline.genealogy.model.PersistentObject;
import lombok.Getter;
import lombok.Setter;
import org.apache.cxf.rs.security.oauth2.grants.code.ServerAuthorizationCodeGrant;

/**
 * @author Alok
 * @since 03-11-2014
 */
@Getter
@Setter
public class ServerAuthorizationCodeGrantWrapper extends PersistentObject {
    private ServerAuthorizationCodeGrant serverAuthorizationCodeGrant;

    public ServerAuthorizationCodeGrantWrapper() {
        super();
    }

    public ServerAuthorizationCodeGrantWrapper(ServerAuthorizationCodeGrant serverAuthorizationCodeGrant) {
        this();
        this.serverAuthorizationCodeGrant = serverAuthorizationCodeGrant;
    }

}
