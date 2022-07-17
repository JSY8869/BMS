package ToyProject.BMS.controller;

import ToyProject.BMS.controller.form.LoginForm;
import ToyProject.BMS.model.domain.SessionConst;
import ToyProject.BMS.model.domain.entity.Member;
import ToyProject.BMS.model.domain.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@Slf4j
public class LoginController {

    private final MemberRepository memberRepository;

    @GetMapping("/login")
    public String loginForm(@ModelAttribute LoginForm loginForm) {
        return "/login/loginForm";
    }

    @PostMapping("/login")
    public String login(@RequestParam(defaultValue = "/") String redirectURL,
                        @Validated @ModelAttribute LoginForm loginForm,
                        BindingResult bindingResult,
                        HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            return "/login/loginForm";
        }

        log.info("loginId = {}", loginForm.getLoginId());
        try {
            Member member = memberRepository.findByLoginId(loginForm.getLoginId()).get();
            if (member.getLoginId().equals(loginForm.getLoginId()) && member.getPassword().equals(loginForm.getPassword())) {
                HttpSession session = request.getSession();
                session.setAttribute(SessionConst.LOGIN_MEMBER, member);
            } else {
                bindingResult.reject("login error", "아이디 또는 비밀번호가 맞지 않습니다.");
                return "/login/loginForm";
            }
        } catch (Exception e) {
            bindingResult.reject("no id error", "일치하는 아이디가 없습니다.");
            return "login/loginForm";
        }

        return "redirect:" + redirectURL;
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/";
    }
}
