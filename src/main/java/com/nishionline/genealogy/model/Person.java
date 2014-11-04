package com.nishionline.genealogy.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

/**
 * @author Alok
 * @since 31-10-2014
 */
@Getter
@Setter
public class Person extends PersistentObject {
    private String firstName;
    private String middleName;
    private String lastName;

    private String prefix;
    private String suffix;

    private Set<Attachment> attachments;

    @Override
    public String toString() {
        return String.format("%s %s %s", this.firstName, this.middleName, this.lastName);
    }
}
