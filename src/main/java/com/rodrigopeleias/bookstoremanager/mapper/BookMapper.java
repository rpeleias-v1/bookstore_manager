package com.rodrigopeleias.bookstoremanager.mapper;

import com.rodrigopeleias.bookstoremanager.dto.BookDTO;
import com.rodrigopeleias.bookstoremanager.entities.Book;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BookMapper {

    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    Book toModel(BookDTO bookDTO);

    BookDTO toDTO(Book book);
}
