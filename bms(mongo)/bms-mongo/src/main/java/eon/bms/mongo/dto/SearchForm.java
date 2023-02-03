package eon.bms.mongo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SearchForm {

    private String category;
    @NotNull
    @NotBlank
    private String keyword;
}
