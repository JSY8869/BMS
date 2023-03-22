package eon.bms.repository;


import eon.bms.domain.Member;
import eon.bms.dto.RegisterForm;

public interface MemberRepository {
    Boolean existsByIdentifier(String identifier);

    Member findByIdentifier(String identifier);

    void save(RegisterForm form);

}
