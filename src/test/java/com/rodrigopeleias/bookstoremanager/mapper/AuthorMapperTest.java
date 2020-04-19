package com.rodrigopeleias.bookstoremanager.mapper;

import com.rodrigopeleias.bookstoremanager.dto.AuthorDTO;
import com.rodrigopeleias.bookstoremanager.dto.BookDTO;
import com.rodrigopeleias.bookstoremanager.entities.Author;
import com.rodrigopeleias.bookstoremanager.entities.Book;
import com.rodrigopeleias.bookstoremanager.utils.AuthorUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.rodrigopeleias.bookstoremanager.utils.AuthorUtils.*;
import static com.rodrigopeleias.bookstoremanager.utils.BookUtils.createFakeBook;
import static com.rodrigopeleias.bookstoremanager.utils.BookUtils.createFakeBookDTO;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class AuthorMapperTest {

    private static final AuthorMapper authorMapper = AuthorMapper.INSTANCE;

    @Test
    void testGivenAuthorDTOThenReturnAuthorEntity() {
        AuthorDTO authorDTO = createFakeAuthorDTO();
        Author author = authorMapper.toModel(authorDTO);

        assertAuthorDTOConversionToEntity(authorDTO, author);
    }

    @Test
    void testGivenAuthorEntityThenReturnAuthorDTO() {
        Author author = createFakeAuthor();
        AuthorDTO authorDTO = authorMapper.toDTO(author);

        assertAuthorEntityConversionToDTO(author, authorDTO);
    }

    private void assertAuthorDTOConversionToEntity(AuthorDTO authorDTO, Author author) {
        assertEquals(authorDTO.getId(), author.getId());
        assertEquals(authorDTO.getName(), author.getName());
    }

    private void assertAuthorEntityConversionToDTO(Author author, AuthorDTO authorDTO) {
        assertEquals(author.getId(), authorDTO.getId());
        assertEquals(author.getName(), authorDTO.getName());
    }
}
