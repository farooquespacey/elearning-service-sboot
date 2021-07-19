package com.spacey.springboot.student.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spacey.springboot.response.StatusResponse;
import com.spacey.springboot.student.data.Student;
import com.spacey.springboot.student.data.StudentRepository;

@Service
public class StudentService {
	
	@Autowired
	StudentRepository studentRepository;

	public Student fetchStudent(Long studentId) {
		return studentRepository.findById(studentId).orElseThrow(() -> new RuntimeException("student not found"));
//		return studentRepository.findOne(studentId);
	}

	public Student createStudent(Student student) {
		return studentRepository.save(student);
	}

	public StatusResponse deleteStudent(Long studentId) {
		studentRepository.deleteById(studentId);
//		studentRepository.delete(studentId);
		return new StatusResponse("success");
	}

}
