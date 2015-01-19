package com.nishionline.genealogy.dao;

import com.nishionline.genealogy.model.MongoObjectId;
import com.nishionline.genealogy.model.PersistentObject;

/**
 * @author Alok
 * @since 19-01-2015
 * @version 0.1.0
 */
public interface GenericDAO {

    /**
     *
     * @param persistentClass    The class of the object
     * @param _id                _id of the object
     * @param <P>                P extends PersistentObject
     * @return The found object, returns <code>null</code> if found nothing
     */
    public <P extends PersistentObject> P findById(Class<P> persistentClass, MongoObjectId _id);

    /**
     *
     * @param persistentClass    The class of the object
     * @param _id                _id of the object
     * @param <P>                P extends PersistentObject
     * @return <code>true</code> if deleted successfully.
     */
    public <P extends PersistentObject> boolean deleteById(Class<P> persistentClass, MongoObjectId _id);

    /**
     *
     * @param persistentClass    The class of the object
     * @param _id                _id of the object
     * @param attribute          attribute of the document to change value of
     * @param newValue           new value of the attribute
     * @param <P>                P extends PersistentObject
     * @return The updated object, returns <code>null</code> if found nothing
     */
    public <P extends PersistentObject> P updateById(Class<P> persistentClass, MongoObjectId _id, String attribute, Object newValue);

}
