package net.coursemanagement.course_app.service;

import net.coursemanagement.course_app.dto.AssignmentDTO;
import net.coursemanagement.course_app.dto.AssignmentUploadDTO;
import net.coursemanagement.course_app.dto.StudentAssignmentDTO;
import net.coursemanagement.course_app.entity.Assignment;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface AssignmentService {
    AssignmentDTO addAssignment(AssignmentDTO assignmentDTO);

    AssignmentDTO updateAssignment(Long assignId, AssignmentDTO assignmentDTO);
    void deleteAssignment(Long assignId);
    AssignmentDTO getAssignmentById(Long assignId);
    List<AssignmentDTO> getAllAssignmentForCourse(Long courseId);
    void uploadAssignmentMaterial(AssignmentUploadDTO assignmentUploadDTO);
    void assignGrade(StudentAssignmentDTO studentAssignmentDTO);
    void addFeedback(StudentAssignmentDTO studentAssignmentDTO);
    void uploadAssignmentDetails(AssignmentUploadDTO assignmentUploadDTO);
    String storeUploadedAssignments(MultipartFile file);
    String storeMaterialsForAssignments(MultipartFile file);

}
