package com.rodrigopeleias.bookstoremanager.service;

import com.rodrigopeleias.bookstoremanager.dto.request.BookRequest;
import com.rodrigopeleias.bookstoremanager.dto.response.BookResponse;
import com.rodrigopeleias.bookstoremanager.entities.Author;
import com.rodrigopeleias.bookstoremanager.entities.Book;
import com.rodrigopeleias.bookstoremanager.entities.Publisher;
import com.rodrigopeleias.bookstoremanager.repository.BookRepository;
import com.rodrigopeleias.bookstoremanager.repository.PublisherRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class BookService {

    private BookRepository bookRepository;

    private AuthorService authorService;

    private PublisherRepository publisherRepository;

    public BookResponse create(BookRequest bookRequest) {
        Author author = authorService.findOrCreate(bookRequest.getAuthorName());
        Publisher publisher = this.findOrCreatePublisher(bookRequest.getPublisherName());

        Book book = mapRequestToEntity(bookRequest, author, publisher);

        Book savedBook = bookRepository.save(book);

        return mapEntityToResponse(savedBook);
    }

    private Book mapRequestToEntity(BookRequest bookRequest, Author author, Publisher publisher) {
        return Book.builder()
                .name(bookRequest.getName())
                .pages(bookRequest.getPages())
                .chapters(bookRequest.getChapters())
                .isbn(bookRequest.getIsbn())
                .publisher(publisher)
                .author(author).build();
    }

    private BookResponse mapEntityToResponse(Book savedBook) {
        return BookResponse.builder()
                .id(savedBook.getId())
                .name(savedBook.getName())
                .pages(savedBook.getPages())
                .chapters(savedBook.getChapters())
                .isbn(savedBook.getIsbn())
                .publisherName(savedBook.getPublisher().getName())
                .createdAt(savedBook.getAudit().getCreatedAt())
                .updatedAt(savedBook.getAudit().getUpdatedAt())
                .authorName(savedBook.getAuthor().getName()).build();
    }

    private Publisher findOrCreatePublisher(String publisherName) {
        return Optional.of(publisherRepository.findByName(publisherName))
                .orElse(Publisher.builder().name(publisherName).build());
    }


}
