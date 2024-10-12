package net.coursemanagement.course_app.service.impl;

import net.coursemanagement.course_app.dto.CourseDTO;
import net.coursemanagement.course_app.dto.StudentDTO;
import net.coursemanagement.course_app.entity.Course;
import net.coursemanagement.course_app.mapper.CourseMapper;
import net.coursemanagement.course_app.mapper.StudentMapper;
import net.coursemanagement.course_app.repository.CourseRepository;
import net.coursemanagement.course_app.service.CourseService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {
    private CourseRepository courseRepository;

    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public CourseDTO getCourseById(Long cId) {
        Course course = courseRepository.findById(cId)
                .orElseThrow(() -> new RuntimeException("Course does not exist"));
        return CourseMapper.mapToCourseDTO(course);
    }

    @Override
    public CourseDTO createCourse(CourseDTO courseDTO) {
        Course course = CourseMapper.mapToCourse(courseDTO);
        Course savedCourse = courseRepository.save(course);
        return CourseMapper.mapToCourseDTO(savedCourse);

    }

    @Override
    public CourseDTO updateCourse(Long cId, CourseDTO courseDTO) {
        Course existingCourse = courseRepository.findById(cId)
                .orElseThrow(() -> new RuntimeException("Course does not exist"));

        Course courseToUpdate = CourseMapper.mapToCourse(courseDTO);
        courseToUpdate.setCId(existingCourse.getCId());

        Course updatedCourse = courseRepository.save(courseToUpdate);
        return CourseMapper.mapToCourseDTO(updatedCourse);
    }

    @Override
    public void deleteCourse(Long cId) {
        Course courseToDelete = courseRepository.findById(cId)
                .orElseThrow(() -> new RuntimeException("Course does not exist"));

        courseRepository.delete(courseToDelete);
    }

    @Override
    public List<CourseDTO> getAllCourses() {
        List<Course> courses = courseRepository.findAll();
        List<CourseDTO> coursesDTO =  courses.stream().map(
                CourseMapper::mapToCourseDTO).toList();
        System.out.println(coursesDTO);
        return coursesDTO;
    }

    @Override
    public List<StudentDTO> getAllStudentsForCourse(Long cId) {
        Course course = courseRepository.findById(cId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        List<StudentDTO> students = new ArrayList<>();
        course.getStudents().forEach(
                student -> {
                    StudentDTO studentDTO = new StudentDTO(
                            student.getSId(),
                            student.getName(),
                            student.getEmail()
                    );
                    students.add(studentDTO);
                }
        );
        return students;
    }


}
