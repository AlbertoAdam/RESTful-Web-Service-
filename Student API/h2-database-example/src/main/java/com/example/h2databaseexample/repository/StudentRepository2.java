package com.example.h2databaseexample.repository;

import java.util.Optional;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.h2databaseexample.model.Student;

public interface StudentRepository2 extends CrudRepository<Student, Integer>{
	
	@Query("SELECT s FROM Student s WHERE s.email = ?1")
	Optional<Student> findStudentByEmail(String email);
	
	@Query("SELECT s FROM Student s WHERE s.id = ?1")
	Student findStudentById(Long id);
}
