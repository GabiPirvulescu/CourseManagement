package net.coursemanagement.course_app.controller;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import net.coursemanagement.course_app.dto.*;
import net.coursemanagement.course_app.entity.Student;
import net.coursemanagement.course_app.service.DepartmentStudentService;
import net.coursemanagement.course_app.service.StudentService;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("api/students")
public class StudentController {

    private StudentService studentService;
    private DepartmentStudentService departmentStudentService;

    public StudentController(StudentService studentService, DepartmentStudentService departmentStudentService) {
        this.studentService = studentService;
        this.departmentStudentService = departmentStudentService;
    }

    //Get Student REST API
    @GetMapping("/{sId}")
    public ResponseEntity<StudentDTO> getStudentById(@PathVariable Long sId){
        StudentDTO studentDTO = studentService.getStudentById(sId);
        return ResponseEntity.ok(studentDTO);
    }

    //Get All Students REST API
    @GetMapping
    public ResponseEntity<Set<StudentDTO>> getAllStudents(){
        Set<StudentDTO> students = studentService.getAllStudents();
        return ResponseEntity.ok(students);
    }

    //Get All Courses for Student REST API
    @GetMapping("/{sId}/courses")
    public ResponseEntity<List<CourseDTO>> getAllCoursesForStudent(@PathVariable Long sId){
        List<CourseDTO> courses = studentService.getAllCoursesForStudent(sId);
        return ResponseEntity.ok(courses);
    }

    @GetMapping("/{sId}/assignments")
    public ResponseEntity<List<AssignmentDTO>> getAllAssignmentsForStudent(@PathVariable Long sId){
        List<AssignmentDTO> studentAssignments = studentService.getAllAssignmentsForStudent(sId);
        return ResponseEntity.ok(studentAssignments);
    }

    //Add Student REST API
    @PostMapping
    public ResponseEntity<StudentDTO> createStudent(@RequestBody StudentDTO studentDTO){
        return new ResponseEntity<>(studentService.createStudent(studentDTO), HttpStatus.CREATED);
    }

    //Enroll Student in a Course REST API
    @PostMapping("/enroll")
    public ResponseEntity<String> enrollStudent(@RequestBody EnrollmentDTO enrollmentDTO){
        studentService.enrollStudent(enrollmentDTO);
        return ResponseEntity.ok("Student enrolled successfully");
    }

    @DeleteMapping("/unenroll")
    public ResponseEntity<String> unEnrollStudent(@RequestBody EnrollmentDTO enrollmentDTO){
        studentService.unEnrollStudent(enrollmentDTO);
        return ResponseEntity.ok("Student unenrolled successfully");
    }

    @DeleteMapping("/{sId}/delete")
    public ResponseEntity<String> deleteStudent(@PathVariable Long sId){
        studentService.deleteStudent(sId);
        return ResponseEntity.ok("Student deleted successfully");
    }

    //Update Student REST API
    @PutMapping("/{sId}")
    public ResponseEntity<StudentDTO> updateStudent(@PathVariable Long sId, @RequestBody StudentDTO studentDTO){
        StudentDTO updatedStudent = studentService.updateStudent(sId, studentDTO);
        return ResponseEntity.ok(updatedStudent);
    }

    //Map Student to Department REST API
    @PutMapping("/map/department")
    public ResponseEntity<String> mapStudentToDepartment(@RequestBody DepartmentStudentDTO departmentStudentDTO){
        departmentStudentService.mapStudentToDepartment(departmentStudentDTO);
        return ResponseEntity.ok("Student mapped successfully");
    }


}
