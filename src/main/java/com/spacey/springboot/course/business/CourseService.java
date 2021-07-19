package com.spacey.springboot.course.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spacey.springboot.course.data.Course;
import com.spacey.springboot.course.data.CourseMaterial;
import com.spacey.springboot.course.data.CourseMaterialRepository;
import com.spacey.springboot.course.data.CourseRepository;
import com.spacey.springboot.response.StatusResponse;

@Service
public class CourseService {

	@Autowired
	CourseRepository courseRepository;
	@Autowired
	CourseMaterialRepository courseMaterialRepository;

	public Course fetchCourse(Long courseId) {
		return courseRepository.findById(courseId).orElseThrow(() -> new RuntimeException("course not found"));
//		return courseRepository.findOne(courseId);
	}

	public List<Course> fetchAllCourses() {
		return courseRepository.findAll();
	}

	public List<Course> fetchAllCoursesBy(Long teacherId) {
		return courseRepository.findByTeacherId(teacherId);
	}

	public Course createCourse(Course course) {
		return courseRepository.save(course);
	}

	public CourseMaterial updateMaterialToCourse(CourseMaterial courseMaterial, Long courseId) {
		Course found = fetchCourse(courseId);
		found.getMaterial().setUrl(courseMaterial.getUrl());
		return courseRepository.save(found).getMaterial();

		// OR the last line can be replace with these two (both are same except the
		// below ones does the insertion while the above ones does the updation)
//		courseMaterial.setCourse(found);
//		return courseMaterialRepository.save(courseMaterial);
	}

	@Transactional
	public StatusResponse deleteCourse(Long courseId) {
		courseRepository.deleteById(courseId);
//		courseRepository.delete(courseId);
		return new StatusResponse("success");
	}

}
