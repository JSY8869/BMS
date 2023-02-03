package eon.bms.mongo.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class LoginForm {

    @NotEmpty
    private String identifier;
    @NotEmpty
    private String password;
}
