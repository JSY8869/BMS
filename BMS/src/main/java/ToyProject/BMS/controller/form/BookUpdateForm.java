package ToyProject.BMS.controller.form;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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

    @NotNull
    @NotBlank
    private String genre;

    @NotNull
    @NotBlank
    private String company;
}
