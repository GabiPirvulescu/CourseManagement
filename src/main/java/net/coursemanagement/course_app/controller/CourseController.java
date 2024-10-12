package net.coursemanagement.course_app.controller;

import net.coursemanagement.course_app.dto.CourseDTO;
import net.coursemanagement.course_app.dto.DepartmentCourseDTO;
import net.coursemanagement.course_app.dto.StudentDTO;
import net.coursemanagement.course_app.entity.Course;
import net.coursemanagement.course_app.service.CourseService;
import net.coursemanagement.course_app.service.DepartmentCourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/courses")
public class CourseController {
    private CourseService courseService;
    private DepartmentCourseService departmentCourseService;

    public CourseController(CourseService courseService, DepartmentCourseService departmentCourseService) {
        this.courseService = courseService;
        this.departmentCourseService = departmentCourseService;
    }

    //Get Course By ID REST API
    @GetMapping("/{cId}")
    public ResponseEntity<CourseDTO> getCourseById(@PathVariable Long cId){
        CourseDTO courseDTO = courseService.getCourseById(cId);
        return ResponseEntity.ok(courseDTO);
    }

    //Get All Courses REST API
    @GetMapping
    public ResponseEntity<List<CourseDTO>> getAllCourses(){
        List<CourseDTO> courses = courseService.getAllCourses();
        return ResponseEntity.ok(courses);
    }

    //Get All Students from a Course REST API
    @GetMapping("/{cId}/students")
    public ResponseEntity<List<StudentDTO>> getAllStudentsForCourse(@PathVariable Long cId){
        List<StudentDTO> students = courseService.getAllStudentsForCourse(cId);
        return ResponseEntity.ok(students);
    }

    //Create Course REST API
    @PostMapping
    public ResponseEntity<CourseDTO> createCourse(@RequestBody CourseDTO courseDTO) {
        CourseDTO savedCourse = courseService.createCourse(courseDTO);
        return new ResponseEntity<>(savedCourse, HttpStatus.CREATED);
    }

    //Map Course To Department REST API
    @PutMapping("/map/department")
    public ResponseEntity<String> mapCourseToDepartment(@RequestBody DepartmentCourseDTO departmentCourseDTO){
        departmentCourseService.mapCourseToDepartment(departmentCourseDTO);
        return ResponseEntity.ok("Course mapped successfully");
    }

    //Update Course REST API
    @PutMapping("/{cId}")
    public ResponseEntity<CourseDTO> updateCourse(@PathVariable Long cId, @RequestBody CourseDTO courseDTO){
        CourseDTO courseToUpdate = courseService.updateCourse(cId, courseDTO);
        return ResponseEntity.ok(courseToUpdate);
    }

    //Delete Course REST API
    @DeleteMapping("/{cId}")
    public ResponseEntity<String> deleteCourse(@PathVariable Long cId){
        courseService.deleteCourse(cId);
        return ResponseEntity.ok("Course deleted successfully");
    }


}
