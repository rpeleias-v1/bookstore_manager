package com.rodrigopeleias.bookstoremanager.controller;

import com.rodrigopeleias.bookstoremanager.dto.request.BookDTO;
import com.rodrigopeleias.bookstoremanager.dto.response.MessageResponseDTO;

public interface BookControllerDocs {

    MessageResponseDTO create(BookDTO bookDTO);
}
