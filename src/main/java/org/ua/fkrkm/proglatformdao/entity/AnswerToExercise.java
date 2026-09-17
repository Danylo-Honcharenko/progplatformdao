package org.ua.fkrkm.proglatformdao.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

/**
 * Сутність "Відповідь на завдання"
 */
@Getter
@Setter
@SuperBuilder
@EqualsAndHashCode(callSuper = false)
public class AnswerToExercise extends BaseEntity {
    // Текст відповіді
    private String text;
    // Шлях до файлу
    private String filePath;
    // ID користувача
    private Long userId;
}
