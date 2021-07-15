package com.spacey.springboot.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spacey.springboot.response.StatusResponse;

@Service
public class StudentService {
	
	@Autowired
	StudentRepository studentRepository;

	public Student fetchStudent(Long studentId) {
		return studentRepository.findOne(studentId);
	}

	public Student createStudent(Student student) {
		return studentRepository.save(student);
	}

	public StatusResponse deleteStudent(Long studentId) {
		studentRepository.delete(studentId);
		return new StatusResponse("success");
	}

}
