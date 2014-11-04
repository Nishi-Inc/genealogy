package com.nishionline.genealogy.manager;

import com.nishionline.genealogy.dto.UserRequestDTO;
import com.nishionline.genealogy.dto.UserResponseDTO;
import com.nishionline.genealogy.model.MongoObjectId;
import com.nishionline.genealogy.model.User;

/**
 * @author Alok
 * @since 03-11-2014
 */
public interface UserManager {
    public User getUserByUsername(String username);

    public UserResponseDTO find(UserRequestDTO requestDTO);

    public User get(MongoObjectId _id);

    /**
     * Creates a new user<br/>
     * It creates a random password and mails that directly to the user
     * @param user    New user to save
     */
    public void create(User user);
}
