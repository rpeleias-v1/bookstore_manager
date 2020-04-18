package com.rodrigopeleias.bookstoremanager.mapper;

import com.github.javafaker.Faker;
import com.rodrigopeleias.bookstoremanager.dto.request.BookDTO;
import com.rodrigopeleias.bookstoremanager.entities.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class BookMapperTest {

    private static final Faker faker = Faker.instance();

    @Test
    void testGivenBookDTOThenReturnBookEntity() {
        BookDTO bookDTO = BookDTO.builder()
                .id(faker.number().randomNumber())
                .name(faker.book().title())
                .pages(faker.number().numberBetween(0, 200))
                .chapters(faker.number().numberBetween(1, 20))
                .isbn("0-596-52068-9")
                .publisherName(faker.book().publisher()).build();


        Book book = BookMapper.INSTANCE.toModel(bookDTO);

        assertEquals(bookDTO.getId(), book.getId());
        assertEquals(bookDTO.getName(), book.getName());
        assertEquals(bookDTO.getPages(), book.getPages());
        assertEquals(bookDTO.getChapters(), book.getChapters());
        assertEquals(bookDTO.getIsbn(), book.getIsbn());
        assertEquals(bookDTO.getPublisherName(), book.getPublisherName());
    }

    @Test
    void testGivenBookEntityThenReturnBookDTO() {
        Book book = Book.builder()
                .id(faker.number().randomNumber())
                .name(faker.book().title())
                .pages(faker.number().numberBetween(0, 200))
                .chapters(faker.number().numberBetween(1, 20))
                .isbn("0-596-52068-9")
                .publisherName(faker.book().publisher()).build();


        BookDTO bookDTO = BookMapper.INSTANCE.toDTO(book);

        assertEquals(book.getId(), bookDTO.getId());
        assertEquals(book.getName(), bookDTO.getName());
        assertEquals(book.getPages(), bookDTO.getPages());
        assertEquals(book.getChapters(), bookDTO.getChapters());
        assertEquals(book.getIsbn(), bookDTO.getIsbn());
        assertEquals(book.getPublisherName(), bookDTO.getPublisherName());
    }
}
