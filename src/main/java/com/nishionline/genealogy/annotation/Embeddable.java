package com.nishionline.genealogy.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Marker annotation to denote that this particular <code>{@link java.io.Serializable}</code> class is
 * embeddable within other <code>{@link com.nishionline.genealogy.model.PersistentObject}</code><br/>
 * Make sure that every class having this annotation is Serializable.
 * @author Alok
 * @since 15-07-2014
 * @see com.nishionline.genealogy.model.PersistentObject
 * @version 0.1.0
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Embeddable {
}
