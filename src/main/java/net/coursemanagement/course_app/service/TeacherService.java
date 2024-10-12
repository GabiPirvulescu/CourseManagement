package net.coursemanagement.course_app.service;

import net.coursemanagement.course_app.dto.TeacherDTO;

import java.util.List;

public interface TeacherService{
    TeacherDTO getTeacherById(Long tId);
    List<TeacherDTO> getAllTeachers();
    TeacherDTO addTeacher(TeacherDTO teacherDTO);
    TeacherDTO updateTeacher(Long tId,  TeacherDTO teacherDTO);
    void deleteTeacher(Long tId);

}
