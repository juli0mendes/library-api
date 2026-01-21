package io.github.juli0mendes.library.model;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "livro", schema = "public")
@NoArgsConstructor
@Data
public class Book {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "isbn", nullable = false, length = 20)
    private String isbn;

    @Column(name = "titulo", nullable = false, length = 150)
    private String title;

    @Column(name = "data_publicacao", nullable = false)
    private LocalDate publicationDate;

    @Convert(converter = BookGenderConverter.class)
    @Column(name = "genero", nullable = false, length = 30)
    private BookGender gender;

    @Column(name = "preco", precision = 18, scale = 2)
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "id_autor")
    private Author author;
}
