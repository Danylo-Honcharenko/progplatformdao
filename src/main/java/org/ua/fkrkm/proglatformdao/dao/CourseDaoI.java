package org.ua.fkrkm.proglatformdao.dao;

import org.ua.fkrkm.proglatformdao.entity.Course;

import java.util.List;

/**
 * DAO інтерфейс для сутності Course
 */
public interface CourseDaoI extends ParentDaoI<Course> {
    /**
     * Додати користувача до курсу
     *
     * @param courseId ID курсу
     * @param userId ID користувача
     */
    void addUserToCourse(int courseId, int userId);
    /**
     * Видалити всіх користувачів курсу
     *
     * @param courseId ID курсу
     */
    void removeAllUsersFromCourse(Integer courseId);
    /**
     * Видалити користувача з курсу
     *
     * @param courseId ID курсу
     * @param userId ID користувача
     */
    void removeUserFromCourse(Integer courseId, Integer userId);
    /**
     * Отримати ID користувачів курсу
     *
     * @param courseId ID курсу
     * @return List<Integer> ID користувачів
     */
    List<Integer> getCourseUsersIdByCourseId(int courseId);
    /**
     * Отримати курси користувача
     *
     * @param userId ID користувача
     * @return List<Course> список курсів
     */
    List<Course> getCoursesIdByUserId(int userId);
}
