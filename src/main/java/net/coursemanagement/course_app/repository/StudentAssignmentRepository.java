package net.coursemanagement.course_app.repository;

import net.coursemanagement.course_app.entity.StudentAssignment;
import net.coursemanagement.course_app.entity.StudentAssignmentPK;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentAssignmentRepository extends JpaRepository<StudentAssignment, StudentAssignmentPK> {
        List<StudentAssignment> findByStudent_sId(Long studentId);
}
