package net.coursemanagement.course_app.repository;

import net.coursemanagement.course_app.entity.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssignmentRepository extends JpaRepository<Assignment, Long> {
}
