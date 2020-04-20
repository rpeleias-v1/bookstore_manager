package com.rodrigopeleias.bookstoremanager.controller;

import com.rodrigopeleias.bookstoremanager.dto.BookDTO;
import com.rodrigopeleias.bookstoremanager.dto.MessageResponseDTO;
import com.rodrigopeleias.bookstoremanager.exception.BookAlreadyExistsException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api("Handle and return books.")
public interface BookControllerDocs {

    @ApiOperation(value = "Create a new book.")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "New book is successfully created."),
            @ApiResponse(code = 400, message = "A required field is missing or ISBN format is wrong.")
    })
    MessageResponseDTO create(BookDTO bookDTO) throws BookAlreadyExistsException;
}
