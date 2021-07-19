package com.spacey.springboot.course.business;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.spacey.springboot.course.data.Course;

/**
 * Used by CourseMaterial class to serialize its Course field
 * 
 * @author Spacey4uq
 *
 */
public class CourseToStringSerializer extends JsonSerializer<Course> {

	@Override
	public void serialize(Course value, JsonGenerator gen, SerializerProvider serializers)
			throws IOException, JsonProcessingException {
		gen.writeObject(value.getTitle());
	}

}
