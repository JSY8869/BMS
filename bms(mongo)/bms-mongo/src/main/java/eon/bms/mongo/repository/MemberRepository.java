package eon.bms.mongo.repository;


import eon.bms.mongo.domain.Member;
import eon.bms.mongo.dto.RegisterForm;

public interface MemberRepository {
    Boolean existsByIdentifier(String identifier);

    Member findByIdentifier(String identifier);

    void save(RegisterForm form);

}
