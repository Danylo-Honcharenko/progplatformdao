package org.ua.fkrkm.proglatformdao.entity;

import lombok.*;

import java.util.Date;

/**
 * Сутність "Відповідь на завдання"
 */
@Data
@Builder
public class AnswerToExercise {
    private Integer id;
    // Текст відповіді
    private String text;
    // Шлях до файлу
    private String filePath;
    // ID користувача
    private Long userId;
    // Час створення
    private Date created;
    // Час оновлення
    private Date updated;
}
