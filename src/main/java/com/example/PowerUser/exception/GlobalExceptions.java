package com.example.PowerUser.exception;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptions {


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleInvalidInputException(MethodArgumentNotValidException exception){
        Map<String, String> errorDetails = new HashMap<>();
        //Lambda Alternative
//        List<FieldError> fieldError = exception.getBindingResult().getFieldErrors();
//        for(FieldError error: fieldError)
//                errorDetails.put(error.getField(), error.getDefaultMessage());

        //Lambda Version
        exception.getBindingResult().getFieldErrors().forEach(
                error -> errorDetails.put(error.getField(), error.getDefaultMessage())
        );
        return errorDetails;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleExceptions( Exception exception ){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("There has been an internal server error");
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler( UserException.class )
    public ResponseEntity<String> handleResourceNotFound( UserException resourceNotFoundException ){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resourceNotFoundException.getMessage());
    }

}
