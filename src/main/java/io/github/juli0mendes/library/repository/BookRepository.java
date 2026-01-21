package io.github.juli0mendes.library.repository;

import io.github.juli0mendes.library.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BookRepository extends JpaRepository<Book, UUID> {
}
