package com.zup.desafio.comicsapi.controller.exceptions;

import com.zup.desafio.comicsapi.controller.exceptions.util.FieldMessage;
import com.zup.desafio.comicsapi.controller.exceptions.util.StandardError;
import com.zup.desafio.comicsapi.controller.exceptions.util.ValidationError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> validation(MethodArgumentNotValidException e, HttpServletRequest request) {
        ValidationError err = new ValidationError(HttpStatus.BAD_REQUEST.value(), "Erro de validação no corpo da requisição", new Date());

        for(FieldError fieldError : e.getBindingResult().getFieldErrors()) {
            err.addError(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

    @ExceptionHandler(FieldAlreadyRegisteredException.class)
    public ResponseEntity<StandardError> fieldAlreadyRegistered(FieldAlreadyRegisteredException e, HttpServletRequest request) {
            ValidationError err = new ValidationError(HttpStatus.BAD_REQUEST.value(), "Erro de validação no corpo da requisição", new Date());

            for(FieldMessage fieldMessage : e.getFieldMessages()) {
                err.addError(fieldMessage.getFieldName(), fieldMessage.getMessage());
            }

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }
}
