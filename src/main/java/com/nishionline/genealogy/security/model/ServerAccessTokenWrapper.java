package com.nishionline.genealogy.security.model;

import com.nishionline.genealogy.model.PersistentObject;
import lombok.Getter;
import lombok.Setter;
import org.apache.cxf.rs.security.oauth2.common.ServerAccessToken;

/**
 * @author Alok
 * @since 14-07-2014
 * @version 0.1.0
 */
@Getter
@Setter
// Annotation removed to prevent it from registering in OfyFactory
//@Entity(name = "ServerAccessTokenWrapper")
public class ServerAccessTokenWrapper extends PersistentObject {

    private ServerAccessToken serverAccessToken;

    public ServerAccessTokenWrapper() {
        super();
    }

    public ServerAccessTokenWrapper(ServerAccessToken serverAccessToken) {
        this();
        this.serverAccessToken = serverAccessToken;
    }
}
