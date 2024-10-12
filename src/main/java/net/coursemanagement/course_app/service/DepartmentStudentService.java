package net.coursemanagement.course_app.service;

import net.coursemanagement.course_app.dto.DepartmentStudentDTO;
import net.coursemanagement.course_app.dto.StudentDTO;
import net.coursemanagement.course_app.entity.Student;

import java.util.List;

public interface DepartmentStudentService {
    void mapStudentToDepartment(DepartmentStudentDTO departmentStudentDTO);
    List<StudentDTO> getAllStudentsFromDepartment(Long dId);
}
