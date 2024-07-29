package com.user.exception;

import com.user.dto.ErrorDetails;
import com.user.service.CustomNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class ExceptionHandling {


    @ExceptionHandler(ResourceNotFoundException.class)
    // return response to postman
    public ResponseEntity<ErrorDetails> ResourceNotFoundException(
            // takes object address with a message
            ResourceNotFoundException ex,
            WebRequest webRequest){
        ErrorDetails error=new ErrorDetails(
                ex.getMessage(),
                new Date(),
                webRequest.getDescription(false));
        return new ResponseEntity<>(error,HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(Exception.class)
    // return response to postman
    public ResponseEntity<ErrorDetails> globalException(
            // takes object address with a message
            Exception ex,
            WebRequest webRequest){
        ErrorDetails err=new ErrorDetails(
                ex.getMessage(),
                new Date(),
                webRequest.getDescription(false)

        );
        return new ResponseEntity<>(err,HttpStatus.INTERNAL_SERVER_ERROR);
    }




}
