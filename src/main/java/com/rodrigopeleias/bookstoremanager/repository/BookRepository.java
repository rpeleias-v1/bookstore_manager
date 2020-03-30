package com.rodrigopeleias.bookstoremanager.repository;

import com.rodrigopeleias.bookstoremanager.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
