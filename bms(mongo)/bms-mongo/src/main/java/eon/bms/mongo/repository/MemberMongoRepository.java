package eon.bms.mongo.repository;

import eon.bms.mongo.domain.Member;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface MemberMongoRepository extends MongoRepository<Member, String> {
    Optional<Member> findByIdentifier(String identifier);
}
