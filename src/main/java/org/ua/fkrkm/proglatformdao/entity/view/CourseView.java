package org.ua.fkrkm.proglatformdao.entity.view;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ua.fkrkm.proglatformdao.entity.Topic;

import java.util.Date;
import java.util.List;

/**
 * Відображення курсів
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseView {
    // ID
    private int id;
    // Назва курсу
    private String name;
    // Опис курсу
    private String description;
    // Дата створення курсу
    private Date created;
    // Теми курсу
    private List<Topic> topics;
}
