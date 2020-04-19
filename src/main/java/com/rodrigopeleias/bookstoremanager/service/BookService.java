package com.rodrigopeleias.bookstoremanager.service;

import com.rodrigopeleias.bookstoremanager.dto.BookDTO;
import com.rodrigopeleias.bookstoremanager.dto.MessageResponseDTO;
import com.rodrigopeleias.bookstoremanager.entities.Book;
import com.rodrigopeleias.bookstoremanager.exception.BookAlreadyExistsException;
import com.rodrigopeleias.bookstoremanager.mapper.BookMapper;
import com.rodrigopeleias.bookstoremanager.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper = BookMapper.INSTANCE;

    public MessageResponseDTO create(BookDTO bookDTO) throws BookAlreadyExistsException {
        Optional<Book> duplicityBookVerification = bookRepository.findByName(bookDTO.getName());
        if (!duplicityBookVerification.isEmpty()) {
            Book duplicatedBook = duplicityBookVerification.get();
            throw new BookAlreadyExistsException(duplicatedBook.getId(), duplicatedBook.getName());
        }
        Book book = bookMapper.toModel(bookDTO);
        Book savedBook = bookRepository.save(book);

        return createReturnMessage("Book with ID %s successfully created.", savedBook.getId());
    }

    private MessageResponseDTO createReturnMessage(String message, Long id) {
        return MessageResponseDTO.builder()
                .message(String.format(message, id))
                .build();
    }
}
