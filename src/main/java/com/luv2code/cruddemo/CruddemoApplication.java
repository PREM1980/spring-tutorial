package com.luv2code.cruddemo;

import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.luv2code.cruddemo.dao.StudentDAO;
import com.luv2code.cruddemo.entity.Student;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(StudentDAO studentDAO) {
		return runner -> {
			// createStudent(studentDAO);
			// createMultipleStudents(studentDAO);
			readStudent(studentDAO);
			queryforStudents(studentDAO);
			queryforStudentsByLastName(studentDAO);
			updateStudent(studentDAO);

		};
	}

	private void updateStudent(StudentDAO studentDAO) {
		// retrieve student based on the id
		int id = 1;
		Student student = studentDAO.findById(id);
		System.out.println("Student found: " + student);
		
		// change the first name to scobby
		student.setFirstName("scobby");
		studentDAO.update(student);
		// update the student
		System.out.println("updated student - " +  student.getFirstName());
		// display the updated student
	}

	private void queryforStudentsByLastName(StudentDAO studentDAO) {
		System.out.println("Querying for students by last name");
		List<Student> theStudents = studentDAO.findByLastName("Doe");

		for (Student student : theStudents) {
			System.out.println(student);
		}

	}

	private void queryforStudents(StudentDAO studentDAO) {
		// TODO Auto-generated method stub
		System.out.println("Querying for students");

		List<Student> students = studentDAO.findAll();

		for (Student student : students) {
			System.out.println(student);
		}

	}

	private void readStudent(StudentDAO studentDAO) {
		System.out.println("Reading student");

		Student student_1 = new Student("Daffy", "Duck", "john@gmail.com");
		studentDAO.save(student_1);

		int id = student_1.getId();
		Student student_2 = studentDAO.findById(id);

		System.out.println("Student found: " + student_2);
		System.out.println("Reading student...Done");
	}

	private void createMultipleStudents(StudentDAO studentDAO) {

		System.out.println("Creating multiple students");

		Student student_1 = new Student("John", "Doe", "john@gmail.com");
		Student student_2 = new Student("Mary", "Public", "mary@gmail.com");
		Student student_3 = new Student("Bonita", "Applebum", "bonita@gmail.com");

		studentDAO.save(student_1);
		studentDAO.save(student_2);
		studentDAO.save(student_3);

		System.out.println("Creating multiple students...Done");

	}

	private void createStudent(StudentDAO studentDAO) {
		System.out.println("Creating student");

		Student student = new Student("John", "Doe", "john@gmail.com");
		studentDAO.save(student);

		System.out.println("Student created. Generated id: " + student.getId());

	}

}
