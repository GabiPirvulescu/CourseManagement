package net.coursemanagement.course_app.service.impl;

import net.coursemanagement.course_app.dto.CourseDTO;
import net.coursemanagement.course_app.dto.DepartmentCourseDTO;
import net.coursemanagement.course_app.entity.Course;
import net.coursemanagement.course_app.entity.Department;
import net.coursemanagement.course_app.mapper.CourseMapper;
import net.coursemanagement.course_app.repository.CourseRepository;
import net.coursemanagement.course_app.repository.DepartmentRepository;
import net.coursemanagement.course_app.service.DepartmentCourseService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class DepartmentCourseServiceImpl implements DepartmentCourseService {

    public DepartmentRepository departmentRepository;
    public CourseRepository courseRepository;

    public DepartmentCourseServiceImpl(DepartmentRepository departmentRepository, CourseRepository courseRepository) {
        this.departmentRepository = departmentRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public void mapCourseToDepartment(DepartmentCourseDTO departmentCourseDTO) {
        Department department = departmentRepository.findById(departmentCourseDTO.getdId())
                .orElseThrow(() -> new RuntimeException("Department not found"));
        Course course =  courseRepository.findById(departmentCourseDTO.getcId())
                .orElseThrow(() -> new RuntimeException("Course not found"));

        course.setDepartment(department);
        department.getCourses().add(course);

        departmentRepository.save(department);
        courseRepository.save(course);
    }

    @Override
    public List<CourseDTO> getAllCoursesFromDepartment(Long dId) {
        Department department = departmentRepository.findById(dId)
                .orElseThrow( () -> new RuntimeException("Department not found"));
        Set<Course> courseSet = department.getCourses();
        List<CourseDTO> courses = courseSet.stream().map(
                CourseMapper::mapToCourseDTO).toList();
        return courses;
    }
}
