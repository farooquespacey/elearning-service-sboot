package com.spacey.springboot.converters;

import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.util.StdConverter;
import com.spacey.springboot.course.data.Course;

/**
 * Custom Converter for List<Course> fields
 * 
 * @author Spacey4uq
 *
 */
public class ListCourseConverter extends StdConverter<List<Course>, List<String>> {
	@Override
	public List<String> convert(List<Course> courses) {
		return courses.stream().map(Course::getTitle).collect(Collectors.toList());
	}
}