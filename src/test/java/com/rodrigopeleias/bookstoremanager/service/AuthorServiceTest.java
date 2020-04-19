package com.rodrigopeleias.bookstoremanager.service;

import com.rodrigopeleias.bookstoremanager.dto.AuthorDTO;
import com.rodrigopeleias.bookstoremanager.entities.Author;
import com.rodrigopeleias.bookstoremanager.repository.AuthorRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.rodrigopeleias.bookstoremanager.utils.AuthorUtils.createFakeAuthor;
import static com.rodrigopeleias.bookstoremanager.utils.AuthorUtils.createFakeAuthorDTO;
import static com.rodrigopeleias.bookstoremanager.utils.AuthorUtils.createFakeAuthorFrom;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AuthorServiceTest {

    @Mock
    private AuthorRepository authorRepository;

    @InjectMocks
    private AuthorService authorService;

    @Test
    void whenExistingAuthorInformedThenReturnThisAuthor() {
        AuthorDTO authorDTO = createFakeAuthorDTO();
        Author expectedFoundAuthor = createFakeAuthor();

        when(authorRepository.findByName(authorDTO.getName())).thenReturn(expectedFoundAuthor);
        Author foundAuthor = authorService.findOrCreate(authorDTO);

        assertValidFoundInformation(expectedFoundAuthor, foundAuthor);
    }

    @Test
    void whenNewAuthorInformedThenCreateNewOne() {
        AuthorDTO authorDTO = createFakeAuthorDTO();
        Author expectedNewAuthor = createFakeAuthorFrom(authorDTO);

        when(authorRepository.save(expectedNewAuthor)).thenReturn(expectedNewAuthor);

        Author newAuthor = authorService.findOrCreate(authorDTO);
        assertValidFoundInformation(expectedNewAuthor, newAuthor);
    }

    private void assertValidFoundInformation(Author expectedFoundAuthor, Author foundAuthor) {
        assertEquals(expectedFoundAuthor.getId(), foundAuthor.getId());
        assertEquals(expectedFoundAuthor.getName(), foundAuthor.getName());
        assertEquals(expectedFoundAuthor.getAge(), foundAuthor.getAge());
    }
}
