package com.nishionline.genealogy.init;

import com.nishionline.genealogy.helper.GsonUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * @author Alok
 * @since 03-11-2014
 */
@Component("ApplicationInitializer")
public class ApplicationInitializer implements InitializingBean {

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("Initializing the application...");
        try {
            GsonUtils.init();
            System.out.println("Application initialized.");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Application could not start properly because of the previous errors.");
        }
    }
}
