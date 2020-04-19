package com.rodrigopeleias.bookstoremanager.controller;

import com.rodrigopeleias.bookstoremanager.dto.BookDTO;
import com.rodrigopeleias.bookstoremanager.dto.MessageResponseDTO;
import com.rodrigopeleias.bookstoremanager.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController("/api/v1/books")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class BookController implements BookControllerDocs {

    private final BookService bookService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponseDTO create(@Valid @RequestBody BookDTO bookDTO) {
        return bookService.create(bookDTO);
    }
}
