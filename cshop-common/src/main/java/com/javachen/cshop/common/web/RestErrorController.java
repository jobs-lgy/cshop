package com.javachen.cshop.common.web;

import com.google.common.collect.ImmutableMap;
import com.javachen.cshop.common.utils.logging.RequestIdMdcFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * 重新spring 的 /error 逻辑
 */
@Slf4j
@RestController
public class RestErrorController implements ErrorController {
    private static final String PATH = "/error";

    @Autowired
    private ErrorAttributes errorAttributes;

    @RequestMapping(value = PATH)
    public ResponseEntity<?> handleError(WebRequest webRequest) {
        Map<String, Object> errorAttributes = getErrorAttributes(webRequest);
        String error = (String) errorAttributes.get("error");
        int status = (int) errorAttributes.get("status");
        String message = (String) errorAttributes.get("message");
        String path = (String) errorAttributes.get("path");
        String trace = (String) errorAttributes.get("trace");

        log.error("Error[{}] occurred while access uri[{}]:{}",error, path,message);

        if(errorAttributes.containsKey("errors")){
            List<ObjectError> errors = (List<ObjectError>) errorAttributes.get("errors");
            List<ImmutableMap> details = errors.stream()
                    .map(err -> ImmutableMap.of(((FieldError) err).getField(), err.getDefaultMessage()))
                    .collect(Collectors.toList());
            errorAttributes.put("errors",details);
        }
        errorAttributes.remove("trace");
        errorAttributes.put("requestId", webRequest.getAttribute(RequestIdMdcFilter.REQUEST_ID, RequestAttributes.SCOPE_REQUEST));
        errorAttributes.put("timestamp",System.currentTimeMillis());
        return ResponseEntity.status(status).body(errorAttributes);
    }

    @Override
    public String getErrorPath() {
        return PATH;
    }

    private Map<String, Object> getErrorAttributes(WebRequest webRequest) {
        return errorAttributes.getErrorAttributes(webRequest, true);
    }
}