package com.nishionline.genealogy.model;

import com.nishionline.genealogy.annotation.Embeddable;
import com.nishionline.genealogy.helper.GenealogyConstants;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author Alok
 * @since 19-01-2015
 * @version 0.1.0
 */
@Getter
@Setter
@Embeddable
public class Link implements Serializable {
    private String name;
    private Type type;
    private MongoObjectId pointsTo;

    public static enum Type {
        BIOLOGICAL(GenealogyConstants.BLANK),
        LEGAL("Legal"),
        ADOPTED("Adopted");

        private String displayName;

        Type(String displayName) {
            this.displayName = displayName;
        }
    }
}
