package com.rodrigopeleias.bookstoremanager.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BookAlreadyExistsException extends Exception {

    public BookAlreadyExistsException(Long id, String name) {
        super(String.format("Book with ID %s and name %s is already registered", id, name));
    }
}
