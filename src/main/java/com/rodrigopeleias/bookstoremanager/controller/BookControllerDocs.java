package com.rodrigopeleias.bookstoremanager.controller;

import com.rodrigopeleias.bookstoremanager.dto.BookDTO;
import com.rodrigopeleias.bookstoremanager.dto.MessageResponseDTO;

public interface BookControllerDocs {

    MessageResponseDTO create(BookDTO bookDTO);
}
