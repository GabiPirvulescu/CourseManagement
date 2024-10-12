package net.coursemanagement.course_app.controller;

import net.coursemanagement.course_app.dto.CourseDTO;
import net.coursemanagement.course_app.dto.DepartmentDTO;
import net.coursemanagement.course_app.dto.StudentDTO;
import net.coursemanagement.course_app.dto.TeacherDTO;
import net.coursemanagement.course_app.entity.Student;
import net.coursemanagement.course_app.service.DepartmentCourseService;
import net.coursemanagement.course_app.service.DepartmentService;
import net.coursemanagement.course_app.service.DepartmentStudentService;
import net.coursemanagement.course_app.service.DepartmentTeacherService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.List;

@RestController
@RequestMapping("api/departments")
public class DepartmentController {
    private DepartmentService departmentService;
    private DepartmentStudentService departmentStudentService;
    private DepartmentCourseService departmentCourseService;
    private DepartmentTeacherService departmentTeacherService;

    public DepartmentController(DepartmentService departmentService, DepartmentStudentService departmentStudentService, DepartmentCourseService departmentCourseService, DepartmentTeacherService departmentTeacherService) {
        this.departmentService = departmentService;
        this.departmentStudentService = departmentStudentService;
        this.departmentCourseService = departmentCourseService;
        this.departmentTeacherService = departmentTeacherService;
    }

    @GetMapping("/{dId}")
    public ResponseEntity<DepartmentDTO> getDepartmentById(@PathVariable Long dId){
        DepartmentDTO departmentDTO = departmentService.getDepartmentById(dId);
        return ResponseEntity.ok(departmentDTO);
    }

    @GetMapping
    public ResponseEntity<List<DepartmentDTO>> getAllDepartments(){
        List<DepartmentDTO> departments = departmentService.getAllDepartments();
        return ResponseEntity.ok(departments);
    }

    //Create Department REST API
    @PostMapping
    public ResponseEntity<DepartmentDTO> createDepartment(@RequestBody DepartmentDTO departmentDTO){
        DepartmentDTO savedDepartment = departmentService.createDepartment(departmentDTO);
        return new ResponseEntity<>(savedDepartment, HttpStatus.CREATED);
    }

    @PutMapping("/{dId}")
    public ResponseEntity<DepartmentDTO> updateDepartment(@PathVariable Long dId, @RequestBody DepartmentDTO departmentDTO){
        DepartmentDTO departmentToUpdate = departmentService.updateDepartment(dId, departmentDTO);
        return ResponseEntity.ok(departmentToUpdate);
    }

    @DeleteMapping("/{dId}")
    public ResponseEntity<String> deleteDepartment(@PathVariable Long dId){
        departmentService.deleteDepartment(dId);
        return ResponseEntity.ok("Department deleted successfully");
    }

    @GetMapping("/{dId}/students")
    public ResponseEntity<List<StudentDTO>> getAllStudentsFromDepartment(@PathVariable Long dId){
        List<StudentDTO> students = departmentStudentService.getAllStudentsFromDepartment(dId);
        return ResponseEntity.ok(students);
    }

    @GetMapping("/{dId}/teachers")
    public ResponseEntity<List<TeacherDTO>> getAllTeachersFromDepartment(@PathVariable Long dId){
        List<TeacherDTO> teachers= departmentTeacherService.getAllTeachersFromDepartment(dId);
        return ResponseEntity.ok(teachers);
    }

    @GetMapping("/{dId}/courses")
    public ResponseEntity<List<CourseDTO>> getAllCoursesFromDepartment(@PathVariable Long dId){
        List<CourseDTO> courses = departmentCourseService.getAllCoursesFromDepartment(dId);
        return ResponseEntity.ok(courses);
    }

}
