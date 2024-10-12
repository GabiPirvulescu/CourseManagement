package net.coursemanagement.course_app.repository;

import net.coursemanagement.course_app.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
