package com.roomy.aspect;

import com.roomy.dto.ExceptionMessageDTO;
import com.roomy.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ExceptionMessageDTO> notFoundException(UserNotFoundException e
            , HttpServletRequest request) {

        ExceptionMessageDTO exceptionMessage = new ExceptionMessageDTO(HttpStatus.BAD_REQUEST
                , System.currentTimeMillis(), e.getMessage());
        return ResponseEntity.badRequest().body(exceptionMessage);
    }
}
