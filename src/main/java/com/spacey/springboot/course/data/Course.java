package com.spacey.springboot.course.data;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.spacey.springboot.course.business.CourseMaterialDeserializer;
import com.spacey.springboot.course.business.CourseSerializer;
import com.spacey.springboot.student.data.Student;
import com.spacey.springboot.teacher.data.Teacher;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor @AllArgsConstructor @Builder 
@JsonSerialize(using = CourseSerializer.class)
@JsonIgnoreProperties(value = { "teacher", "students" }, allowGetters = true) // if you want to serialize
																				// properties but ignore few
																				// while deserializing it
public class Course {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter
	@Setter
	private Long id;
	@Getter
	@Setter
	private String title;

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "teacher_id", referencedColumnName = "id")
	@Getter
	@Setter
	private Teacher teacher;

//	@OneToOne(mappedBy = "course", cascade = CascadeType.ALL)
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "course_material_id")
	@Getter
	@Setter
	@JsonDeserialize(using = CourseMaterialDeserializer.class)
	private CourseMaterial material;

	@ManyToMany
	@JoinTable(name = "students_courses", joinColumns = @JoinColumn(name = "course_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "student_id", referencedColumnName = "id"))
	@Getter
	@Setter
	@Default
	private Set<Student> students = new HashSet<>();

//    public Course(String title) {
//        this.title = title;
//    }

	
	public Course(String title, CourseMaterial material) {
		this.title = title;
		this.material = material;
	}
	
	public Course(Long courseId) {
		this.id = courseId;
	}

	public void addStudent(Student student) {
		this.students.add(student);
//        student.addCourse(this);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Course course = (Course) o;
		return Objects.equals(id, course.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public String toString() {
		return "Course [id=" + id + ", title=" + title + ", teacher=" + teacher + ", material=" + material
				+ ", students=" + students + "]";
	}

}