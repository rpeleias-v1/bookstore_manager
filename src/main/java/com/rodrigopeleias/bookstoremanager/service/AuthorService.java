package com.rodrigopeleias.bookstoremanager.service;

import com.rodrigopeleias.bookstoremanager.entities.Author;
import com.rodrigopeleias.bookstoremanager.repository.AuthorRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AuthorService {

    private AuthorRepository authorRepository;

    public Author findOrCreate(String authorName) {
        return Optional.ofNullable(authorRepository.findByName(authorName))
                .orElse(createAndSave(authorName));
    }

    private Author createAndSave(String authorName) {
        Author newAuthor = Author.builder().name(authorName).build();
        return authorRepository.save(newAuthor);
    }
}
