package eon.bms.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import eon.bms.domain.Member;
import eon.bms.domain.QMember;
import eon.bms.dto.RegisterForm;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Slf4j
public class MemberRepositoryImpl implements MemberRepository{
    private final JPAQueryFactory jpaQueryFactory;
    private final QMember qMember = QMember.member;
    private final PasswordEncoder passwordEncoder;
    private final EntityManager entityManager;


    @Override
    @Transactional(readOnly = true)
    public Boolean existsByIdentifier(String identifier) {
        return jpaQueryFactory.from(qMember)
                .where(qMember.identifier.eq(identifier))
                .select(qMember.identifier)
                .fetchFirst() != null;
    }

    @Override
    @Transactional(readOnly = true)
    public Member findByIdentifier(String identifier) {
        Optional<Member> member = Optional.ofNullable(jpaQueryFactory
                .selectFrom(qMember)
                .where(qMember.identifier.eq(identifier))
                .fetchOne());
        log.info("member = {}", member.get().getPassword());
        return member.orElseThrow(() -> new UsernameNotFoundException("유저 정보를 찾을 수 없습니다."));
    }

    @Override
    @Transactional
    public void save(RegisterForm form) {
        log.info("save member={}", form);
        entityManager.persist(form.toMember(passwordEncoder));
        entityManager.flush();
        entityManager.clear();
    }
}
