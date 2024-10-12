package net.coursemanagement.course_app.mapper;

import net.coursemanagement.course_app.dto.AssignmentDTO;
import net.coursemanagement.course_app.entity.Assignment;

public class AssignmentMapper {
    public static Assignment mapToAssignment(AssignmentDTO assignmentDTO) {
        Assignment assignment = new Assignment();

        assignment.setAssignId(assignmentDTO.getAssignId());
        assignment.setName(assignmentDTO.getName());
        assignment.setDescription(assignmentDTO.getDescription());
        assignment.setStartDate(assignmentDTO.getStartDate());
        assignment.setEndDate(assignmentDTO.getEndDate());
        assignment.setFile(assignment.getFile());

        return assignment;
    }

    public static AssignmentDTO mapToAssignmentDTO(Assignment assignment) {
        AssignmentDTO assignmentDTO = new AssignmentDTO();

        assignmentDTO.setAssignId(assignment.getAssignId());
        assignmentDTO.setName(assignment.getName());
        assignmentDTO.setDescription(assignment.getDescription());
        assignmentDTO.setStartDate(assignment.getStartDate());
        assignmentDTO.setEndDate(assignment.getEndDate());
        assignmentDTO.setCourseId(assignment.getCourse().getCId());

        return assignmentDTO;
    }
}
