package net.coursemanagement.course_app.service.impl;

import jakarta.persistence.EntityNotFoundException;
import net.coursemanagement.course_app.dto.AssignmentDTO;
import net.coursemanagement.course_app.dto.AssignmentUploadDTO;
import net.coursemanagement.course_app.dto.StudentAssignmentDTO;
import net.coursemanagement.course_app.entity.*;
import net.coursemanagement.course_app.mapper.AssignmentMapper;
import net.coursemanagement.course_app.repository.AssignmentRepository;
import net.coursemanagement.course_app.repository.CourseRepository;
import net.coursemanagement.course_app.repository.StudentAssignmentRepository;
import net.coursemanagement.course_app.repository.StudentRepository;
import net.coursemanagement.course_app.service.AssignmentService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AssignmentServiceImpl implements AssignmentService {
    private AssignmentRepository assignmentRepository;
    private StudentAssignmentRepository studentAssignmentRepository;
    private CourseRepository courseRepository;
    private StudentRepository studentRepository;

    public AssignmentServiceImpl(AssignmentRepository assignmentRepository, CourseRepository courseRepository,
                                 StudentRepository studentRepository, StudentAssignmentRepository studentAssignmentRepository) {
        this.assignmentRepository = assignmentRepository;
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
        this.studentAssignmentRepository = studentAssignmentRepository;
    }


    @Override
    public AssignmentDTO addAssignment(AssignmentDTO assignmentDTO) {
        Assignment assignment = AssignmentMapper.mapToAssignment(assignmentDTO);
        Course course = courseRepository.findById(assignmentDTO.getCourseId())
                .orElseThrow(() -> new RuntimeException("Course not found."));

        assignment.setCourse(course);
        course.getAssignments().add(assignment);

        courseRepository.saveAndFlush(course);
        Assignment savedAssignment = assignmentRepository.save(assignment);

        List<Student> students = studentRepository.findByCourses_cId(course.getCId());

        for (Student student : students) {
            StudentAssignment studentAssignment = new StudentAssignment();
            StudentAssignmentPK pk = new StudentAssignmentPK();
            pk.setStudentId(student.getSId());
            pk.setAssignmentId(savedAssignment.getAssignId());
            studentAssignment.setId(pk);

            studentAssignmentRepository.save(studentAssignment);
        }

        return AssignmentMapper.mapToAssignmentDTO(savedAssignment);
    }

    @Override
    public AssignmentDTO updateAssignment(Long assignId, AssignmentDTO assignmentDTO) {
        Assignment existingAssignment = assignmentRepository.findById(assignId)
                .orElseThrow(() -> new RuntimeException("Assignment not found"));
        Assignment assignmentToUpdate = AssignmentMapper.mapToAssignment(assignmentDTO);

        assignmentToUpdate.setAssignId(existingAssignment.getAssignId());

        Assignment updatedAssignment = assignmentRepository.save(assignmentToUpdate);
        return AssignmentMapper.mapToAssignmentDTO(updatedAssignment);
    }

    @Override
    public void deleteAssignment(Long assignId) {
        Assignment assignment = assignmentRepository.findById(assignId)
                .orElseThrow(() -> new RuntimeException("Assignment not found"));
        assignmentRepository.delete(assignment);
    }

    @Override
    public AssignmentDTO getAssignmentById(Long assignId) {
        Assignment assignment = assignmentRepository.findById(assignId)
                .orElseThrow(() -> new RuntimeException("Assignment not found"));
        return AssignmentMapper.mapToAssignmentDTO(assignment);
    }

    @Override
    public List<AssignmentDTO> getAllAssignmentForCourse(Long courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("course not found."));

        Set<Assignment> assignmentsSet = course.getAssignments();
        return assignmentsSet.stream().map(
                AssignmentMapper::mapToAssignmentDTO
        ).toList();
    }

    @Override
    public void uploadAssignmentMaterial(AssignmentUploadDTO assignmentUploadDTO) {
        Assignment assignment = assignmentRepository.findById(assignmentUploadDTO.getAssignId())
                .orElseThrow(() -> new RuntimeException("assignment not found"));
        assignment.setFile(assignmentUploadDTO.getFile());
        assignmentRepository.save(assignment);

    }

    @Override
    public void assignGrade(StudentAssignmentDTO studentAssignmentDTO) {
        StudentAssignmentPK studentAssignmentPK = new StudentAssignmentPK();

        studentAssignmentPK.setAssignmentId(studentAssignmentDTO.getAssignId());
        studentAssignmentPK.setStudentId(studentAssignmentDTO.getStudentId());

        System.out.println("Looking for StudentAssignment with PK: " + studentAssignmentPK);

        StudentAssignment studentAssignment = studentAssignmentRepository.findById(studentAssignmentPK)
                .orElseThrow(() -> new RuntimeException("student-assign not found"));

        studentAssignment.setMarks(studentAssignmentDTO.getMarks());
        studentAssignmentRepository.save(studentAssignment);

    }

    @Override
    public void addFeedback(StudentAssignmentDTO studentAssignmentDTO) {
        StudentAssignmentPK studentAssignmentPK = new StudentAssignmentPK();
        studentAssignmentPK.setAssignmentId(studentAssignmentDTO.getAssignId());
        studentAssignmentPK.setStudentId(studentAssignmentDTO.getStudentId());

        Optional<StudentAssignment> studentAssignmentOptional = studentAssignmentRepository.findById(studentAssignmentPK);

        if (studentAssignmentOptional.isPresent()) {
            StudentAssignment studentAssignment = studentAssignmentOptional.get();
            studentAssignment.setFeedback(studentAssignmentDTO.getFeedback());

            studentAssignmentRepository.save(studentAssignment);
        } else {
            throw new EntityNotFoundException("StudentAssignment not found");
        }
    }

    @Override
    public void uploadAssignmentDetails(AssignmentUploadDTO assignmentUploadDTO) {
        Optional<Assignment> assignment = assignmentRepository.findById(assignmentUploadDTO.getAssignId());
        Optional<Student> student = studentRepository.findById(assignmentUploadDTO.getsId());

        if (assignment.isPresent() && student.isPresent()) {
            StudentAssignment studentAssignment = new StudentAssignment();
            studentAssignment.setStudent(student.get());
            studentAssignment.setAssignment(assignment.get());
            studentAssignment.setFile(assignmentUploadDTO.getFile());
            studentAssignmentRepository.save(studentAssignment);
            student.get().getStudentAssignments().add(studentAssignment);
            studentRepository.save(student.get());
        }
    }

    @Override
    public String storeUploadedAssignments(MultipartFile file) {
        try {
            byte[] bytes = file.getBytes();
            Path path = Paths.get("src/main/java/net.coursemanagement.course_app/assignments", file.getOriginalFilename());
            Files.write(path, bytes);
            return path.toString();

        } catch (Exception e) {
            throw new RuntimeException("FAIL!", e);
        }
    }

    @Override
    public String storeMaterialsForAssignments(MultipartFile file) {
        try {
            byte[] bytes = file.getBytes();
            Path path = Paths.get("src/main/java/net.coursemanagement.course_app/files", file.getOriginalFilename());
            Files.write(path, bytes);
            return path.toString();
        } catch (Exception e) {
            throw new RuntimeException("FAIL!", e);
        }
    }


}
