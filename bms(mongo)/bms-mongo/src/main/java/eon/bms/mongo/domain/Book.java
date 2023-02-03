package eon.bms.mongo.domain;

import eon.bms.mongo.dto.BookUpdateForm;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Book")
@RequiredArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder @Getter
public class Book {

    @Id
    private String id;

    private String name;
    private String author;
    private String year;
    private GenreEnum genre;
    private String company;

    public void updateBook(BookUpdateForm form) {
        this.name = form.getName();
        this.author = form.getAuthor();
        this.company = form.getCompany();
        this.year = form.getYear();
        this.genre = form.getGenre();
    }
}
