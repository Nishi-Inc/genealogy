package com.nishionline.genealogy.annotation;

import com.nishionline.genealogy.helper.GenealogyConstants;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Alok
 * @since 07-09-2014
 * @version 0.1.0
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Collection {
    public String value() default GenealogyConstants.BLANK;

}
