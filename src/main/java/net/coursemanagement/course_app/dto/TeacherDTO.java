package net.coursemanagement.course_app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class TeacherDTO {
    private Long tId;
    private String name;
    private String email;
}
