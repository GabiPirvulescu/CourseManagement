package net.coursemanagement.course_app.mapper;

import net.coursemanagement.course_app.dto.TeacherDTO;
import net.coursemanagement.course_app.entity.Teacher;

public class TeacherMapper {
    public static TeacherDTO mapToTeacherDTO(Teacher teacher){
        TeacherDTO teacherDTO;
        teacherDTO = new TeacherDTO(
                teacher.getTId(),
                teacher.getName(),
                teacher.getEmail()
        );
        return teacherDTO;
    }

    public static Teacher mapToTeacher(TeacherDTO teacherDTO){
        Teacher teacher;
        teacher = new Teacher(
                teacherDTO.getTId(),
                teacherDTO.getName(),
                teacherDTO.getEmail()
        );
        return teacher;
    }

}
