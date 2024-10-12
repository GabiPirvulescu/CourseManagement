package net.coursemanagement.course_app.service;

import net.coursemanagement.course_app.dto.CourseDTO;
import net.coursemanagement.course_app.dto.StudentDTO;
import net.coursemanagement.course_app.entity.Course;

import java.util.List;

public interface CourseService {
    CourseDTO getCourseById(Long cId);
    CourseDTO createCourse(CourseDTO courseDTO);
    CourseDTO updateCourse(Long cId, CourseDTO courseDTO);
    void deleteCourse(Long cId);
    List<CourseDTO> getAllCourses();
    List<StudentDTO> getAllStudentsForCourse(Long cId);

}
