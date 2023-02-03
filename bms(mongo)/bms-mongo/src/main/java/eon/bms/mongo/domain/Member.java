package eon.bms.mongo.domain;

import com.mongodb.lang.Nullable;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Member")
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor
@Builder @Getter
public class Member {

    @Id
    private String identifier;

    private String memberName;

    private String password;
}
