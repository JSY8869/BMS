package eon.bms.service;

import eon.bms.domain.Member;
import eon.bms.dto.RegisterForm;
import eon.bms.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public void save(RegisterForm form){
        memberRepository.save(form);
    }

    public Boolean existsByIdentifier(String identifier) {
        return memberRepository.existsByIdentifier(identifier);
    }
}
