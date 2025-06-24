package org.ua.fkrkm.proglatformdao.entity.view;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * Сутність "Статистика проходження всіх модулів"
 */
@Data
@Builder
public class ModuleStateView {
    private int moduleId;
    private BigDecimal moduleCompletionPercentage;
}
