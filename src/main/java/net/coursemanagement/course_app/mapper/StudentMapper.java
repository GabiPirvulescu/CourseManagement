package net.coursemanagement.course_app.mapper;

import net.coursemanagement.course_app.dto.StudentDTO;
import net.coursemanagement.course_app.entity.Student;

import java.util.Set;
import java.util.stream.Collectors;

public class StudentMapper {
    public static Student mapToStudent(StudentDTO studentDTO) {
        Student student;
        student = new Student(
                studentDTO.getSId(),
                studentDTO.getName(),
                studentDTO.getEmail()
        );
        return student;
    }

    public static StudentDTO mapToStudentDTO(Student student) {
        StudentDTO studentDTO;
        studentDTO = new StudentDTO(
                student.getSId(),
                student.getName(),
                student.getEmail()
        );
        return studentDTO;
    }

    public static Set<StudentDTO> mapToSetStudentDTO(Set<Student> students) {
        return students.stream()
                .map(StudentMapper::mapToStudentDTO)
                .collect(Collectors.toSet());
    }

    public static Set<Student> mapToSetStudent(Set<StudentDTO> studentsDTO) {
        return studentsDTO.stream()
                .map(StudentMapper::mapToStudent)
                .collect(Collectors.toSet());
    }
}
