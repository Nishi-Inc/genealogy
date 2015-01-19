package com.nishionline.genealogy.annotation;

import com.nishionline.genealogy.helper.GenealogyConstants;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Alok
 * @since 03-11-2014
 * @version 0.1.0
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Optional {
    public String value() default GenealogyConstants.BLANK;
}
