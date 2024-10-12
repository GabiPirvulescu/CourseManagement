package net.coursemanagement.course_app.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "student_assignment")
public class StudentAssignment {
    @EmbeddedId
    private StudentAssignmentPK id = new StudentAssignmentPK();

    @ManyToOne
    @MapsId("student_id")
    @JoinColumn(name = "student_id", referencedColumnName = "sId")
    private Student student;

    @ManyToOne
    @MapsId("assignment_id")
    @JoinColumn(name = "assignment_id", referencedColumnName = "assignId")
    private Assignment assignment;

    @Column(name = "grade")
    private Long marks;

    @Column(name = "file")
    private String file;

    @Column(name = "feedback")
    private String feedback;

}
