package net.coursemanagement.course_app.service;

import net.coursemanagement.course_app.dto.AssignmentDTO;
import net.coursemanagement.course_app.dto.CourseDTO;
import net.coursemanagement.course_app.dto.EnrollmentDTO;
import net.coursemanagement.course_app.dto.StudentDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

public interface StudentService {
    StudentDTO getStudentById(Long studentId);
    Set<StudentDTO> getAllStudents();
    StudentDTO createStudent(StudentDTO studentDTO);
    StudentDTO updateStudent(Long sId, StudentDTO studentDTO);
    void deleteStudent(Long studentId);
    List<CourseDTO> getAllCoursesForStudent(Long sId);
    List<AssignmentDTO> getAllAssignmentsForStudent(Long sId);
    void enrollStudent(EnrollmentDTO enrollmentDTO);
    void unEnrollStudent(EnrollmentDTO enrollmentDTO);
}
