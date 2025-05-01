package org.ua.fkrkm.proglatformdao.entityMongo;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class Question {
    private String question;
    private String correctAnswer;
    private List<String> options;
}
