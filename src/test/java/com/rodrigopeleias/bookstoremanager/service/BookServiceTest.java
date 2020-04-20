package com.rodrigopeleias.bookstoremanager.service;

import com.rodrigopeleias.bookstoremanager.dto.AuthorDTO;
import com.rodrigopeleias.bookstoremanager.dto.BookDTO;
import com.rodrigopeleias.bookstoremanager.dto.MessageResponseDTO;
import com.rodrigopeleias.bookstoremanager.entities.Author;
import com.rodrigopeleias.bookstoremanager.entities.Book;
import com.rodrigopeleias.bookstoremanager.exception.BookAlreadyExistsException;
import com.rodrigopeleias.bookstoremanager.exception.BookNotFoundException;
import com.rodrigopeleias.bookstoremanager.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.rodrigopeleias.bookstoremanager.utils.BookUtils.createFakeBook;
import static com.rodrigopeleias.bookstoremanager.utils.BookUtils.createFakeBookDTO;
import static com.rodrigopeleias.bookstoremanager.utils.BookUtils.createFakeBookFrom;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    @Test
    void whenNewBookInformedThenReturnSuccessCreateMessage() throws BookAlreadyExistsException {
        BookDTO bookDTO = createFakeBookDTO();
        Book expectedCreatedBook = createFakeBookFrom(bookDTO);

        when(bookRepository.save(expectedCreatedBook)).thenReturn(expectedCreatedBook);

        String expectedCreatedMessage = String.format("Book with ID %s successfully created.", expectedCreatedBook.getId());
        MessageResponseDTO messageResponse = bookService.create(bookDTO);
        assertEquals(expectedCreatedMessage, messageResponse.getMessage());
    }

    @Test
    void whenDuplicatedBookInformedThenAnExceptionIsThrown() throws BookAlreadyExistsException {
        BookDTO bookDTO = createFakeBookDTO();
        Book expectedCreatedBook = createFakeBookFrom(bookDTO);

        when(bookRepository.findByName(bookDTO.getName())).thenReturn(Optional.of(expectedCreatedBook));

        assertThrows(BookAlreadyExistsException.class, () -> bookService.create(bookDTO));
    }

    @Test
    void whenGivenExistingIdThenReturnThisBook() throws BookNotFoundException {
        Book expectedFoundBook = createFakeBook();

        when(bookRepository.findById(expectedFoundBook.getId())).thenReturn(Optional.of(expectedFoundBook));

        BookDTO bookDTO = bookService.findById(expectedFoundBook.getId());
        assertDTOisConvertedFromEntity(expectedFoundBook, bookDTO);
        assertDTOisConvertedFromEntity(expectedFoundBook.getAuthor(), bookDTO.getAuthor());
    }

    @Test
    void whenGivenUnexistingIdThenThrowAnException() {
        var invalidId = 1L;
        when(bookRepository.findById(invalidId))
                .thenReturn(Optional.ofNullable(any(Book.class)));

        assertThrows(BookNotFoundException.class, () -> bookService.findById(invalidId));
    }

    private void assertDTOisConvertedFromEntity(Book expectedFoundBook, BookDTO bookDTO) {
        assertEquals(expectedFoundBook.getId(), bookDTO.getId());
        assertEquals(expectedFoundBook.getName(), bookDTO.getName());
        assertEquals(expectedFoundBook.getChapters(), bookDTO.getChapters());
        assertEquals(expectedFoundBook.getPages(), bookDTO.getPages());
        assertEquals(expectedFoundBook.getPublisherName(), bookDTO.getPublisherName());
    }

    private void assertDTOisConvertedFromEntity(Author expectedFoundAuthor, AuthorDTO authorDTO) {
        assertEquals(expectedFoundAuthor.getId(), authorDTO.getId());
        assertEquals(expectedFoundAuthor.getName(), authorDTO.getName());
        assertEquals(expectedFoundAuthor.getAge(), authorDTO.getAge());
    }
}
