package net.coursemanagement.course_app.service;

import net.coursemanagement.course_app.dto.CourseDTO;
import net.coursemanagement.course_app.dto.DepartmentCourseDTO;
import net.coursemanagement.course_app.entity.Course;

import java.util.List;

public interface DepartmentCourseService {
    void mapCourseToDepartment(DepartmentCourseDTO  departmentCourseDTO);
    List<CourseDTO> getAllCoursesFromDepartment(Long dId);
}
