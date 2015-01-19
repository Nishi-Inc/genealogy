package com.nishionline.genealogy.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author Alok
 * @since 31-10-2014
 * @version 0.1.0
 */
@Getter
@Setter
public abstract class PersistentObject implements Serializable {
    private MongoObjectId _id;
    private String name;
    private String displayName;
}
