package com.nishionline.genealogy.exceptions;


import com.nishionline.genealogy.helper.GenealogyConstants;

import javax.ws.rs.core.Response;
import java.io.Serializable;
import java.lang.reflect.Field;

/**
 * @author Alok
 * @since 20-08-2014
 */
public class WSError implements Serializable {
    private String errorMessage;
    private Code errorCode;
    private int status;

    public WSError(String message, Response.Status status, WSError.Code code) {
        this.errorMessage = message;
        this.status = status.getStatusCode();
        this.errorCode = code;
    }

    public static enum Code {
        INVALID_REQUEST, INVALID_CLIENT, INVALID_CREDENTIALS;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(GenealogyConstants.LEFT_BRACE);
        boolean first = true;
        for(Field field : WSError.class.getFields()) {
            field.setAccessible(true);
            if(!first) {
                stringBuilder.append(GenealogyConstants.COMMA);
            }
            try {
                stringBuilder.append(GenealogyConstants.DOUBLE_QUOTES).append(field.getName()).append(GenealogyConstants.DOUBLE_QUOTES)
                        .append(GenealogyConstants.COLON)
                        .append(GenealogyConstants.DOUBLE_QUOTES).append(field.get(this)).append(GenealogyConstants.DOUBLE_QUOTES);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            first = false;
        }

        stringBuilder.append(GenealogyConstants.RIGHT_BRACE);
        return stringBuilder.toString();
    }

}
