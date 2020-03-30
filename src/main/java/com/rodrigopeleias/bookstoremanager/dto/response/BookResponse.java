package com.rodrigopeleias.bookstoremanager.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookResponse {

    private Long id;

    private String name;

    private Integer pages;

    private Integer chapters;

    private String isbn;

    private String publisherName;

    private String authorName;
}
