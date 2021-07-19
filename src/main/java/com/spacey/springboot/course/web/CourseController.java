package com.spacey.springboot.course.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spacey.springboot.course.business.CourseService;
import com.spacey.springboot.course.data.Course;
import com.spacey.springboot.course.data.CourseMaterial;
import com.spacey.springboot.response.StatusResponse;
import com.spacey.springboot.teacher.data.Teacher;

@RestController
@RequestMapping("/api/course")
public class CourseController {

	@Autowired
	CourseService courseService;

	@GetMapping("/{courseId}")
	public Course fetchCourse(@PathVariable("courseId") Long courseId) {
		return courseService.fetchCourse(courseId);
	}

	@GetMapping
	public List<Course> fetchAllCourses() {
		return courseService.fetchAllCourses();
	}
	

	@GetMapping("/by/{teacherId}")
	public List<Course> fetchAllCoursesBy(@PathVariable("teacherId") Long teacherId) {
		return courseService.fetchAllCoursesBy(teacherId);
	}	

//	@PostMapping("/by/{teacherId}")
//	public Course createCourse(@RequestBody Course course, @PathVariable("teacherId") Long teacherId) {
//		course.setTeacher(Teacher.createWithId(teacherId));
////		course.getMaterial().setCourse(course); // required if CourseMaterial becomes the owner of O2O mapping
//		return courseService.createCourse(course);
//	}
	
	@PostMapping("/by/{teacherId}")
	public Course createCourse(@RequestBody Course course, @PathVariable("teacherId") Long teacherId) {
		course.setTeacher(Teacher.createWithId(teacherId));
		course.getMaterial().setCourse(course);
		return courseService.createCourse(course);
	}

	@PutMapping("/{courseId}/material")
	public CourseMaterial updateMaterialToCourse(@RequestBody CourseMaterial courseMaterial,
			@PathVariable("courseId") Long courseId) {
		return courseService.updateMaterialToCourse(courseMaterial, courseId);
	}

	@DeleteMapping("/{courseId}")
	public StatusResponse deleteCourse(@PathVariable("courseId") Long courseId) {
		return courseService.deleteCourse(courseId);
	}

}
