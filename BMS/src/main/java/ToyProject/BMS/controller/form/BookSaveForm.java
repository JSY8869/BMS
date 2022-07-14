package ToyProject.BMS.controller.form;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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

    @NotNull
    @NotBlank
    private String genre;

    @NotNull
    @NotBlank
    private String company;
}
