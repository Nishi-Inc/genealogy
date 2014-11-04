package com.nishionline.genealogy.dto;

import com.nishionline.genealogy.model.PersistentObject;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author Alok
 * @since 03-11-2014
 */
@Getter
@Setter
public abstract class ResponseDTO<T extends PersistentObject> {
    private List<T> searchResults;
}
