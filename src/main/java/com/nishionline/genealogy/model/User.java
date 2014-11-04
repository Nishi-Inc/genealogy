package com.nishionline.genealogy.model;

import com.nishionline.genealogy.security.model.Role;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

/**
 * @author Alok
 * @since 03-11-2014
 */
@Getter
@Setter
public class User extends PersistentObject {

    private String username;
    private String password;

    /**
     * Every user needs to have a person profile as well
     */
    private MongoObjectId personId;

    private Map<Long, Role> rolesMap;

}
