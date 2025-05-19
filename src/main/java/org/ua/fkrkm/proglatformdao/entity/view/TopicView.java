package org.ua.fkrkm.proglatformdao.entity.view;

import lombok.Builder;
import lombok.Data;
import org.ua.fkrkm.proglatformdao.entity.Exercise;
import org.ua.fkrkm.proglatformdao.entityMongo.view.TestView;

import java.util.Date;
import java.util.List;

@Data
@Builder
public class TopicView {
    private Integer id;
    // Назва теми
    private String name;
    // Опис теми
    private String description;
    // ID курсу
    private Integer moduleId;
    // Статус виконання теми
    private Boolean done;
    // Тести
    private List<TestView> tests;
    // Завдання
    private Exercise exercise;
    // Час створення
    private Date created;
    // Час оновлення
    private Date updated;
}
