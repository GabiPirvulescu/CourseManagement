package net.coursemanagement.course_app.service;

import net.coursemanagement.course_app.dto.DepartmentTeacherDTO;
import net.coursemanagement.course_app.dto.TeacherDTO;
import net.coursemanagement.course_app.entity.Teacher;

import java.util.List;

public interface DepartmentTeacherService {
    void mapTeacherToDepartment (DepartmentTeacherDTO departmentTeacherDTO);
    List<TeacherDTO> getAllTeachersFromDepartment(Long dId);
}
