package net.coursemanagement.course_app.controller;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import net.coursemanagement.course_app.dto.AssignmentDTO;
import net.coursemanagement.course_app.dto.AssignmentUploadDTO;
import net.coursemanagement.course_app.dto.StudentAssignmentDTO;
import net.coursemanagement.course_app.entity.Assignment;
import net.coursemanagement.course_app.service.AssignmentService;
import net.coursemanagement.course_app.service.StudentService;
import org.springframework.expression.spel.ast.Assign;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("api/assignment")
public class AssignmentController {
    private AssignmentService assignmentService;
    private StudentService studentService;

    public AssignmentController(AssignmentService assignmentService, StudentService studentService) {
        this.assignmentService = assignmentService;
        this.studentService = studentService;
    }

    @GetMapping("/{cId}")
    public ResponseEntity<List<AssignmentDTO>> getAllAssignmentsForCourse(@PathVariable Long cId) {
        List<AssignmentDTO> assignments = assignmentService.getAllAssignmentForCourse(cId);
        return ResponseEntity.ok(assignments);
    }

    @GetMapping("/{assignId}")
    public ResponseEntity<AssignmentDTO> getAssignmentById(@PathVariable Long assignId) {
        AssignmentDTO assignmentDTO = assignmentService.getAssignmentById(assignId);
        return ResponseEntity.ok(assignmentDTO);

    }

    @PostMapping
    public ResponseEntity<AssignmentDTO> addNewAssignment(@RequestBody AssignmentDTO assignmentDTO) {
        AssignmentDTO savedAssignment = assignmentService.addAssignment(assignmentDTO);
        return new ResponseEntity<>(savedAssignment, HttpStatus.CREATED);
    }

    @PostMapping("/student")
    public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file, @RequestParam Long assignId, @RequestParam Long sId) {
        AssignmentUploadDTO assignmentUploadDTO = new AssignmentUploadDTO();
        assignmentUploadDTO.setAssignId(assignId);
        assignmentUploadDTO.setsId(sId);

        String fileLocation = assignmentService.storeUploadedAssignments(file);
        assignmentUploadDTO.setFile(fileLocation);
        assignmentService.uploadAssignmentDetails(assignmentUploadDTO);
        return ResponseEntity.ok("File uploaded successfully");

    }

    @PostMapping("{assignId}/marks")
    @ResponseBody
    public void assignGradeForAssignment(@RequestBody StudentAssignmentDTO studentAssignmentDTO) {
        assignmentService.assignGrade(studentAssignmentDTO);
    }

    @PostMapping("{assignId}/feedback")
    @ResponseBody
    public void assignFeedbackForAssignment(@RequestBody StudentAssignmentDTO studentAssignmentDTO) {
        assignmentService.addFeedback(studentAssignmentDTO);
    }

    @RequestMapping(value = "student-assignments/{id}/file", produces = {"application/json"})
    public @ResponseBody ResponseEntity getUploadedFile(@RequestParam(value = "fileName", required = false) String fileName) throws IOException {
        return uploadFile(fileName);
    }

    @RequestMapping(value = "materials/file", produces = {"application/json"})
    public @ResponseBody ResponseEntity getAssignmentFile(@RequestParam(value = "fileName", required = false) String fileName) throws IOException {

        return uploadFile(fileName);
    }


    @PutMapping("/{assignId}")
    public ResponseEntity<AssignmentDTO> updateAssignmentById(@PathVariable Long assignId, @RequestBody AssignmentDTO assignmentDTO) {
        AssignmentDTO assignmentToUpdate = assignmentService.updateAssignment(assignId, assignmentDTO);
        return ResponseEntity.ok(assignmentToUpdate);
    }

    @DeleteMapping("/{assignId}")
    public ResponseEntity<String> deleteAssignment(@PathVariable Long assignId) {
        assignmentService.deleteAssignment(assignId);
        return ResponseEntity.ok("Assignment deleted successfully");
    }

    private ResponseEntity uploadFile(@RequestParam(value = "fileName", required = false) String fileName) throws IOException {
        ResponseEntity respEntity = null;
        File result = new File(fileName);

        if (result.exists()) {
            InputStream inputStream = new FileInputStream(fileName);
            String type = result.toURL().openConnection().guessContentTypeFromName(fileName);

            byte[] out = IOUtils.toByteArray(inputStream);

            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.add("content-disposition", "attachment; filename=" + fileName);
            responseHeaders.add("Content-Type", type);

            respEntity = new ResponseEntity(out, responseHeaders, HttpStatus.OK);
        } else {
            respEntity = new ResponseEntity("File Not Found", HttpStatus.OK);
        }
        return respEntity;
    }


}

