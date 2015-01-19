package com.nishionline.genealogy.model;

import com.nishionline.genealogy.enums.Sex;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

/**
 * @author Alok
 * @since 31-10-2014
 * @version 0.1.0
 */
@Getter
@Setter
public class Person extends PersistentObject {
    private String firstName;
    private String middleName;
    private String lastName;

    private Sex sex;

    private String prefix;
    private String suffix;

    private Set<Link> links;

    private Set<Attachment> attachments;

    @Override
    public String getName() {
        return this.toString();
    }

    @Override
    public String getDisplayName() {
        return this.toString();
    }

    @Override
    public String toString() {
        return String.format("%s %s %s", this.firstName, this.middleName, this.lastName);
    }
}
