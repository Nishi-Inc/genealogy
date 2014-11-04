package com.nishionline.genealogy.service.impl;

import com.nishionline.genealogy.dto.UserResponseDTO;
import com.nishionline.genealogy.model.User;
import com.nishionline.genealogy.service.UserService;

import javax.ws.rs.core.MultivaluedMap;

/**
 * @author Alok
 * @since 03-11-2014
 */
public class UserServiceImpl implements UserService {
    @Override
    public User find(String id) {
        return null;
    }

    @Override
    public UserResponseDTO find(MultivaluedMap<String, String> params) {
        return null;
    }

    @Override
    public void create(MultivaluedMap<String, String> params) {

    }

    @Override
    public void update(String id, MultivaluedMap<String, String> params) {

    }

    @Override
    public void delete(String id) {

    }
}
