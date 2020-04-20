package com.rodrigopeleias.bookstoremanager.controller;

import com.rodrigopeleias.bookstoremanager.dto.BookDTO;
import com.rodrigopeleias.bookstoremanager.dto.MessageResponseDTO;
import com.rodrigopeleias.bookstoremanager.exception.BookAlreadyExistsException;
import com.rodrigopeleias.bookstoremanager.exception.BookNotFoundException;
import com.rodrigopeleias.bookstoremanager.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/books")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class BookController implements BookControllerDocs {

    private final BookService bookService;

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponseDTO create(@Valid @RequestBody BookDTO bookDTO) throws BookAlreadyExistsException {
        return bookService.create(bookDTO);
    }

    @Override
    @GetMapping("/{bookId}")
    @ResponseStatus(HttpStatus.OK)
    public BookDTO findById(@PathVariable Long bookId) throws BookNotFoundException {
        return bookService.findById(bookId);
    }
}
