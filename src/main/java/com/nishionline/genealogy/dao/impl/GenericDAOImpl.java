package com.nishionline.genealogy.dao.impl;

import com.nishionline.genealogy.dao.GenericDAO;
import com.nishionline.genealogy.model.MongoObjectId;
import com.nishionline.genealogy.model.PersistentObject;
import org.springframework.stereotype.Repository;

/**
 * @author Alok
 * @since 19-01-2015
 */
@Repository("GenericDAO")
public class GenericDAOImpl extends GenealogyDAOSupport implements GenericDAO {

    @Override
    public <P extends PersistentObject> P findById(Class<P> persistentClass, MongoObjectId _id) {
        return null;
    }

    @Override
    public <P extends PersistentObject> boolean deleteById(Class<P> persistentClass, MongoObjectId _id) {
        return false;
    }

    @Override
    public <P extends PersistentObject> P updateById(Class<P> persistentClass, MongoObjectId _id,
                                                     String attribute, Object newValue) {
        return null;
    }
}
