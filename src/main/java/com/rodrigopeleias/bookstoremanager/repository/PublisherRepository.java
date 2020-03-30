package com.rodrigopeleias.bookstoremanager.repository;

import com.rodrigopeleias.bookstoremanager.entities.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublisherRepository extends JpaRepository<Publisher, Long> {

    Publisher findByName(String name);
}
