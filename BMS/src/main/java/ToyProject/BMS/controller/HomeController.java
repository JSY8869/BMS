package ToyProject.BMS.controller;

import ToyProject.BMS.model.domain.SessionConst;
import ToyProject.BMS.model.domain.entity.Member;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(@SessionAttribute(value = SessionConst.LOGIN_MEMBER, required = false) Member member, Model model) {
        if (member == null) {
            return "home";
        } else {
            model.addAttribute("member", member);
            return "loginHome";
        }
    }

}
