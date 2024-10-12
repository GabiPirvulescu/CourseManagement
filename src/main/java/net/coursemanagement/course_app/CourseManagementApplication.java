package net.coursemanagement.course_app;

import net.coursemanagement.course_app.dto.AssignmentDTO;
import net.coursemanagement.course_app.entity.*;
import net.coursemanagement.course_app.repository.*;
import net.coursemanagement.course_app.service.AssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootApplication
public class CourseManagementApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(CourseManagementApplication.class, args);
	}

	@Autowired
	StudentRepository studentRepository;
	@Autowired
	CourseRepository courseRepository;
	@Autowired
	TeacherRepository teacherRepository;
	@Autowired
	StudentAssignmentRepository studentAssignmentRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	DepartmentRepository departmentRepository;
	@Autowired
	AssignmentService assignmentService;

	@Override
	public void run(String... strings) throws Exception{
		Course course = new Course();
		course.setName("Operating systems");

		Student student = new Student();
		student.setName("John");
		student.setEmail("john@gmail.com");
		course.getStudents().add(studentRepository.save(student));

		Student student1 = new Student();
		student1.setName("Maria");
		student1.setEmail("maria@gmail.com");
		course.getStudents().add(studentRepository.save(student1));

		User user1 = new User();
		user1.setEmail(student.getEmail());
		user1.setRole("3");
		user1.setPassword("123");
		userRepository.save(user1);

		User user2 = new User();
		user2.setEmail(student1.getEmail());
		user2.setRole("3");
		user2.setPassword("123");
		userRepository.save(user2);

		Course course2 = new Course();
		course2.setName("Software engineering I");
		course2.setDescription("Difficult, I module");
		courseRepository.save(course2);

		Course course3 = new Course();
		course3.setName("Software engineering II");
		course3.setDescription("Difficult, II module");
		courseRepository.save(course3);

		Teacher teacher = new Teacher();
		teacher.setName("Adrian");
		teacher.setEmail("adrian@gmail.com");
		teacherRepository.save(teacher);

		Teacher teacher1 = new Teacher();
		teacher1.setName("Alex");
		teacher.setEmail("alex@gmail.com");
		teacherRepository.save(teacher1);

		Department department = new Department();
		department.setName("Computer Science");
		departmentRepository.save(department);
		department.getStudents().add(student);
		department.getStudents().add(student1);
		department.getTeachers().add(teacher1);
		department.getTeachers().add(teacher);
		department.getCourses().add(course);
		department.getCourses().add(course2);
		department.getCourses().add(course3);

		User userT = new User();
		userT.setEmail(teacher.getEmail());
		userT.setRole("2");
		userT.setPassword("123");
		userRepository.save(userT);

		courseRepository.save(course);

		String inputString = "10-12-2024";
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		Date endDate = dateFormat.parse(inputString);

		AssignmentDTO assignment = new AssignmentDTO();
		assignment.setCourseId(course.getCId());
		assignment.setDescription("test assignment");
		assignment.setEndDate(endDate);
		assignmentService.addAssignment(assignment);

		AssignmentDTO assignment2 = new AssignmentDTO();
		assignment2.setCourseId(course2.getCId());
		assignment2.setDescription("test assignment2");
		assignment2.setEndDate(endDate);
		assignmentService.addAssignment(assignment2);

	}


}
