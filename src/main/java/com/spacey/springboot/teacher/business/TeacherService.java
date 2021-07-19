package com.spacey.springboot.teacher.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spacey.springboot.response.StatusResponse;
import com.spacey.springboot.teacher.data.Teacher;
import com.spacey.springboot.teacher.data.TeacherNotFoundException;
import com.spacey.springboot.teacher.data.TeacherRepository;

@Service
public class TeacherService {

	@Autowired
	TeacherRepository teacherRepository;

	public Teacher getTeacher(Long teacherId) {
		try {
			return teacherRepository.findById(teacherId).orElseThrow(() -> new RuntimeException());	
//			return teacherRepository.findOne(teacherId);
		} catch(Exception e) {
			throw new TeacherNotFoundException("teacher id not found");
		}
		
	}
	
	public Teacher getTeacherByName(String firstName, String lastName) {
		return teacherRepository.findByFirstNameAndLastName(firstName, lastName).orElse(null);
	}

	public Teacher createTeacher(Teacher teacher) {
		return teacherRepository.save(teacher);
	}

	public StatusResponse deleteTeacher(Long teacherId) {
		try {
			teacherRepository.deleteById(teacherId);
//			teacherRepository.delete(teacherId);
		} catch (Exception e) {
			throw new TeacherNotFoundException("teacher id not found, " + e.getMessage());
		}
		return new StatusResponse("success");
	}

	public List<Teacher> getAllTeachers() {
		return teacherRepository.findAll();
	}
}
