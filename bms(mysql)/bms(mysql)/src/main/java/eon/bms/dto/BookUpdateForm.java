package eon.bms.dto;

import eon.bms.domain.Book;
import eon.bms.domain.GenreEnum;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BookUpdateForm {

    @NotNull
    private Long id;

    @NotNull
    @NotBlank
    private String name;

    @NotNull
    @NotBlank
    private String author;

    @NotNull
    @NotBlank
    @Min(0)
    private String year;

    private GenreEnum genre;

    @NotNull
    @NotBlank
    private String company;
}
