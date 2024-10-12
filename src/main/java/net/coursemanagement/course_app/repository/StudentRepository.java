package net.coursemanagement.course_app.repository;

import net.coursemanagement.course_app.entity.Course;
import net.coursemanagement.course_app.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByCourses_cId(Long courseId);
}
