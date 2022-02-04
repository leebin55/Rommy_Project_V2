package com.roomy.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Date;

@AllArgsConstructor
@Getter
public class ExceptionMessageDTO {

    private HttpStatus statusCode;
    private long time;
    private String message;
}
