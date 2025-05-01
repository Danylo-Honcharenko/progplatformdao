package org.ua.fkrkm.proglatformdao.entityMongo;

import org.springframework.data.annotation.Id;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
@Data
@Builder
public class Test {
    @Id
    private String id;
    private Integer topicId;
    private String name;
    private List<Question> questions;
    private String created;
    private String updated;
}
