package com.gymtrack.api.exception.response;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

/**
 * Modifying default Spring Boot response when exceptions are thrown
 */
@Component
public class CustomErrorAttributes extends DefaultErrorAttributes {
    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, ErrorAttributeOptions errorAttributeOptions) {
        final Map<String, Object> errorAttributes = super.getErrorAttributes(webRequest, errorAttributeOptions);

        errorAttributes.remove("timestamp");
        errorAttributes.remove("path");
        errorAttributes.remove("exception");

        return errorAttributes;
    }
}
