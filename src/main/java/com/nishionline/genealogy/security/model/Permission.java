package com.nishionline.genealogy.security.model;

import com.nishionline.genealogy.annotation.Embeddable;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.Set;

/**
 * @author Alok
 * @since 26-07-2014
 */
@Getter
@Setter
@Embeddable
public class Permission implements Serializable {
    private String name;
    private String description;
    private Set<String> allowedUrls;

    public boolean isUrlAllowed(String url) {
        return StringUtils.isNotBlank(url) && this.getAllowedUrls().contains(url);
    }

}
