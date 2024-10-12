package net.coursemanagement.course_app.repository;

import net.coursemanagement.course_app.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
}
