package net.coursemanagement.course_app.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table (name = "department")
@JsonIdentityInfo( generator =  ObjectIdGenerators.PropertyGenerator.class,
            property = "dId")
public class Department {
    @Id
    @GeneratedValue ( strategy = GenerationType.IDENTITY)
    private Long dId;
    private String name;
    private String description;

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    private Set<Course> courses = new HashSet<>();

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    private Set<Student> students = new HashSet<>();

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    private Set<Teacher> teachers = new HashSet<>();

    public Department(Long dId, String name, String description, Set<Course> courses, Set<Student> students, Set<Teacher> teachers) {
        this.dId = dId;
        this.name = name;
        this.description = description;
        this.courses = courses;
        this.students = students;
        this.teachers = teachers;
    }
}
