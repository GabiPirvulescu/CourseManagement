package net.coursemanagement.course_app.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Table( name = "student")
@JsonIdentityInfo( generator = ObjectIdGenerators.PropertyGenerator.class, property =  "sId")
@Entity
public class Student {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long sId;
    private String name;
    private String email;

    @ManyToMany(mappedBy = "students")
    private Set<Course> courses;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @OneToMany (mappedBy = "student")
    private Set<StudentAssignment> studentAssignments;

    public Student(Long sId, String name, String email) {
        this.sId = sId;
        this.name = name;
        this.email = email;
    }
}
