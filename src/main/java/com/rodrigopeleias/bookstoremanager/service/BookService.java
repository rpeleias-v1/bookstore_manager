package com.rodrigopeleias.bookstoremanager.service;

import com.rodrigopeleias.bookstoremanager.dto.request.BookRequest;
import com.rodrigopeleias.bookstoremanager.dto.response.BookResponse;
import com.rodrigopeleias.bookstoremanager.entities.Author;
import com.rodrigopeleias.bookstoremanager.entities.Book;
import com.rodrigopeleias.bookstoremanager.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class BookService {

    private final BookRepository bookRepository;

    private final AuthorService authorService;

    public BookResponse create(BookRequest bookRequest) {
        Author author = authorService.findOrCreate(bookRequest.getAuthorName());

        Book book = mapRequestToEntity(bookRequest, author);

        Book savedBook = bookRepository.save(book);

        return mapEntityToResponse(savedBook);
    }

    private Book mapRequestToEntity(BookRequest bookRequest, Author author) {
        return Book.builder()
                .name(bookRequest.getName())
                .pages(bookRequest.getPages())
                .chapters(bookRequest.getChapters())
                .isbn(bookRequest.getIsbn())
                .author(author).build();
    }

    private BookResponse mapEntityToResponse(Book savedBook) {
        return BookResponse.builder()
                .id(savedBook.getId())
                .name(savedBook.getName())
                .pages(savedBook.getPages())
                .chapters(savedBook.getChapters())
                .isbn(savedBook.getIsbn())
                .authorName(savedBook.getAuthor().getName()).build();
    }
}
