package net.coursemanagement.course_app.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class StudentAssignmentPK implements Serializable {
    @Column(name = "student_id")
    private Long studentId;

    @Column(name = "assignment_id")
    private Long assignmentId;

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getAssignmentId() {
        return assignmentId;
    }

    public void setAssignmentId(Long assignmentId) {
        this.assignmentId = assignmentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentAssignmentPK that = (StudentAssignmentPK) o;
        return Objects.equals(studentId, that.studentId) &&
                Objects.equals(assignmentId, that.assignmentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, assignmentId);
    }

    @Override
    public String toString() {
        return "StudentAssignmentPK{" +
                "studentId=" + studentId +
                ", assignmentId=" + assignmentId +
                '}';
    }
}


