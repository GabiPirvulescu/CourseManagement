package net.coursemanagement.course_app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import net.coursemanagement.course_app.entity.Student;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
public class CourseDTO {
    private Long cId;
    private String name;
    private String description;
}
