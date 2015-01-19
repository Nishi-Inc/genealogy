package com.nishionline.genealogy.security.model;

import com.nishionline.genealogy.model.PersistentObject;
import lombok.Getter;
import lombok.Setter;
import org.apache.cxf.rs.security.oauth2.common.Client;

/**
 * @author Alok
 * @since 03-11-2014
 * @version 0.1.0
 */
@Getter
@Setter
public class ClientWrapper extends PersistentObject {
    private Client client;

}
