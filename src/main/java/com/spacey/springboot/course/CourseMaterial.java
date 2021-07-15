package com.spacey.springboot.course;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.Getter;
import lombok.Setter;

@Entity
class CourseMaterial {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Getter
	@Setter
	private Long id;

	@Getter
	@Setter
	private String url;

//	@OneToOne(optional = false)
//	@JoinColumn(name = "course_id", referencedColumnName = "id")
	@OneToOne(mappedBy = "material", optional = false)
	@JsonSerialize(using = CourseToStringSerializer.class)
	@Getter
	@Setter
	private Course course;
	
	public CourseMaterial() {}
	
	public CourseMaterial(String url) { this.url = url; }

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		CourseMaterial that = (CourseMaterial) o;
		return Objects.equals(id, that.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public String toString() {
		return "CourseMaterial [id=" + id + ", url=" + url + "]";
	}

}