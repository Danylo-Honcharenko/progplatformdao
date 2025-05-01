package org.ua.fkrkm.proglatformdao.entityMongo.view;

import lombok.Builder;
import lombok.Data;
import java.util.List;

@Data
@Builder
public class TestView {
    private String uuid;
    private Integer topicId;
    private String name;
    private List<QuestionView> questions;
    private String created;
    private String updated;
}
