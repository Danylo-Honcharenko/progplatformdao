package org.ua.fkrkm.proglatformdao.entity;

import lombok.Builder;
import lombok.Data;

/**
 * Сутність "Статистика проходження модуля"
 */
@Data
@Builder
public class ModuleStat {
    private int id;
    private int moduleId;
    private int topicId;
    private int userId;
}
