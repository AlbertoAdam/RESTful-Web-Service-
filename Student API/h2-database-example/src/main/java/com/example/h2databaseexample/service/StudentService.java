package com.example.h2databaseexample.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.h2databaseexample.model.Student;
import com.example.h2databaseexample.repository.StudentRepository;

import jakarta.transaction.Transactional;

@Service
public class StudentService {
	
	@Autowired
	StudentRepository studentRepository;
	
	public List<Student> getAllStudent(){
		List<Student> students = new ArrayList<Student>();
		studentRepository.findAll().forEach(student -> students.add(student));
		return students;
	}
	
	//getting a specific record
	public Student getStudentById(int id) {
		return studentRepository.findById(id).get();
	}
	
	public void save(List<Student> students) {
		studentRepository.saveAll(students);
	}
	
	public void deleteStudent(int id) {
		boolean exists = studentRepository.existsById(id);
		if(!exists) {
			throw new IllegalStateException("student with id" + id + "does bot exists");
		}
		studentRepository.deleteById(id);
	}
	public void saveMoreStudents(List<Student> students) {
		studentRepository.saveAll(students);
	}

	public void addStudent(Student student) {
		Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());
		if(studentOptional.isPresent()) {
			throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "email taken");
		}
		studentRepository.save(student);
	}
	
	@Transactional
	public void updateStudent(Long studentId, String name, int age) {
		Student student = studentRepository.findStudentById(studentId);
		if(student.getName() == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Student with the id " + studentId + " does not exist");
		}
		if(name != null && name.length() > 0 && !Objects.equals(student.getName(), name));
			student.setName(name);
		if(age > 0 && age != student.getAge())
			student.setAge(age);
	}
	
	public void replaceStudent(Long studentId, Student newStudent) {
		List<Student> students = new ArrayList<Student>();
		studentRepository.findAll().forEach(stud -> students.add(stud));
		students.forEach(stud ->{
			System.out.println(stud.getId());
			if(stud.getId() == studentId) {
				System.out.println(students.indexOf(stud));
				students.set(students.indexOf(stud), newStudent);
			}
		});
		studentRepository.saveAll(students);
	}
}
