package org.ua.fkrkm.proglatformdao.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

/**
 * Сутність "Питання тесту"
 */
@Getter
@Setter
@SuperBuilder
@EqualsAndHashCode(callSuper = false)
public class Question extends BaseEntity {
    // ID тесту
    private Long testId;
    // Текст питання
    private String question;
    // Правильна відповідь
    private String correctAnswer;
    // Варіанти відповіді
    private List<String> options;
}
