package eon.bms.mongo.dto;

import eon.bms.mongo.domain.Member;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
public class RegisterForm {
    @NotBlank(message = "아이디를 작성해주세요.")
    @Pattern(
            regexp = "^[A-Za-z0-9]{6,18}$",
            message = "아이디는 숫자, 문자 포함의 6~18자 이내로 작성해주세요."
    )
    private String identifier;

    @NotBlank(message = "비밀번호를 작성해주세요.")
    @Pattern(
            regexp = "^.*(?=^.{8,15}$)(?=.*\\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&+=*]).*$",
            message = "비밀번호는 숫자, 문자, 특수문자 포함의 8~15자 이내로 작성해주세요."
    )
    private String password;

    @NotBlank(message = "비밀번호를 작성해주세요.")
    @Pattern(
            regexp = "^.*(?=^.{8,15}$)(?=.*\\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&+=*]).*$",
            message = "비밀번호는 숫자, 문자, 특수문자 포함의 8~15자 이내로 작성해주세요."
    )
    private String repeatPassword;

    @NotBlank(message = "이름을 작성해주세요.")
    private String name;

    public Member toMember(PasswordEncoder passwordEncoder){
        return Member
                .builder()
                .identifier(identifier)
                .password(passwordEncoder.encode(password))
                .memberName(name)
                .build();
    }
}
