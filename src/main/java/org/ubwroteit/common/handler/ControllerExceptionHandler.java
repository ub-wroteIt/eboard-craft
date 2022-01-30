package org.ubwroteit.common.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.ubwroteit.common.exception.ResourceNotFoundException;
import org.ubwroteit.common.model.ErrorMessage;

import java.util.Date;

@ControllerAdvice
@ResponseBody
public class ControllerExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorMessage resourceNotFoundException(ResourceNotFoundException resourceNotFoundException, WebRequest request){
        return new ErrorMessage(new Date(), resourceNotFoundException.getMessage(),request.getDescription(false));
    }
}
