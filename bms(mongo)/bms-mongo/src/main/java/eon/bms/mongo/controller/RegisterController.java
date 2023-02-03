package eon.bms.mongo.controller;

import eon.bms.mongo.dto.RegisterForm;
import eon.bms.mongo.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class RegisterController {

    private final MemberService memberService;

    @GetMapping("/register")
    public String addMemberForm(@ModelAttribute("form") RegisterForm form) {
        return "/register";
    }

    @PostMapping("/register")
    public String addMember(@Validated @ModelAttribute("form") RegisterForm form, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            log.debug("필드 검증 오류={}", bindingResult);
            return "register";
        }

        if(memberService.existsByIdentifier(form.getIdentifier())){
            log.debug("아이디 중복 오류 identifier={}", form.getIdentifier());
            bindingResult.rejectValue("identifier", "duplicatedUser", "아이디가 중복됩니다.");
            return "register";
        }

        if(!form.getPassword().equals(form.getRepeatPassword())){
            log.debug("비밀번호 불일치={},{}", form.getPassword(), form.getRepeatPassword());
            bindingResult.reject("notSamePassword", "비밀번호가 일치하지 않습니다.");
            return "register";
        }

        memberService.save(form);

        return "redirect:/";

    }
}
