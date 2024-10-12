package net.coursemanagement.course_app.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Table(name = "course")
@Entity
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "cId")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cId;
    private String name;
    private String description;

    @ManyToMany
    @JoinTable(name = "student_course", joinColumns = @JoinColumn(name = "course_id", referencedColumnName = "cId"),
            inverseJoinColumns = @JoinColumn(name = "student_id", referencedColumnName = "sId"))
    private Set<Student> students;

    @ManyToMany
    @JsonIgnore
    @JoinTable(name = "teacher_course", joinColumns = @JoinColumn(name = "course_id", referencedColumnName = "cId"),
            inverseJoinColumns = @JoinColumn(name = "teacher_id", referencedColumnName = "tId"))
    private Set<Teacher> teachers;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @OneToMany(mappedBy = "course", fetch = FetchType.EAGER)
    private Set<Assignment> assignments;


    public Course(){
        this.students = new HashSet<>();
        this.teachers = new HashSet<>();
    }

    public Course(Long cId, String name, String description) {
        this.cId = cId;
        this.name = name;
        this.description = description;
    }
}
