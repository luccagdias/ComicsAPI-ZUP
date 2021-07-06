package com.zup.desafio.comicsapi.controller.exceptions;

import com.zup.desafio.comicsapi.controller.exceptions.util.FieldMessage;

import java.util.ArrayList;
import java.util.List;

public class FieldAlreadyRegisteredException extends Exception{

    List<FieldMessage> fieldMessages;

    public FieldAlreadyRegisteredException(List<FieldMessage> fieldMessages) {
        this.fieldMessages = fieldMessages;
    }

    public List<FieldMessage> getFieldMessages() {
        return fieldMessages;
    }
}
