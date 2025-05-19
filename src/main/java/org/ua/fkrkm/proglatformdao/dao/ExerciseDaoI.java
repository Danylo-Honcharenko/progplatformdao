package org.ua.fkrkm.proglatformdao.dao;

import org.ua.fkrkm.proglatformdao.entity.Exercise;

import java.util.List;

public interface ExerciseDaoI extends ParentDaoI<Exercise> {
    List<Exercise> findExercisesByTopicId(int topicId);
}
