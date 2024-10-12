package net.coursemanagement.course_app.service.impl;

import net.coursemanagement.course_app.dto.TeacherDTO;
import net.coursemanagement.course_app.entity.Teacher;
import net.coursemanagement.course_app.mapper.TeacherMapper;
import net.coursemanagement.course_app.repository.TeacherRepository;
import net.coursemanagement.course_app.service.TeacherService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeacherServiceImpl implements TeacherService {
    public TeacherRepository teacherRepository;

    public TeacherServiceImpl(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @Override
    public TeacherDTO getTeacherById(Long tId) {
        Teacher teacher = teacherRepository.findById(tId)
                .orElseThrow(() -> new RuntimeException("Teacher does not exist"));
        return TeacherMapper.mapToTeacherDTO(teacher);
    }

    @Override
    public List<TeacherDTO> getAllTeachers() {
        List<Teacher> teachers = teacherRepository.findAll();
        return teachers.stream().map(TeacherMapper::mapToTeacherDTO).collect(Collectors.toList());
    }

    @Override
    public TeacherDTO addTeacher(TeacherDTO teacherDTO) {
        Teacher teacher = TeacherMapper.mapToTeacher(teacherDTO);
        Teacher savedTeacher = teacherRepository.save(teacher);
        return TeacherMapper.mapToTeacherDTO(savedTeacher);
    }

    @Override
    public TeacherDTO updateTeacher(Long tId, TeacherDTO teacherDTO) {
        Teacher existingTeacher = teacherRepository.findById(tId)
                .orElseThrow(() -> new RuntimeException("Teacher not found"));

        Teacher teacherToUpdate = TeacherMapper.mapToTeacher(teacherDTO);
        teacherToUpdate.setTId(existingTeacher.getTId());

        Teacher updatedTeacher = teacherRepository.save(teacherToUpdate);
        return TeacherMapper.mapToTeacherDTO(updatedTeacher);
    }

    @Override
    public void deleteTeacher(Long tId) {
        Teacher teacher = teacherRepository.findById(tId)
                .orElseThrow(() -> new RuntimeException("Teacher not found"));

        teacherRepository.delete(teacher);
    }


}
