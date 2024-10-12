package net.coursemanagement.course_app.mapper;

import net.coursemanagement.course_app.dto.CourseDTO;
import net.coursemanagement.course_app.dto.StudentDTO;
import net.coursemanagement.course_app.entity.Course;
import net.coursemanagement.course_app.entity.Student;

public class CourseMapper {
    public static Course mapToCourse(CourseDTO courseDTO){
        Course course = new Course();
        course.setCId(courseDTO.getCId());
        course.setName(courseDTO.getName());
        course.setDescription(courseDTO.getDescription());
        return course;
    }

    public static CourseDTO mapToCourseDTO(Course course){
        return new CourseDTO(course.getCId(), course.getName(), course.getDescription());
    }
}
