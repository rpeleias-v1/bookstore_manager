package com.rodrigopeleias.bookstoremanager.service;

import com.rodrigopeleias.bookstoremanager.repository.PublisherRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PublisherService {

    private PublisherRepository publisherRepository;


}
