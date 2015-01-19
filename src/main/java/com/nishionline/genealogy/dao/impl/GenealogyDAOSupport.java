package com.nishionline.genealogy.dao.impl;

import com.nishionline.genealogy.dao.GenericDAO;
import com.nishionline.genealogy.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Alok
 * @since 31-10-2014
 */
public class GenealogyDAOSupport {


    // DAO layer
    @Autowired(required = false)
    protected GenericDAO genericDAO;

    @Autowired(required = false)
    protected UserDAO userDAO;
}
