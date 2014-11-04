package com.nishionline.genealogy.manager.impl;

import com.nishionline.genealogy.dto.UserRequestDTO;
import com.nishionline.genealogy.dto.UserResponseDTO;
import com.nishionline.genealogy.manager.UserManager;
import com.nishionline.genealogy.model.MongoObjectId;
import com.nishionline.genealogy.model.User;
import org.springframework.stereotype.Service;

/**
 * @author Alok
 * @since 03-11-2014
 */
@Service("UserManager")
public class UserManagerImpl implements UserManager {
    @Override
    public User getUserByUsername(String username) {
        return null;
    }

    @Override
    public UserResponseDTO find(UserRequestDTO requestDTO) {
        return null;
    }

    @Override
    public User get(MongoObjectId _id) {
        return null;
    }

    @Override
    public void create(User user) {

    }
}
