package com.spacey.springboot.course.business;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.spacey.springboot.course.data.Course;

/**
 * Custom serializer for Course class by ignoring its other properties in JSON.
 * 
 * @author Spacey4uq
 *
 */
public class CourseSerializer extends StdSerializer<Course> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CourseSerializer() {
		this(null);
	}

	public CourseSerializer(Class<Course> t) {
		super(t);
	}

	@Override
	public void serialize(Course value, JsonGenerator jgen, SerializerProvider provider)
			throws IOException, JsonProcessingException {
//		{
//		    "id": , ?------------ integer
//		    "title": "", ?------------ string 
//		    "teacher": "", ?------------ string
//		    "material": "", ?------------ string (url to the material)
//		    "total_students": ?------------ integer
//		}
		jgen.writeStartObject();
		jgen.writeNumberField("id", value.getId());
		jgen.writeStringField("title", value.getTitle());
		jgen.writeStringField("teacher", value.getTeacher().getFirstName() + " " + value.getTeacher().getLastName());
		jgen.writeStringField("material", (value.getMaterial() == null) ? "" : value.getMaterial().getUrl());
		jgen.writeNumberField("total_students", value.getStudents().size());
		jgen.writeEndObject();
	}
}