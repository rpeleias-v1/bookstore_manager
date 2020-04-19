package com.rodrigopeleias.bookstoremanager.utils;

import com.github.javafaker.Faker;
import com.rodrigopeleias.bookstoremanager.dto.AuthorDTO;
import com.rodrigopeleias.bookstoremanager.dto.BookDTO;
import com.rodrigopeleias.bookstoremanager.entities.Author;
import com.rodrigopeleias.bookstoremanager.entities.Book;

public class BookUtils {

    private static final Faker faker = Faker.instance();

    public static BookDTO createFakeBookDTO() {
        return BookDTO.builder()
                .id(faker.number().randomNumber())
                .name(faker.book().title())
                .pages(faker.number().numberBetween(0, 200))
                .chapters(faker.number().numberBetween(1, 20))
                .isbn("0-596-52068-9")
                .publisherName(faker.book().publisher())
                .author(createFakeAuthorDTO())
                .build();
    }

    private static AuthorDTO createFakeAuthorDTO() {
        return AuthorDTO.builder()
                .id(faker.number().randomNumber())
                .name(faker.book().author())
                .build();
    }

    public static Book createFakeBook() {
        return Book.builder()
                .id(faker.number().randomNumber())
                .name(faker.book().title())
                .pages(faker.number().numberBetween(0, 200))
                .chapters(faker.number().numberBetween(1, 20))
                .isbn("0-596-52068-9")
                .publisherName(faker.book().publisher())
                .author(createFakeAuthor())
                .build();
    }

    private static Author createFakeAuthor() {
        return Author.builder()
                .id(faker.number().randomNumber())
                .name(faker.book().author())
                .build();
    }

}
