package net.coursemanagement.course_app.service;

import net.coursemanagement.course_app.dto.DepartmentDTO;

import java.util.List;

public interface DepartmentService {
    DepartmentDTO createDepartment(DepartmentDTO departmentDTO);
    DepartmentDTO getDepartmentById(Long dId);
    List<DepartmentDTO> getAllDepartments();
    DepartmentDTO updateDepartment(Long dId, DepartmentDTO departmentDTO);
    void deleteDepartment(Long dId);
}
