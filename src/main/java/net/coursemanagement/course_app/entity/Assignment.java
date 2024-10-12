package net.coursemanagement.course_app.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
@Entity
@Table( name = "assignment")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "assignId")
public class Assignment {
    @Id
    @GeneratedValue ( strategy = GenerationType.IDENTITY)
    private Long assignId;
    private String name;
    private String description;
    private Date startDate;
    private Date endDate;
    private String file;

    @ManyToOne
    @JoinColumn( name = "course_id")
    private Course course;

    @OneToMany(mappedBy = "assignment")
    private Set<StudentAssignment> studentAssignments;

}
