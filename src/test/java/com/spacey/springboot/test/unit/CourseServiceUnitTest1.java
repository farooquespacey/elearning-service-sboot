//package com.spacey.springboot.test.unit;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.HashSet;
//import java.util.List;
//
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//import static org.hamcrest.Matchers.*;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.spacey.springboot.course.data.Course;
//import com.spacey.springboot.course.data.CourseMaterial;
//import com.spacey.springboot.course.data.CourseRepository;
//import com.spacey.springboot.course.web.CourseController;
//import com.spacey.springboot.teacher.data.Teacher;

/**
 * 
 * @author Spacey4uq
 *
 */
//@WebMvcTest(CourseController.class)
//public class CourseServiceUnitTest1 {
//
//	@Autowired
//	MockMvc mockMvc;
//	@Autowired
//	ObjectMapper mapper;
//
//	@MockBean
//	CourseRepository courseRepository;
//
//	Course course_1 = new Course(1L, "C1", new Teacher(11L, "a", "b", new ArrayList<>()), new CourseMaterial(11L, "url1", new Course()), new HashSet<>());
//	Course course_2 = new Course(2L, "C2", new Teacher(11L, "a", "b", new ArrayList<>()), new CourseMaterial(11L, "url2", new Course()), new HashSet<>());
//
//	@Test
//	public void getAllRecords_success() throws Exception {
//		List<Course> records = new ArrayList<>(Arrays.asList(course_1, course_2));
//
//		Mockito.when(courseRepository.findAll()).thenReturn(records);
//
//		mockMvc.perform(MockMvcRequestBuilders.get("/api/course").contentType(MediaType.APPLICATION_JSON))
//				.andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(2)))
//				.andExpect(jsonPath("$[1].title", is("C2")))
//				.andExpect(jsonPath("$[1].url", is("url2")));
//	}
//
//}
