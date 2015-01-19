package com.nishionline.genealogy.model;

import org.bson.types.ObjectId;

/**
 * @author Alok
 * @since 03-11-2014
 * @version 0.1.0
 */
public class MongoObjectId extends ObjectId {
    private String $oid;

    public MongoObjectId() {
        super();
    }

    public MongoObjectId(String $oid) {
        super($oid);
        this.$oid = $oid;
    }

    public ObjectId getObjectId() {
        return new ObjectId(this.$oid);
    }

    @Override
    public String toString()
    {
        return $oid;
    }

    public static MongoObjectId getObjectId(String id) {
        return new MongoObjectId(id);
    }

    @Override
    public int hashCode() {
        return this.getObjectId().hashCode();
    }

}
