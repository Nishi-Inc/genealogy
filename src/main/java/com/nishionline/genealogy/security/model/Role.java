package com.nishionline.genealogy.security.model;

import com.nishionline.genealogy.annotation.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;

/**
 * @author Alok
 * @since 26-07-2014
 * @version 0.1.0
 */
@Getter
@Setter
@Embeddable
public class Role implements Serializable {
    private String name;
    private String description;
    private Set<Permission> permissions;

    public Role(String name) {
        this();
        this.name = name;
    }

    public Role() {}

    @Override
    public boolean equals(Object o) {
        return o instanceof Role && ((Role) o).getName().equals(this.getName());
    }

    @Override
    public int hashCode() {
        return this.getName().hashCode();
    }

}
