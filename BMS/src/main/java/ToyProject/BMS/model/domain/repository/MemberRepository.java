package ToyProject.BMS.model.domain.repository;

import ToyProject.BMS.model.domain.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
@Transactional
public class MemberRepository {

    private final EntityManager em;

    public Boolean saveMember(Member member, BindingResult bindingResult) {
        if (findByLoginId(member.getLoginId()).isEmpty()) {
            em.persist(member);
            em.flush();
            em.clear();
            return true;
        } else {
            bindingResult.reject("member save error", "이미 존재하는 아이디 입니다.");
            return false;
        }
    }

    public Optional<Member> findByLoginId(String loginId) {
        List<Member> memberList = em.createQuery("select m from Member m where m.loginId = :loginId")
                .setParameter("loginId", loginId)
                .getResultList();
        return memberList.stream().findAny();
    }
}
