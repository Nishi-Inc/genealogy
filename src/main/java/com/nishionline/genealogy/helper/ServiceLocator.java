package com.nishionline.genealogy.helper;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.Map;

/**
 * @author nishi
 * @since 4/22/14
 */
public class ServiceLocator implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ServiceLocator.applicationContext = applicationContext;
    }

    public ApplicationContext getApplicationContext() {
        return ServiceLocator.applicationContext;
    }

    @SuppressWarnings(GenealogyConstants.UNCHECKED)
    public <T extends Object> T getBean(String beanName) {
        return (T) this.getApplicationContext().getBean(beanName);
    }

    public static <T> T getBean(Class<T> clazz) {
        Map<String, T> beans = ServiceLocator.applicationContext.getBeansOfType(clazz);
        if(beans.size() == 1) {
            return beans.get(clazz.getSimpleName());
        } else {
            throw new IllegalArgumentException("More than one beans or no beans of " + clazz.getSimpleName() + " are available.");
        }
    }

}
