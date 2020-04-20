package com.rodrigopeleias.bookstoremanager.mapper;

import com.rodrigopeleias.bookstoremanager.dto.AuthorDTO;
import com.rodrigopeleias.bookstoremanager.dto.BookDTO;
import com.rodrigopeleias.bookstoremanager.entities.Author;
import com.rodrigopeleias.bookstoremanager.entities.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.rodrigopeleias.bookstoremanager.utils.BookUtils.createFakeBook;
import static com.rodrigopeleias.bookstoremanager.utils.BookUtils.createFakeBookDTO;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class BookMapperTest {

    private static final BookMapper bookMapper = BookMapper.INSTANCE;

    @Test
    void testGivenBookDTOThenReturnBookEntity() {
        BookDTO bookDTO = createFakeBookDTO();
        Book book = bookMapper.toModel(bookDTO);

        assertBookDTOConversionToEntity(bookDTO, book);
        assertAuthorDTOConversionToEntity(bookDTO.getAuthor(), book.getAuthor());
    }

    @Test
    void testGivenBookEntityThenReturnBookDTO() {
        Book book = createFakeBook();
        BookDTO bookDTO = bookMapper.toDTO(book);

        assertBookEntityConversionToDTO(book, bookDTO);
        assertAuthorEntityConversionToDTO(book.getAuthor(), bookDTO.getAuthor());
    }

    private void assertBookDTOConversionToEntity(BookDTO bookDTO, Book book) {
        assertEquals(bookDTO.getId(), book.getId());
        assertEquals(bookDTO.getName(), book.getName());
        assertEquals(bookDTO.getPages(), book.getPages());
        assertEquals(bookDTO.getChapters(), book.getChapters());
        assertEquals(bookDTO.getIsbn(), book.getIsbn());
        assertEquals(bookDTO.getPublisherName(), book.getPublisherName());
    }

    private void assertAuthorDTOConversionToEntity(AuthorDTO authorDTO, Author author) {
        assertEquals(authorDTO.getId(), author.getId());
        assertEquals(authorDTO.getName(), author.getName());
    }

    private void assertBookEntityConversionToDTO(Book book, BookDTO bookDTO) {
        assertEquals(book.getId(), bookDTO.getId());
        assertEquals(book.getName(), bookDTO.getName());
        assertEquals(book.getPages(), bookDTO.getPages());
        assertEquals(book.getChapters(), bookDTO.getChapters());
        assertEquals(book.getIsbn(), bookDTO.getIsbn());
        assertEquals(book.getPublisherName(), bookDTO.getPublisherName());
    }

    private void assertAuthorEntityConversionToDTO(Author author, AuthorDTO authorDTO) {
        assertEquals(author.getId(), authorDTO.getId());
        assertEquals(author.getName(), authorDTO.getName());
    }
}
