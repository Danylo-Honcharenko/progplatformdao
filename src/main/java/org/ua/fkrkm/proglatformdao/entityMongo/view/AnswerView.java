package org.ua.fkrkm.proglatformdao.entityMongo.view;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AnswerView {
    private String question;
    private String answer;
}
