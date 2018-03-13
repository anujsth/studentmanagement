package com.itntraining.studentmanagement;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class StudentController {
	@Autowired
	private StudentRepositary studentRepositary;
//	@GetMapping("/students")
//	public Student getStudent() {
//		Student student = new Student();
//		student.setStudentId(1l);
//		student.setFirstName("Anuj");
//		student.setLastName("Shrestha");
//		student.setAddress("nuwakot");
//		return student;
//	}
	@GetMapping("/students/{firstName}")
	public String greet(@PathVariable String firstName) {
		return "hello" + firstName;
	}
	@PostMapping("/students")
	public ResponseEntity<?> saveStudents(@RequestBody Student student) {
	 System.out.println("First Name:" + student.getFirstName());
	 System.out.println("Last Name:" + student.getLastName());
	Student savedStudent =studentRepositary.save(student);
	 return ResponseEntity.ok(savedStudent);
	}
	@GetMapping("/students")
	public ResponseEntity<?> getAllStudents(){
		List<Student> studentList= studentRepositary.findAll();
		return ResponseEntity.ok(studentList);
	}
	@PutMapping("/students")
	@Transactional//autosave in database
	public ResponseEntity<?> updateStudet(@RequestBody Student student,@RequestParam Long studentId){
		Optional<Student> oldStudentOptional= studentRepositary.findById(studentId);
		oldStudentOptional.ifPresent(s->{
		s.setAddress(student.getAddress());
		s.setFirstName(student.getFirstName());
		s.setLastName(student.getLastName());
	});
	return ResponseEntity.ok("student update succesfully");
	}
@DeleteMapping("/students")
public ResponseEntity<?> deleteStudent(@RequestParam Long studentId){
	studentRepositary.findById(studentId).ifPresent(s->{		
	studentRepositary.delete(s);
	});
	return ResponseEntity.ok("deleted");
}
}
