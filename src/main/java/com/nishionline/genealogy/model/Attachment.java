package com.nishionline.genealogy.model;

import lombok.Getter;
import lombok.Setter;

import java.io.File;

/**
 * @author Alok
 * @version 0.1.0
 * @since 31 October, 2014
 */
@Getter
@Setter
public class Attachment extends PersistentObject {
    private File file;
    private String displayName;

}
