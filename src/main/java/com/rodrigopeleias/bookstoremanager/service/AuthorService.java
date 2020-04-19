package com.rodrigopeleias.bookstoremanager.service;

import com.rodrigopeleias.bookstoremanager.dto.AuthorDTO;
import com.rodrigopeleias.bookstoremanager.entities.Author;
import com.rodrigopeleias.bookstoremanager.mapper.AuthorMapper;
import com.rodrigopeleias.bookstoremanager.repository.AuthorRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AuthorService {

    private final AuthorRepository authorRepository;

    private final AuthorMapper authorMapper = AuthorMapper.INSTANCE;

    public Author findOrCreate(AuthorDTO authorDTO) {
        return Optional.ofNullable(authorRepository.findByName(authorDTO.getName()))
                .orElse(createAndSave(authorDTO));
    }

    private Author createAndSave(AuthorDTO authorDTO) {
        Author newAuthor = authorMapper.toModel(authorDTO);
        return authorRepository.save(newAuthor);
    }
}
