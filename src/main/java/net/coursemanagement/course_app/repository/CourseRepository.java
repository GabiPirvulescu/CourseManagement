package net.coursemanagement.course_app.repository;

import net.coursemanagement.course_app.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
