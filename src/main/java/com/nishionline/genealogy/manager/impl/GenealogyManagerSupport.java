package com.nishionline.genealogy.manager.impl;

import com.nishionline.genealogy.dao.GenericDAO;
import com.nishionline.genealogy.dao.UserDAO;
import com.nishionline.genealogy.manager.UserManager;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Alok
 * @since 31-10-2014
 * @version 0.1.0
 */
public class GenealogyManagerSupport {

    // Manager layer
    @Autowired(required = false)
    protected UserManager userManager;

    // DAO layer
    @Autowired
    protected GenericDAO genericDAO;

    @Autowired
    protected UserDAO userDAO;
}
