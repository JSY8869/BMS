package eon.bms.mongo.dto;

import eon.bms.mongo.domain.Book;
import eon.bms.mongo.domain.GenreEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

@Data
public class BookSaveForm {

    @NotNull
    @NotBlank
    private String name;

    @NotNull
    @NotBlank
    private String author;

    @NotNull
    @NotBlank
    @Range(min = 0,max = 2022)
    private String year;

    private GenreEnum genre;

    @NotNull
    @NotBlank
    private String company;

    public Book toBook(){
        return Book.builder()
                .name(this.name)
                .author(this.author)
                .company(this.company)
                .genre(this.genre)
                .year(this.year)
                .build();
    }
}
