package com.rodrigopeleias.bookstoremanager.controller;

import com.rodrigopeleias.bookstoremanager.dto.request.BookRequest;
import com.rodrigopeleias.bookstoremanager.dto.response.BookResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

public interface BookControllerDocs {

    ResponseEntity<BookResponse> create(@Valid @RequestBody BookRequest bookRequest);
}
