package org.ua.fkrkm.proglatformdao.entityMongo.view;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class QuestionView {
    private String question;
    private List<String> options;
}
