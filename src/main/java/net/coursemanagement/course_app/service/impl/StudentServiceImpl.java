package net.coursemanagement.course_app.service.impl;

import net.coursemanagement.course_app.dto.AssignmentDTO;
import net.coursemanagement.course_app.dto.CourseDTO;
import net.coursemanagement.course_app.dto.EnrollmentDTO;
import net.coursemanagement.course_app.dto.StudentDTO;
import net.coursemanagement.course_app.entity.Course;
import net.coursemanagement.course_app.entity.Student;
import net.coursemanagement.course_app.entity.StudentAssignment;
import net.coursemanagement.course_app.entity.User;
import net.coursemanagement.course_app.mapper.AssignmentMapper;
import net.coursemanagement.course_app.mapper.StudentMapper;
import net.coursemanagement.course_app.repository.CourseRepository;
import net.coursemanagement.course_app.repository.StudentAssignmentRepository;
import net.coursemanagement.course_app.repository.StudentRepository;
import net.coursemanagement.course_app.repository.UserRepository;
import net.coursemanagement.course_app.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {
    private StudentRepository studentRepository;
    private UserRepository userRepository;
    private CourseRepository courseRepository;
    private StudentAssignmentRepository studentAssignmentRepository;

    public StudentServiceImpl(StudentRepository studentRepository, UserRepository userRepository
                              , CourseRepository courseRepository, StudentAssignmentRepository studentAssignmentRepository) {
        this.studentRepository = studentRepository;
        this.userRepository = userRepository;
        this.courseRepository = courseRepository;
        this.studentAssignmentRepository = studentAssignmentRepository;
    }

    @Override
    public StudentDTO getStudentById(Long studentId){
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student does not exist"));
        return StudentMapper.mapToStudentDTO(student);
    }

    @Override
    public Set<StudentDTO> getAllStudents() {
        Set<Student> students = studentRepository.findAll().stream().collect(Collectors.toSet());
        Set<StudentDTO> studentsDTO = students.stream()
                .map(StudentMapper::mapToStudentDTO)
                .collect(Collectors.toSet());

        System.out.println(studentsDTO);
        return studentsDTO;
    }


    @Override
    public StudentDTO createStudent(StudentDTO studentDTO) {
        Student student = StudentMapper.mapToStudent(studentDTO);
        Student savedStudent = studentRepository.save(student);

        User newUser = new User();
        newUser.setEmail(student.getEmail());
        newUser.setPassword("123");
        newUser.setRole("2");
        userRepository.save(newUser);

        return StudentMapper.mapToStudentDTO(savedStudent);
    }

    @Override
    public StudentDTO updateStudent(Long sId, StudentDTO studentDTO) {
            Student updatedStudent = studentRepository.findById(sId)
                    .orElseThrow(() -> new RuntimeException("Student does not exist"));

            updatedStudent.setName(studentDTO.getName());
            updatedStudent.setEmail(studentDTO.getEmail());
            studentRepository.save(updatedStudent);
            return StudentMapper.mapToStudentDTO(updatedStudent);
    }

    @Override
    public void deleteStudent(Long studentId) {
        Student updatedStudent = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student does not exist"));
        studentRepository.deleteById(studentId);
        userRepository.deleteById(studentId);
    }

    @Override
    public List<CourseDTO> getAllCoursesForStudent(Long sId) {
        Student student = studentRepository.findById(sId)
                .orElseThrow(() -> new RuntimeException("Student does not exist"));

        List<CourseDTO> courses = new ArrayList<>();
        student.getCourses().forEach(
                course -> {
                    CourseDTO courseDTO = new CourseDTO(
                            course.getCId(),
                            course.getName(),
                            course.getDescription()
                    );
                    courses.add(courseDTO);
                }
        );
        return courses;
    }

    @Override
    public List<AssignmentDTO> getAllAssignmentsForStudent(Long sId) {
        List<StudentAssignment> studentAssignments = studentAssignmentRepository.findByStudent_sId(sId);

        return studentAssignments.stream().map(
                studentAssignment -> AssignmentMapper.mapToAssignmentDTO(studentAssignment.getAssignment())
        ).collect(Collectors.toList());

    }

    @Override
    public void enrollStudent(EnrollmentDTO enrollmentDTO) {
        Student student = studentRepository.findById(enrollmentDTO.getsId())
                .orElseThrow(() -> new RuntimeException("Student not found"));
        Course course = courseRepository.findById(enrollmentDTO.getcId())
                .orElseThrow( () -> new RuntimeException("Course not found"));

        student.getCourses().add(course);
        course.getStudents().add(student);
        courseRepository.save(course);
        studentRepository.save(student);
    }

    @Override
    public void unEnrollStudent(EnrollmentDTO enrollmentDTO) {
        Student student = studentRepository.findById(enrollmentDTO.getsId())
                .orElseThrow(() -> new RuntimeException("Student not found"));
        Course course = courseRepository.findById(enrollmentDTO.getcId())
                .orElseThrow( () -> new RuntimeException("Course not found"));

        student.getCourses().remove(course);
        course.getStudents().remove(student);

        courseRepository.save(course);
        studentRepository.save(student);
    }


}
