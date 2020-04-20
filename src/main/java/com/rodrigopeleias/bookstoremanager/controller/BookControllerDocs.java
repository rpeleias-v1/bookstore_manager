package com.rodrigopeleias.bookstoremanager.controller;

import com.rodrigopeleias.bookstoremanager.dto.BookDTO;
import com.rodrigopeleias.bookstoremanager.dto.MessageResponseDTO;
import com.rodrigopeleias.bookstoremanager.exception.BookAlreadyExistsException;
import com.rodrigopeleias.bookstoremanager.exception.BookNotFoundException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;

@Api("Handle and return books.")
public interface BookControllerDocs {

    @ApiOperation(value = "Create a new book.")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "New book is successfully created."),
            @ApiResponse(code = 400, message = "A required field is missing or ISBN format is wrong.")
    })
    MessageResponseDTO create(BookDTO bookDTO) throws BookAlreadyExistsException;

    @ApiOperation(value = "Given an ID, return the corresponding book.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Book with informed ID is successfully returned."),
            @ApiResponse(code = 404, message = "Error returned when a book is not found.")
    })
    BookDTO findById(@PathVariable Long bookId) throws BookNotFoundException;
}
