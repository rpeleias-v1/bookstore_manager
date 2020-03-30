package com.rodrigopeleias.bookstoremanager.service;

import com.rodrigopeleias.bookstoremanager.dto.request.BookRequest;
import com.rodrigopeleias.bookstoremanager.dto.response.BookResponse;
import com.rodrigopeleias.bookstoremanager.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public BookResponse create(BookRequest bookRequest) {
        return null;
    }
}
