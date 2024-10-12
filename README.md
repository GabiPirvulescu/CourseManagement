# CourseManagement
A Spring Boot application designed to manage educational courses, assignments, students, and teachers efficiently. This system enables user management, course enrollment, and assignment tracking.

# Overview
This Course Management System is a Spring Boot application designed to facilitate the management of educational courses, assignments, students, and teachers. 

# Setting up database
- This project uses MySQL as a database. To set it up, you need to install MySQL and create a database called course_management ( or change the name inside application.properties )
- Make sure password, username, database name and port are correct inside application.properties file.

# Running the Project
- Configuration: Check src/main/resources for application properties. Adjust the database URL, username, and password as per your MySQL setup.
- Database Setup: Install MySQL and make a database. Then change inside application.settings the username, password and databse accordingly.

# Project structure
- src/main/java/net.coursemanagement.course_app is the base path for the project. Inside this path there are:
  * /controller: Contains all the controllers for handling incoming requests and managing the application flow.
  * /dto: Holds Data Transfer Objects (DTOs) that facilitate communication between the application and external data sources, such as the database and cookies.
  * /entity: Contains the entity classes that represent the database tables and define the structure of the data being managed.
  * /mapper: Includes mapping classes that convert between entity objects and DTOs, simplifying data transfer and ensuring a clean separation of data layers.
  * /repository: Holds repository interfaces for data access, providing CRUD operations for the entities.
  * /service: Contains service classes that encapsulate business logic and interact with both the repository and the controller layers.


# Features
- **User Management:** Supports user roles for students and teachers, enabling personalized access to course materials.
- **Course Management:** Create, update, and delete courses along with descriptions and assigned teachers.
- **Student Enrollment:** Allows students to enroll in courses and view their assignments.
- **Assignment Management:** Teachers can create and manage assignments associated with their courses.
- **Department Management:** Organize courses and staff into departments for better administration.

# Technologies Used
- **Java**: The primary programming language used for backend development.
- **Spring Boot**: A framework for building production-ready applications quickly.
- **Hibernate**: ORM framework for database interactions.
- **MySQL**: Relational database management system to store application data.
- **Maven**: Build automation tool used for managing project dependencies.

  # Future Improvements

- **User-Friendly GUI**: Plans to develop an intuitive graphical user interface (GUI) to enhance user experience and accessibility for non-technical users.
- **Quizzes and Tests**: Future implementation of quiz management features will enrich the educational experience by allowing teachers to create, manage, and grade quizzes and tests effectively.
