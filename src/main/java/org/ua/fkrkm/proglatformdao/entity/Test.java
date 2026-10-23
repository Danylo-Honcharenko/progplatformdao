package org.ua.fkrkm.proglatformdao.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

/**
 * Сутність "Тест"
 */
@Getter
@Setter
@SuperBuilder
@EqualsAndHashCode(callSuper = false)
public class Test extends BaseEntity {
    // Назва тесту
    private String name;
    // ID теми
    private Long topicId;
    // Питання тесту
    private List<Question> questions;
}
