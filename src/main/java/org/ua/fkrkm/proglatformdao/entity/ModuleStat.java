package org.ua.fkrkm.proglatformdao.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

/**
 * Сутність "Статистика проходження модуля"
 */
@Getter
@Setter
@SuperBuilder
@EqualsAndHashCode(callSuper = false)
public class ModuleStat extends BaseEntity {
    private Long moduleId;
    private Long topicId;
    private Long userId;
}
