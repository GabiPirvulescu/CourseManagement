package net.coursemanagement.course_app.mapper;

import net.coursemanagement.course_app.dto.DepartmentDTO;
import net.coursemanagement.course_app.entity.Course;
import net.coursemanagement.course_app.entity.Department;
import net.coursemanagement.course_app.entity.Student;
import net.coursemanagement.course_app.entity.Teacher;
import net.coursemanagement.course_app.repository.CourseRepository;
import net.coursemanagement.course_app.repository.StudentRepository;
import net.coursemanagement.course_app.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;
@Component
public class DepartmentMapper {

    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;

    public DepartmentMapper(CourseRepository courseRepository, StudentRepository studentRepository, TeacherRepository teacherRepository) {
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
    }

    public Department mapToDepartment(DepartmentDTO departmentDTO) {
        Department department = new Department();
        department.setDId(departmentDTO.getDId());
        department.setName(departmentDTO.getName());
        department.setDescription(departmentDTO.getDescription());

        if (departmentDTO.getCourses() != null) {
            Set<Course> courses = courseRepository.findAllById(departmentDTO.getCourses()).stream().collect(Collectors.toSet());
            department.setCourses(courses);
        }

        if (departmentDTO.getStudents() != null) {
            Set<Student> students = studentRepository.findAllById(departmentDTO.getStudents()).stream().collect(Collectors.toSet());
            department.setStudents(students);
        }

        if (departmentDTO.getTeachers() != null) {
            Set<Teacher> teachers = teacherRepository.findAllById(departmentDTO.getTeachers()).stream().collect(Collectors.toSet());
            department.setTeachers(teachers);
        }

        return department;
    }

    public DepartmentDTO mapToDepartmentDTO(Department department) {
        DepartmentDTO departmentDTO = new DepartmentDTO();
        departmentDTO.setDId(department.getDId());
        departmentDTO.setName(department.getName());
        departmentDTO.setDescription(department.getDescription());

        if (department.getCourses() != null) {
            Set<Long> courseIds = department.getCourses().stream().map(Course::getCId).collect(Collectors.toSet());
            departmentDTO.setCourses(courseIds);
        }

        if (department.getStudents() != null) {
            Set<Long> studentIds = department.getStudents().stream().map(Student::getSId).collect(Collectors.toSet());
            departmentDTO.setStudents(studentIds);
        }

        if (department.getTeachers() != null) {
            Set<Long> teacherIds = department.getTeachers().stream().map(Teacher::getTId).collect(Collectors.toSet());
            departmentDTO.setTeachers(teacherIds);
        }

        return departmentDTO;
    }
}
