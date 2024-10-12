package net.coursemanagement.course_app.repository;

import net.coursemanagement.course_app.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
