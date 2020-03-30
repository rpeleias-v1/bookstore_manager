package com.rodrigopeleias.bookstoremanager.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class BookRequest {

    @NotBlank
    private String name;

    @NotNull
    private Integer pages;

    @NotNull
    private Integer chapters;

    @NotBlank
    private String isbn;

    @NotBlank
    private String publisherName;

    @NotBlank
    private String authorName;

}
