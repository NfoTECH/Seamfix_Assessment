package com.fortunate.seamfix_assessment.exception;


import com.fortunate.seamfix_assessment.payload.response.BvnResponse;
import com.fortunate.seamfix_assessment.util.CustomResponseCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author fortunate on 28/10/2022
 * @project
 */

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BvnResponse handleConstraintViolationErrors(BindException ex) {
        log.error(ex.getMessage(), ex);
        BvnResponse response = new BvnResponse();

        FieldError fieldError = ex.getFieldErrors().get(0);
        response.setMessage(fieldError.getDefaultMessage());
        response.setCode(CustomResponseCode.BAD_REQUEST);
        return response;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public BvnResponse handleGeneralExceptions(Exception ex) {
        String message = "Something went wrong, Please retry";
        log.error(message, ex);
        BvnResponse response = new BvnResponse();
        response.setMessage(message);
        response.setCode(CustomResponseCode.INTERNAL_SERVER_ERROR);
        return response;
    }
}
