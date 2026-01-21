package io.github.juli0mendes.library.repository;

import io.github.juli0mendes.library.model.Author;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Optional;

@SpringBootTest
public class AuthorRepositoryTest {

    @Autowired
    private AuthorRepository authorRepository;

    @Test
    public void testSaveAuthor() {
        var author = new Author();
        author.setName("J. R. R. Tolkien");
        author.setNationality("British");
        author.setBirthDate(LocalDate.of(1950, 1, 1));

        var savedAuthor = this.authorRepository.save(author);
        System.out.println("Author saved with ID: " + savedAuthor.getId());
    }

    @Test
    public void testUpdateAuthor() {
        var author = new Author();
        author.setName("Júlio Mendes");
        author.setNationality("American");
        author.setBirthDate(LocalDate.of(1948, 9, 20));

        var savedAuthor = this.authorRepository.save(author);
        System.out.println("Author saved with ID: " + savedAuthor.getId());

        Optional<Author> authorExists = this.authorRepository.findById(savedAuthor.getId());

        if (authorExists.isPresent()) {
            var authorData = authorExists.get();
            System.out.println("Author data: " + authorData);

            authorData.setNationality("Canadian");

            this.authorRepository.save(authorData);
        }
    }

    @Test
    public void testFindAllAuthors() {
        var authors = this.authorRepository.findAll();
        authors.forEach(author -> System.out.println("Author: " + author));
    }

    @Test
    public void testCountAuthors() {
        long count = this.authorRepository.count();
        System.out.println("Total authors: " + count);
    }

    @Test
    public void testDeleteByIdAuthor() {
        var author = new Author();
        author.setName("To Delete");
        author.setNationality("Nowhere");
        author.setBirthDate(LocalDate.of(2000, 1, 1));

        var savedAuthor = this.authorRepository.save(author);
        System.out.println("Author saved with ID: " + savedAuthor.getId());

        this.authorRepository.deleteById(savedAuthor.getId());
        System.out.println("Author deleted with ID: " + savedAuthor.getId());
    }

    @Test
    public void testDeleteAuthor() {
        var author = new Author();
        author.setName("To Delete Directly");
        author.setNationality("Nowhere");
        author.setBirthDate(LocalDate.of(2001, 2, 2));

        var savedAuthor = this.authorRepository.save(author);
        System.out.println("Author saved with ID: " + savedAuthor.getId());

        this.authorRepository.delete(savedAuthor);
        System.out.println("Author deleted with ID: " + savedAuthor.getId());
    }
}
