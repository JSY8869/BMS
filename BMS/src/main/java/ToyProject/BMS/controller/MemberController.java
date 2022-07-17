package ToyProject.BMS.controller;

import ToyProject.BMS.model.domain.entity.Member;
import ToyProject.BMS.model.domain.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.PostConstruct;

@Slf4j
@Controller
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberRepository memberRepository;

    @GetMapping("/add")
    public String addMemberForm(@ModelAttribute Member member) {
        return "/basic/member/addMemberForm";
    }

    @PostMapping("/add")
    public String addMember(@Validated @ModelAttribute Member member, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "/basic/member/addMemberForm";
        }

        if (memberRepository.saveMember(member, bindingResult)) {
            return "redirect:/";
        } else {
            return "/basic/member/addMemberForm";
        }

    }
}
