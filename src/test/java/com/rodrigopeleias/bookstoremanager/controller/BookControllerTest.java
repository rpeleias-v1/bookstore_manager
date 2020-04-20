package com.rodrigopeleias.bookstoremanager.controller;

import com.rodrigopeleias.bookstoremanager.dto.BookDTO;
import com.rodrigopeleias.bookstoremanager.dto.MessageResponseDTO;
import com.rodrigopeleias.bookstoremanager.exception.BookNotFoundException;
import com.rodrigopeleias.bookstoremanager.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import static com.rodrigopeleias.bookstoremanager.utils.BookUtils.asJsonString;
import static com.rodrigopeleias.bookstoremanager.utils.BookUtils.createFakeBookDTO;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class BookControllerTest {

    private static final String BOOK_API_URL_PATH = "/api/v1/books";

    private MockMvc mockMvc;

    @Mock
    private BookService bookService;

    @InjectMocks
    private BookController bookController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(bookController)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .setViewResolvers((viewName, locale) -> new MappingJackson2JsonView())
                .build();
    }

    @Test
    void testWhenPOSTisCalledThenABookShouldBeCreated() throws Exception {
        BookDTO bookDTO = createFakeBookDTO();
        MessageResponseDTO successfulCreateMessage = createReturnMessage("Book with ID %s successfully created.", bookDTO.getId());

        when(bookService.create(bookDTO)).thenReturn(successfulCreateMessage);

        mockMvc.perform(post(BOOK_API_URL_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(bookDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.message", is(successfulCreateMessage.getMessage())));
    }

    @Test
    void testWhenPOSTwithInvalidISBNIsCalledThenBadRequestShouldBeReturned() throws Exception {
        BookDTO bookDTO = createFakeBookDTO();
        bookDTO.setIsbn("invalid isbn");

        mockMvc.perform(post(BOOK_API_URL_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(bookDTO)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testWhenPOSTwithoutRequiredFieldInformedIsCalledThenBadRequestShouldBeReturned() throws Exception {
        BookDTO bookDTO = createFakeBookDTO();
        bookDTO.setName(null);

        mockMvc.perform(post(BOOK_API_URL_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(bookDTO)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testWhenGETWithValidIDisCalledThenABookShouldBeRetorned() throws Exception {
        BookDTO bookDTO = createFakeBookDTO();

        when(bookService.findById(bookDTO.getId())).thenReturn(bookDTO);

        mockMvc.perform(get(BOOK_API_URL_PATH + "/" + bookDTO.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(bookDTO.getId().intValue())))
                .andExpect(jsonPath("$.name", is(bookDTO.getName())))
                .andExpect(jsonPath("$.pages", is(bookDTO.getPages().intValue())))
                .andExpect(jsonPath("$.chapters", is(bookDTO.getChapters().intValue())))
                .andExpect(jsonPath("$.isbn", is(bookDTO.getIsbn())))
                .andExpect(jsonPath("$.publisherName", is(bookDTO.getPublisherName())));
    }

    @Test
    void testWhenGETWithInvalidIDisCalledThenNotFoundShouldBeRetorned() throws Exception {
        var invalidId = 1L;

        when(bookService.findById(invalidId)).thenThrow(BookNotFoundException.class);

        mockMvc.perform(get(BOOK_API_URL_PATH + "/" + invalidId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void testWhenDELETEWithValidIDisCalledThenABookShouldBeDeleted() throws Exception {
        var validId = 1L;

        mockMvc.perform(delete(BOOK_API_URL_PATH + "/" + validId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        verify(bookService, times(1)).deleteById(validId);
    }

    private MessageResponseDTO createReturnMessage(String message, Long id) {
        return MessageResponseDTO.builder()
                .message(String.format(message, id))
                .build();
    }
}
