package net.coursemanagement.course_app.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table( name = "teacher")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property =  "tId")

public class Teacher {
    @Id
    @GeneratedValue ( strategy = GenerationType.IDENTITY)
    private Long tId;
    private String name;
    private String email;

    @ManyToMany(mappedBy = "teachers")
    private List<Course> courses;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    public Teacher(Long tId, String name, String email) {
        this.tId = tId;
        this.name = name;
        this.email = email;
    }
}
