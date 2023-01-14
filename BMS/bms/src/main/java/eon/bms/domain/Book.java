package eon.bms.domain;

import jakarta.persistence.*;
import lombok.*;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "Book")
@RequiredArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder @Getter
public class Book {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(name = "book_name", unique = true, nullable = false)
    private String name;
    @Column(name = "book_author", nullable = false)
    private String author;
    @Column(name = "book_year", nullable = false)
    private String year;
    @Column(name = "book_genre", nullable = false)
    @Enumerated(EnumType.STRING)
    private GenreEnum genre;
    @Column(name = "book_company", nullable = false)
    private String company;
}
