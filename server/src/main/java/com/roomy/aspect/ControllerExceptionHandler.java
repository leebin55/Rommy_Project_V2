package com.roomy.aspect;

import com.roomy.dto.ExceptionMessageDTO;
import com.roomy.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ExceptionMessageDTO notFoundException(ResourceNotFoundException e
            , HttpServletRequest request){

        return new ExceptionMessageDTO(HttpStatus.NOT_FOUND, System.currentTimeMillis(), e.getMessage());

    }

}
