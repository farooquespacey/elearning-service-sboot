package com.spacey.springboot.student.data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.spacey.springboot.converters.ListCourseConverter;
import com.spacey.springboot.course.data.Course;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "stud")
@JsonIgnoreProperties(value = { "courses" }, allowGetters = true)
public class Student {
	@Id
	@GeneratedValue
	@Getter
	@Setter
	private Long id;
	@Getter
	@Setter
	private String lastName;
	@Column(name = "first_name")
	@Getter
	@Setter
	private String firstName;

	@Column(name = "birthdate")
	@Temporal(TemporalType.DATE)
	@Getter
	@Setter
	private Date birthDateAsDate;

	@Column(name = "birthdate", insertable = false, updatable = false)
	@Getter
	@Setter
	private LocalDate birthDateAsLocalDate;

	@Enumerated(EnumType.STRING)
	@Getter
	@Setter
	private Gender gender;

	@Getter
	@Setter
	private boolean wantsNewsletter;

	@Embedded
	@AttributeOverrides({ @AttributeOverride(name = "street", column = @Column(name = "st_street")),
			@AttributeOverride(name = "number", column = @Column(name = "st_number")),
			@AttributeOverride(name = "city", column = @Column(name = "st_city")) })
	@Getter
	@Setter
	private Address address;

	@ManyToMany(mappedBy = "students")
	@JsonSerialize(converter = ListCourseConverter.class) // https://stackoverflow.com/questions/41649932/custom-serializer-for-serializing-a-listuser-in-liststring/41651324
	@Getter
	@Setter
	private List<Course> courses = new ArrayList<>();

	public void addCourse(Course course) {
		this.courses.add(course);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Student student = (Student) o;
		return Objects.equals(id, student.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", lastName=" + lastName + ", firstName=" + firstName + ", birthDateAsDate="
				+ birthDateAsDate + ", birthDateAsLocalDate=" + birthDateAsLocalDate + ", gender=" + gender
				+ ", wantsNewsletter=" + wantsNewsletter + ", address=" + address + ", courses=" + courses + "]";
	}

	public enum Gender {
		MALE, FEMALE
	}
}