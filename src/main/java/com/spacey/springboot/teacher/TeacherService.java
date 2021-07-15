package com.spacey.springboot.teacher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spacey.springboot.response.StatusResponse;

@Service
public class TeacherService {

	@Autowired
	TeacherRepository teacherRepository;

	public Teacher getTeacher(Long teacherId) {
		return teacherRepository.findOne(teacherId);
	}

	public Teacher createTeacher(Teacher teacher) {
		return teacherRepository.save(teacher);
	}

	public StatusResponse deleteTeacher(Long teacherId) {
		teacherRepository.delete(teacherId);
		return new StatusResponse("success");
	}
}
