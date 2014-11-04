package com.nishionline.genealogy.security.model;

import com.nishionline.genealogy.annotation.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;

/**
 * @author aloks
 * @since 8/12/14
 */
@Getter
@Setter
@Embeddable
public class Scope implements Serializable {
    private String name;
    private String description;
    private Set<Permission> permissions;

}
