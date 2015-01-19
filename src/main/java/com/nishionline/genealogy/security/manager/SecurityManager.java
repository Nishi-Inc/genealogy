package com.nishionline.genealogy.security.manager;

import com.nishionline.genealogy.dto.ClientRequestDTO;
import com.nishionline.genealogy.dto.ClientResponseDTO;
import com.nishionline.genealogy.model.MongoObjectId;
import com.nishionline.genealogy.security.model.ClientWrapper;
import com.nishionline.genealogy.security.model.ServerAuthorizationCodeGrantWrapper;
import org.apache.cxf.rs.security.oauth2.common.Client;

/**
 * @author Alok
 * @since 15-07-2014
 * @version 0.1.0
 */
public interface SecurityManager {

    public void save(ServerAuthorizationCodeGrantWrapper authorizationCodeGrantWrapper);

    public ServerAuthorizationCodeGrantWrapper findServerAuthorizationCodeGrant(String code);

    public ClientWrapper findClient(String clientId);

    public ServerAuthorizationCodeGrantWrapper deleteServerAuthorizationCodeGrant(String code);

    /**
     * Saves client and returns db id
     * @param client    The {@link org.apache.cxf.rs.security.oauth2.common.Client} object
     * @return id of the saved object
     */
    public void registerClient(Client client);

    public ClientResponseDTO findClient(ClientRequestDTO clientRequestDTO);

    /**
     * Delete the clientWrapper object from the db
     * @param _id    _id of the object
     */
    public void removeClientWrapper(MongoObjectId _id);
}
