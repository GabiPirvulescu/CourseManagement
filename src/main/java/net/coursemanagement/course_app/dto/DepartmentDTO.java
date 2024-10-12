package net.coursemanagement.course_app.dto;

import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class DepartmentDTO {
    private Long dId;
    private String name;
    private String description;
    private Set<Long> courses;
    private Set<Long> students;
    private Set<Long> teachers;

    public DepartmentDTO(Long dId, String name, String description, Set<Long> courses, Set<Long> students, Set<Long> teachers) {
        this.dId = dId;
        this.name = name;
        this.description = description;
        this.courses = courses;
        this.students = students;
        this.teachers = teachers;
    }

    public DepartmentDTO() {
    }
}
