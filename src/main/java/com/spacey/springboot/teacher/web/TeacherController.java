package com.spacey.springboot.teacher.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spacey.springboot.response.StatusResponse;
import com.spacey.springboot.teacher.business.TeacherService;
import com.spacey.springboot.teacher.data.Teacher;

@RestController
@RequestMapping("/api/teacher")
public class TeacherController {
	
	@Autowired
	TeacherService teacherService;
	
	@GetMapping("/{teacherId}")
	public Teacher getTeacher(@PathVariable("teacherId") Long teacherId) {
		return teacherService.getTeacher(teacherId);
	}
	
	@PostMapping
	public Teacher createTeacher(@RequestBody Teacher teacher) {
		return teacherService.createTeacher(teacher);
	}
	
	@DeleteMapping("/{teacherId}")
	public StatusResponse deleteTeacher(@PathVariable("teacherId") Long teacherId) {
		return teacherService.deleteTeacher(teacherId);
	}
}
