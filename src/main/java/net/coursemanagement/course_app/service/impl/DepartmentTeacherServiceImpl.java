package net.coursemanagement.course_app.service.impl;

import net.coursemanagement.course_app.dto.DepartmentTeacherDTO;
import net.coursemanagement.course_app.dto.TeacherDTO;
import net.coursemanagement.course_app.entity.Department;
import net.coursemanagement.course_app.entity.Teacher;
import net.coursemanagement.course_app.mapper.TeacherMapper;
import net.coursemanagement.course_app.repository.DepartmentRepository;
import net.coursemanagement.course_app.repository.TeacherRepository;
import net.coursemanagement.course_app.service.DepartmentTeacherService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class DepartmentTeacherServiceImpl implements DepartmentTeacherService {

    public TeacherRepository teacherRepository;
    public DepartmentRepository departmentRepository;

    public DepartmentTeacherServiceImpl(TeacherRepository teacherRepository, DepartmentRepository departmentRepository) {
        this.teacherRepository = teacherRepository;
        this.departmentRepository = departmentRepository;
    }

    @Override
    public void mapTeacherToDepartment(DepartmentTeacherDTO departmentTeacherDTO) {
        Department department = departmentRepository.findById(departmentTeacherDTO.getdId())
                .orElseThrow(() -> new RuntimeException("Department not found"));
        Teacher teacher = teacherRepository.findById(departmentTeacherDTO.gettId())
                .orElseThrow(() -> new RuntimeException("Teacher not found"));

        teacher.setDepartment(department);
        department.getTeachers().add(teacher);

        departmentRepository.save(department);
        teacherRepository.save(teacher);
    }

    @Override
    public List<TeacherDTO> getAllTeachersFromDepartment(Long dId) {
        Department department = departmentRepository.findById(dId)
                .orElseThrow(() -> new RuntimeException("Teacher not found"));
        Set<Teacher> teacherSet = department.getTeachers();
        List<TeacherDTO> teacherDTOList = teacherSet.stream().map(
                TeacherMapper::mapToTeacherDTO).toList();
        return teacherDTOList;
    }


}
