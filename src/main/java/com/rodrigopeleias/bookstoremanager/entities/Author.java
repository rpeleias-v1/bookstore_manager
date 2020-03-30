package com.rodrigopeleias.bookstoremanager.entities;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@Entity
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String phone;

    private String email;

    private LocalDateTime birthdate;

    private Integer age;

    @Embedded
    private Audit audit = new Audit();

    @OneToMany(mappedBy = "author")
    private List<Book> books;
}
