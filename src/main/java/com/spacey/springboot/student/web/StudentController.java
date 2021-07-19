package com.spacey.springboot.student.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spacey.springboot.response.StatusResponse;
import com.spacey.springboot.student.business.StudentService;
import com.spacey.springboot.student.data.Student;

@RestController
@RequestMapping("/api/student")
public class StudentController {

	@Autowired
	StudentService studentService;
	
	@GetMapping("/{studentId}")
	public Student fetchStudent(@PathVariable("studentId") Long studentId) {
		return studentService.fetchStudent(studentId);
	}
	
	@PostMapping
	public Student createStudent(@RequestBody Student student) {
		return studentService.createStudent(student);
	}
	
	@DeleteMapping("/{studentId}")
	public StatusResponse deleteStudent(@PathVariable("studentId") Long studentId) {
		return studentService.deleteStudent(studentId);
	}
}
