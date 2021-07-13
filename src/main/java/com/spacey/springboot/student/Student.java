package com.spacey.springboot.student;
import javax.persistence.*;

import com.spacey.springboot.course.Course;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "stud")
public class Student {
    @Id
    @GeneratedValue
    private Long id;

    private String lastName;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "birthdate")
    @Temporal(TemporalType.DATE)
    private Date birthDateAsDate;

    @Column(name = "birthdate", insertable = false, updatable = false)
    private LocalDate birthDateAsLocalDate;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private boolean wantsNewsletter;

    @Embedded
    @AttributeOverrides({
      @AttributeOverride(name = "street", column = @Column(name = "st_street")),
      @AttributeOverride(name = "number", column = @Column(name = "st_number")),
      @AttributeOverride(name = "city", column = @Column(name = "st_city"))
    })
    private Address address;

    @ManyToMany(mappedBy = "students")
    private List<Course> courses = new ArrayList<>();

    public Long id() {
        return id;
    }

    public String lastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String firstName() {
        return firstName;
    }

    public Date birthDateAsDate() {
        return birthDateAsDate;
    }

    public LocalDate birthDateAsLocalDate() {
        return birthDateAsLocalDate;
    }

    public Gender gender() {
        return gender;
    }

    public boolean wantsNewsletter() {
        return wantsNewsletter;
    }

    public Address address() {
        return address;
    }

    public List<Course> courses() {
        return courses;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setBirthDateAsDate(Date birthDateAsDate) {
        this.birthDateAsDate = birthDateAsDate;
    }

    public void setBirthDateAsLocalDate(LocalDate birthDateAsLocalDate) {
        this.birthDateAsLocalDate = birthDateAsLocalDate;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public void setWantsNewsletter(boolean wantsNewsletter) {
        this.wantsNewsletter = wantsNewsletter;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void addCourse(Course course) {
        this.courses.add(course);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(id, student.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


    public enum Gender {
        MALE,
        FEMALE
    }
}