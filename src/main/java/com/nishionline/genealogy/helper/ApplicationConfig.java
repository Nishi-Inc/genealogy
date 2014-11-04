package com.nishionline.genealogy.helper;

import com.nishionline.genealogy.annotation.Ignore;
import com.nishionline.genealogy.annotation.Optional;
import com.sun.javafx.fxml.PropertyNotFoundException;
import lombok.Getter;
import org.apache.cxf.common.util.StringUtils;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * @author Alok
 * @since 04-07-2014
 */
@Getter
@Component("ApplicationConfig")
public final class ApplicationConfig {
    public static int DEFAULT_SEARCH_RESULTS_LIMIT      = 20;
    public static int DEFAULT_SEARCH_RESULTS_PER_PAGE   = 20;

    // DATABASE
    private String databaseName;
    private String databaseHost;
    private String databasePort;
    private String datasetId;

    // OAUTH
    private String lifetimeOfCodeGrant;
    private String lifetimeOfAccessToken;

    // MAIL
    private String smtpHost;
    private String smtpPort;
    private String mailUsername;
    private String mailPassword;
    private String mailFrom;
    @Optional("Team, Innosols Infocom")
    private String mailFromName;
    @Optional("[Vendor Bridge]")
    private String mailSubjectPrefix;

    // ADAPTERS
    private String openExchangeRateAppId;

    @Optional
    @Ignore
    private boolean loadConfig = true;

    public void init() throws IllegalAccessException {
        if (this.loadConfig) {
            if(System.getProperty("loadConfig") != null) {
                this.loadConfig = Boolean.parseBoolean(System.getProperty("loadConfig"));
            }

            if (this.loadConfig) {
                for (Field field : ApplicationConfig.class.getDeclaredFields()) {
                    field.setAccessible(true);

                    if(field.getAnnotation(Ignore.class) != null || Modifier.isStatic(field.getModifiers())) {
                        continue;
                    }

                    Optional annotation = field.getAnnotation(Optional.class);
                    if (!StringUtils.isEmpty(System.getProperty(field.getName()))) {
                        field.set(this, System.getProperty(field.getName()));
                    } else if (annotation != null) {
                        if (!StringUtils.isEmpty(annotation.value())) {
                            field.set(this, annotation.value());
                        }
                    } else {
                        throw new PropertyNotFoundException("Property " + field.getName() + " not found in appengine-web.xml");
                    }
                }
            }
        }
    }

}
