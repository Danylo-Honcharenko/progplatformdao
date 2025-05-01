package org.ua.fkrkm.proglatformdao.entity.view;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@Builder
public class ModuleView {
    private int id;
    private String name;
    private String description;
    private List<TopicView> topics;
    private BigDecimal complete;
    private Boolean active;
    private Date created;
    private Date updated;
}
