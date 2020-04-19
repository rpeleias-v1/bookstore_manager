package com.rodrigopeleias.bookstoremanager.controller;

import com.rodrigopeleias.bookstoremanager.dto.BookDTO;
import com.rodrigopeleias.bookstoremanager.dto.MessageResponseDTO;
import com.rodrigopeleias.bookstoremanager.exception.BookAlreadyExistsException;

public interface BookControllerDocs {

    MessageResponseDTO create(BookDTO bookDTO) throws BookAlreadyExistsException;
}
