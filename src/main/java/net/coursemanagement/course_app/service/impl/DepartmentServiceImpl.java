package net.coursemanagement.course_app.service.impl;

import net.coursemanagement.course_app.dto.DepartmentDTO;
import net.coursemanagement.course_app.entity.Department;
import net.coursemanagement.course_app.mapper.DepartmentMapper;
import net.coursemanagement.course_app.repository.DepartmentRepository;
import net.coursemanagement.course_app.service.DepartmentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    private DepartmentRepository departmentRepository;
    private DepartmentMapper departmentMapper;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository, DepartmentMapper departmentMapper) {
        this.departmentRepository = departmentRepository;
        this.departmentMapper = departmentMapper;
    }

    @Override
    public DepartmentDTO createDepartment(DepartmentDTO departmentDTO) {
        Department department = departmentMapper.mapToDepartment(departmentDTO);
        Department savedDepartment = departmentRepository.save(department);
        return departmentMapper.mapToDepartmentDTO(savedDepartment);
    }

    @Override
    public DepartmentDTO getDepartmentById(Long dId) {
        Department department = departmentRepository.findById(dId)
                .orElseThrow(() -> new RuntimeException("Department not found"));
        return departmentMapper.mapToDepartmentDTO(department);
    }

    @Override
    public List<DepartmentDTO> getAllDepartments() {
        List<Department> departments = departmentRepository.findAll();
        List<DepartmentDTO> departmentDTOS = departments.stream()
                .map(department -> departmentMapper.mapToDepartmentDTO(department))
                .collect(Collectors.toList());
        return departmentDTOS;
    }

    @Override
    public DepartmentDTO updateDepartment(Long dId, DepartmentDTO departmentDTO) {
        Department existingDepartment = departmentRepository.findById(dId)
                .orElseThrow(() -> new RuntimeException("Deparment not found."));

        Department departmentToUpdate = departmentMapper.mapToDepartment(departmentDTO);
        departmentToUpdate.setDId(existingDepartment.getDId());

        Department updatedDepartment = departmentRepository.save(departmentToUpdate);
        return departmentMapper.mapToDepartmentDTO(updatedDepartment);
    }

    @Override
    public void deleteDepartment(Long dId) {
        Department department = departmentRepository.findById(dId)
                .orElseThrow(() -> new RuntimeException("Department not found"));

        departmentRepository.delete(department);
    }


}
