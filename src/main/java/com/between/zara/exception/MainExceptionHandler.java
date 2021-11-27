package com.between.zara.exception;

import com.between.zara.model.ErrorResponse;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Optional;

@RestControllerAdvice
public class MainExceptionHandler {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse handleArgumentException(MethodArgumentNotValidException ex){
        return new ErrorResponse("Invalid request parameters", getInvalidRequestErrorMessage(ex));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ErrorResponse handleArgumentException(HttpMessageNotReadableException ex){
        return new ErrorResponse("Invalid date pattern", "Please provide a date with the following format yyyy-MM-dd-HH.mm.ss");
    }

    private String getInvalidRequestErrorMessage(MethodArgumentNotValidException ex) {
        return Optional.of(ex.getBindingResult())
                .flatMap(bindingResult -> Optional.ofNullable(bindingResult.getFieldError())
                        .map(DefaultMessageSourceResolvable::getDefaultMessage))
                .orElseGet(ex::getLocalizedMessage);
    }
}
