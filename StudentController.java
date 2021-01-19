package com.springbootdemo.crud.restful.webservices.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springbootdemo.crud.restful.webservices.exception.ResourceNotFoundException;
import com.springbootdemo.crud.restful.webservices.model.Student;
import com.springbootdemo.crud.restful.webservices.repository.StudentRepository;

@RestController
@RequestMapping("/api/v1")
public class StudentController {
	
	@Autowired
	private StudentRepository studentrepository;
	
	
	//create get all student
	@GetMapping("/students")
	public List<Student> getAllStudent()
	{
		return studentrepository.findAll();
	}
	
	
	//create  student
	@PostMapping("/students")
	public Student createStudent(@Validated @RequestBody Student student)
	{
		return studentrepository.save(student);
	}
	
	
	//create get student by id
	@GetMapping("/students/{id}")
	public ResponseEntity<Student> getStudentById(@PathVariable(value = "id") long studentId) throws ResourceNotFoundException{
		Student student=studentrepository.findById(studentId).orElseThrow(() -> new ResourceNotFoundException("This id doesnot exists : "+studentId));
		return ResponseEntity.ok().body(student);
	}
	//update student
	@PutMapping("/students/{id}")
	public ResponseEntity<Student> updateStudent(@PathVariable(value = "id") long studentId , @RequestBody Student studentDetails) throws ResourceNotFoundException{
		Student student=studentrepository.findById(studentId).orElseThrow(() -> new ResourceNotFoundException("This id doesnot exists : "+studentId));
		student.setFirstName(studentDetails.getFirstName());
		student.setLastName(studentDetails.getLastName());
		student.setAddress(studentDetails.getAddress());
		studentrepository.save(student);
		return ResponseEntity.ok().body(student);
	}
	//delete student
	@DeleteMapping("/students/{id}")
	public ResponseEntity<?> deleteStudent(@PathVariable(value = "id") long studentId) throws ResourceNotFoundException {
		studentrepository.findById(studentId)
				.orElseThrow(() -> new ResourceNotFoundException("This id doesnot exists ::" + studentId));
		studentrepository.deleteById(studentId);
		return ResponseEntity.ok().build();
	}


}
