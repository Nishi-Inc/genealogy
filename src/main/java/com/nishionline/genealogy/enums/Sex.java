package com.nishionline.genealogy.enums;

import com.nishionline.genealogy.annotation.Embeddable;
import lombok.Getter;

/**
 * @author Alok
 * @since 19-01-2015
 */
@Embeddable
public enum Sex {
    MALE("blue", "Male"),
    FEMALE("pink", "Female"),
    OTHER("purple", "Other"),
    UNKNOWN("gray", "Unknown");

    @Getter
    private String color;
    @Getter
    private String displayName;

    Sex(String color, String displayName) {
        this.color = color;
        this.displayName = displayName;
    }
}
