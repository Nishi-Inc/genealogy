package com.nishionline.genealogy.security.manager.impl;

import com.nishionline.genealogy.dto.ClientRequestDTO;
import com.nishionline.genealogy.dto.ClientResponseDTO;
import com.nishionline.genealogy.model.MongoObjectId;
import com.nishionline.genealogy.security.model.ClientWrapper;
import com.nishionline.genealogy.security.model.ServerAuthorizationCodeGrantWrapper;
import org.apache.cxf.rs.security.oauth2.common.Client;
import org.springframework.stereotype.Service;

/**
 * @author Alok
 * @since 03-11-2014
 */
@Service("SecurityManager")
public class SecurityManagerImpl implements com.nishionline.genealogy.security.manager.SecurityManager {
    @Override
    public void save(ServerAuthorizationCodeGrantWrapper authorizationCodeGrantWrapper) {

    }

    @Override
    public ServerAuthorizationCodeGrantWrapper findServerAuthorizationCodeGrant(String code) {
        return null;
    }

    @Override
    public ClientWrapper findClient(String clientId) {
        return null;
    }

    @Override
    public ServerAuthorizationCodeGrantWrapper deleteServerAuthorizationCodeGrant(String code) {
        return null;
    }

    @Override
    public void registerClient(Client client) {

    }

    @Override
    public ClientResponseDTO findClient(ClientRequestDTO clientRequestDTO) {
        return null;
    }

    @Override
    public void removeClientWrapper(MongoObjectId _id) {

    }
}
