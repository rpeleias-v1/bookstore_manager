package com.rodrigopeleias.bookstoremanager.service;

import com.rodrigopeleias.bookstoremanager.dto.BookDTO;
import com.rodrigopeleias.bookstoremanager.dto.MessageResponseDTO;
import com.rodrigopeleias.bookstoremanager.entities.Author;
import com.rodrigopeleias.bookstoremanager.entities.Book;
import com.rodrigopeleias.bookstoremanager.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorService authorService;

    public MessageResponseDTO create(BookDTO bookDTO) {
        //Author author = authorService.findOrCreate(bookDTO.getAuthorName());

        Book book = mapRequestToEntity(bookDTO, null);

        Book savedBook = bookRepository.save(book);

        return null;
    }

    private Book mapRequestToEntity(BookDTO bookDTO, Author author) {
        return Book.builder()
                .name(bookDTO.getName())
                .pages(bookDTO.getPages())
                .chapters(bookDTO.getChapters())
                .isbn(bookDTO.getIsbn())
                .author(author).build();
    }
}
