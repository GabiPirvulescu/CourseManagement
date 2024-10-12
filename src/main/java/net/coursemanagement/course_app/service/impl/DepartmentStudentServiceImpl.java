package net.coursemanagement.course_app.service.impl;

import net.coursemanagement.course_app.dto.DepartmentStudentDTO;
import net.coursemanagement.course_app.dto.StudentDTO;
import net.coursemanagement.course_app.entity.Department;
import net.coursemanagement.course_app.entity.Student;
import net.coursemanagement.course_app.mapper.StudentMapper;
import net.coursemanagement.course_app.repository.DepartmentRepository;
import net.coursemanagement.course_app.repository.StudentRepository;
import net.coursemanagement.course_app.service.DepartmentService;
import net.coursemanagement.course_app.service.DepartmentStudentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class DepartmentStudentServiceImpl implements DepartmentStudentService {

    public DepartmentRepository departmentRepository;
    public StudentRepository studentRepository;

    public DepartmentStudentServiceImpl(DepartmentRepository departmentRepository, StudentRepository studentRepository) {
        this.departmentRepository = departmentRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public void mapStudentToDepartment(DepartmentStudentDTO departmentStudentDTO) {
        Department department = departmentRepository.findById(departmentStudentDTO.getdId())
                .orElseThrow(() -> new RuntimeException("Department not found"));
        Student student = studentRepository.findById(departmentStudentDTO.getsId())
                .orElseThrow(() -> new RuntimeException("Student not found"));

        student.setDepartment(department);
        department.getStudents().add(student);

        departmentRepository.save(department);
        studentRepository.save(student);
    }

    @Override
    public List<StudentDTO> getAllStudentsFromDepartment(Long dId) {
        Department department = departmentRepository.findById(dId)
                .orElseThrow(() -> new RuntimeException("Department not found"));
        Set<Student> studentsSet = department.getStudents();
        List<StudentDTO> studentsList = studentsSet.stream().map(
                StudentMapper::mapToStudentDTO).collect(Collectors.toList());
        return studentsList;
    }


}
