package com.example.h2databaseexample.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.h2databaseexample.model.Student;
import com.example.h2databaseexample.service.StudentService;

@RestController
public class StudentController {
	
	@Autowired  
	StudentService studentService;
	
	@GetMapping("/students")
	private List<Student> getAllStudent(){
		return studentService.getAllStudent();
	}
	
	@GetMapping("/student/{id}")
	private Student getStudent(@PathVariable("id") int id) {
		return studentService.getStudentById(id);
	}
	
	@DeleteMapping("/student/{id}")
	private void deleteStudent(@PathVariable("id") int id) {
		studentService.deleteStudent(id);
	}
	
	@PostMapping("/student")  
	private void saveStudent(@RequestBody List<Student> students)   
	{  
		studentService.save(students);  
	}
	
	@PostMapping("/addstudents")
	private void saveMoreStudents(@RequestBody List<Student> students) {
		studentService.saveMoreStudents(students);
	}
	
	@PostMapping("/adaugaStudent")
	public void registerNewStudent(@RequestBody Student student) {
		studentService.addStudent(student);
	}
	
	@PatchMapping("student/{id}")
	public void updateStudent(@PathVariable("id") long id, @RequestParam String name, @RequestParam int age) {
		studentService.updateStudent(id, name, age);
	}
	
	@PutMapping("student/{id}")
	public void replaceStudent(@PathVariable("id") long id, @RequestBody Student student) {
		studentService.replaceStudent(id, student);
	}
}
