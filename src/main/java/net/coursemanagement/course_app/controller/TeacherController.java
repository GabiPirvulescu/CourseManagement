package net.coursemanagement.course_app.controller;

import net.coursemanagement.course_app.dto.DepartmentTeacherDTO;
import net.coursemanagement.course_app.dto.TeacherDTO;
import net.coursemanagement.course_app.entity.Teacher;
import net.coursemanagement.course_app.mapper.TeacherMapper;
import net.coursemanagement.course_app.repository.TeacherRepository;
import net.coursemanagement.course_app.service.DepartmentTeacherService;
import net.coursemanagement.course_app.service.TeacherService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.ResourceBundle;

@RestController
@RequestMapping("api/teachers")
public class TeacherController {
    private TeacherService teacherService;
    private DepartmentTeacherService departmentTeacherService;

    public TeacherController(TeacherService teacherService, DepartmentTeacherService departmentTeacherService) {
        this.teacherService = teacherService;
        this.departmentTeacherService = departmentTeacherService;
    }

    //Get a Teacher By ID REST API
    @GetMapping("/{tId}")
    public ResponseEntity<TeacherDTO> getTeacherById(@PathVariable Long tId) {
        TeacherDTO teacherDTO = teacherService.getTeacherById(tId);
        return ResponseEntity.ok(teacherDTO);
    }

    //Get All Teachers REST API
    @GetMapping
    public ResponseEntity<List<TeacherDTO>> getAllTeachers(){
        List<TeacherDTO> teachers = teacherService.getAllTeachers();
        return ResponseEntity.ok(teachers);
    }

    //Create Teacher REST API
    @PostMapping
    public ResponseEntity<TeacherDTO> addTeacher(@RequestBody TeacherDTO teacherDTO){
        return new ResponseEntity<>(teacherService.addTeacher(teacherDTO), HttpStatus.CREATED);
    }

    //Update Teacher REST API
    @PutMapping("/{tId}")
    public ResponseEntity<TeacherDTO> updateTeacher(@PathVariable Long tId, @RequestBody TeacherDTO teacherDTO){
        TeacherDTO teacherToUpdate = teacherService.updateTeacher(tId,teacherDTO);
        return ResponseEntity.ok(teacherToUpdate);
    }

    //Delete Teacher REST API
    @DeleteMapping("/{tId}")
    public ResponseEntity<String> deleteTeacher(@PathVariable Long tId){
        teacherService.deleteTeacher(tId);
        return ResponseEntity.ok("Teacher deleted successfully");
    }

    @PutMapping("/map/department")
    public ResponseEntity<String> mapTeacherToDepartment(@RequestBody DepartmentTeacherDTO departmentTeacherDTO){
       departmentTeacherService.mapTeacherToDepartment(departmentTeacherDTO);
        return ResponseEntity.ok("Teacher mapped successfully");
    }




}
