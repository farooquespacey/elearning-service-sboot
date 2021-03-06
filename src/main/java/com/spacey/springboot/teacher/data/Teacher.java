package com.spacey.springboot.teacher.data;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.spacey.springboot.converters.ListCourseConverter;
import com.spacey.springboot.course.data.Course;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Builder.Default;

@Entity
@NoArgsConstructor @AllArgsConstructor @Builder
@JsonIgnoreProperties(value = { "courses" }, allowGetters = true)
public class Teacher {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Getter
	@Setter
	private Long id;
	@Getter
	@Setter
	@JsonProperty("first_name")
	private String firstName;
	@Getter
	@Setter
	@JsonProperty("last_name")
	private String lastName;

	@OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL /*Try , fetch = EAGER*/)
	@JsonSerialize(converter = ListCourseConverter.class)
	@Getter
	@Setter
	@Default
	private List<Course> courses = new ArrayList<>();
	
	public static Teacher createWithId(Long id) {
		return new Teacher(id);
	}

	public Teacher(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Teacher [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", courses=" + courses
				+ "]";
	}

}