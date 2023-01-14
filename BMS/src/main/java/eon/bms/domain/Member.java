package eon.bms.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "Member")
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor
@Builder @Getter
public class Member {

    @Id
    @Column(name = "identifier")
    private String identifier;

    @Column(name = "member_name", nullable = false)
    private String memberName;

    @Column(name = "password", nullable = false)
    private String password;
}
