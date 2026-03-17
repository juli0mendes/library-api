package io.github.juli0mendes.library.repository;

import io.github.juli0mendes.library.model.Author;
import io.github.juli0mendes.library.model.Book;
import io.github.juli0mendes.library.model.BookGender;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@SpringBootTest
class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Test
    public void testSaveBookWithoutCascadeStrategy() {

        var book = new Book();
        book.setIsbn("978-0547928227");
        book.setPrice(BigDecimal.valueOf(100));
        book.setGender(BookGender.FICCION);
        book.setTitle("The Hobbit");
        book.setPublicationDate(LocalDate.of(1980, 1, 1));

        var author = this.authorRepository.findById(UUID.fromString("b48e0297-0436-4b68-960c-edc181b79b77"))
                .orElse(null);

        book.setAuthor(author);

        this.bookRepository.save(book);
    }

    @Test
    public void testSaveBookWithCascadeStrategy() {

        var book = new Book();
        book.setIsbn("978-0547928228");
        book.setPrice(BigDecimal.valueOf(100));
        book.setGender(BookGender.FICCION);
        book.setTitle("The Hobbit");
        book.setPublicationDate(LocalDate.of(1980, 1, 1));

        var author = new Author();
        author.setName("Vincent van Gogh");
        author.setNationality("French");
        author.setBirthDate(LocalDate.of(1950, 1, 1));

        book.setAuthor(author);

        this.bookRepository.save(book);

    }

    @Test
    public void testSaveAuthorAndBookWithoutCascadeStrategy() {

        var book = new Book();
        book.setIsbn("978-0547928227");
        book.setPrice(BigDecimal.valueOf(100));
        book.setGender(BookGender.FICCION);
        book.setTitle("The Hobbit");
        book.setPublicationDate(LocalDate.of(1980, 1, 1));

        var author = this.authorRepository.findById(UUID.fromString("b48e0297-0436-4b68-960c-edc181b79b77"))
                .orElse(null);

        book.setAuthor(author);

        this.bookRepository.save(book);
    }
}