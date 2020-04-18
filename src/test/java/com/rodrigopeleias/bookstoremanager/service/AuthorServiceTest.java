package com.rodrigopeleias.bookstoremanager.service;

import com.github.javafaker.Faker;
import com.rodrigopeleias.bookstoremanager.entities.Author;
import com.rodrigopeleias.bookstoremanager.repository.AuthorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AuthorServiceTest {

    @Mock
    private AuthorRepository authorRepository;

    @InjectMocks
    private AuthorService authorService;

    private Faker faker;

    @BeforeEach
    void setUp() {
        faker = new Faker();
    }

    @Test
    void whenExistingAuthorInformedThenReturnThisAuthor() {
        Author expectedSavedAuthor = this.buildFakeSavedAuthor();
        when(authorRepository.findByName(expectedSavedAuthor.getName())).thenReturn(expectedSavedAuthor);

        Author foundAuthor = authorService.findOrCreate(expectedSavedAuthor.getName());

        validateExpectedAndSaved(expectedSavedAuthor, foundAuthor);
    }

    @Test
    void whenNewAuthorInformedThenCreateNewOne() {
        Author expectedSavedAuthor = this.buildFakeSavedAuthor();
        when(authorRepository.save(buildFakeNewAuthor(expectedSavedAuthor.getName()))).thenReturn(expectedSavedAuthor);

        Author foundAuthor = authorService.findOrCreate(expectedSavedAuthor.getName());

        validateExpectedAndSaved(expectedSavedAuthor, foundAuthor);
    }

    private void validateExpectedAndSaved(Author expectedSavedAuthor, Author foundAuthor) {
        assertEquals(expectedSavedAuthor.getId(), foundAuthor.getId());
        assertEquals(expectedSavedAuthor.getName(), foundAuthor.getName());
        assertEquals(expectedSavedAuthor.getAudit().getCreatedAt(), foundAuthor.getAudit().getCreatedAt());
        assertEquals(expectedSavedAuthor.getAudit().getUpdatedAt(), foundAuthor.getAudit().getUpdatedAt());
    }

    private Author buildFakeSavedAuthor() {
        Audit audit = Audit.builder()
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        return Author.builder()
                .id(faker.number().randomNumber())
                .name(faker.name().fullName())
                .audit(audit)
                .build();
    }

    private Author buildFakeNewAuthor(String newAuthorName) {
        return Author.builder()
                .name(newAuthorName)
                .build();
    }
}
