package net.coursemanagement.course_app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StudentDTO {
    private Long sId;
    private String name;
    private String email;
}
