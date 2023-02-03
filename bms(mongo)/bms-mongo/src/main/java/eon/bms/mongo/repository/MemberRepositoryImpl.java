package eon.bms.mongo.repository;

import eon.bms.mongo.domain.Member;
import eon.bms.mongo.dto.RegisterForm;
import eon.bms.mongo.exception.CustomException;
import eon.bms.mongo.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepository{

    private final PasswordEncoder passwordEncoder;

    private final MemberMongoRepository memberMongoRepository;

    @Override
    public Boolean existsByIdentifier(String identifier) {
        return memberMongoRepository.existsById(identifier);
    }

    @Override
    public Member findByIdentifier(String identifier) {
        return memberMongoRepository.findByIdentifier(identifier)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
    }

    @Override
    public void save(RegisterForm form) {
        memberMongoRepository.save(form.toMember(passwordEncoder));
    }
}
