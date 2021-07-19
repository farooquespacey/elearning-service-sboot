package com.spacey.springboot.test.integration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.spacey.springboot.teacher.data.Teacher;
import com.spacey.springboot.teacher.data.TeacherRepository;

/**
 * 
 * The first statement annotates the class
 * with @ExtendWith(SpringExtension.class) . This integrates the Spring test
 * context framework into the JUnit 5 Jupiter programming model.
 * 
 * Our test will be an integration test as an external database is used. Being
 * an integration test, we need to load the Spring context in our test. We can
 * do that using the @SpringBootTest annotation.
 * 
 * However, loading the entire Spring context is heavy and makes the tests slow.
 * 
 * Therefore, we will only load the Spring Data JPA slice of the Spring context.
 * The @DataJpaTest annotation in the code does exactly that.
 *
 * @author Spacey4uq
 * 
 */
@ExtendWith(SpringExtension.class)
@DataJpaTest
class TeacherRepositoryIntegrationTest {

	@Autowired
	private TeacherRepository teacherRepository;
	private Teacher teacher;

	@BeforeEach
	public void setUp() {
		teacher = Teacher.builder().firstName("farooque").lastName("spacey").build();
	}

	@AfterEach
	public void tearDown() {
		teacherRepository.deleteAll();
		teacher = null;
	}

	@Test
	public void givenTeacherToAdd_thenReturnAddedTeacher() {
		// given
		teacherRepository.save(teacher);
		Teacher fetchedProduct = teacherRepository.findById(teacher.getId()).get();
		
		// then
		assertEquals("spacey", fetchedProduct.getLastName());
	}

	@Test
	public void whenFindById_thenReturnTeacher() {
		// given
		Teacher freeguy = Teacher.builder().firstName("free").lastName("guy").build();
		Teacher saved = teacherRepository.save(freeguy);
		
		// when
		Optional<Teacher> optional = teacherRepository.findById(saved.getId());
		
		// then
		assertThat(saved).isEqualTo(optional.get());
	}

	@Test
	public void givenSetOfTeachers_whenFindAll_thenReturnAllTeachers() {
		// given
		Teacher maurice = Teacher.builder().firstName("maurice").lastName("prek").build();
		Teacher john = Teacher.builder().firstName("john").lastName("doe").build();
		teacherRepository.save(maurice);
		teacherRepository.save(john);
		
		// when
		List<Teacher> teacherList = teacherRepository.findAll();
		
		// then
        assertThat(teacherList).hasSize(2).extracting(Teacher::getLastName)
        .containsOnly(maurice.getLastName(), john.getLastName());
	}

	@Test
	public void givenTeacherToDelte_thenDeleteTeacher() {
		// given
		Teacher peanut = Teacher.builder().firstName("peanut").lastName("mib").build();
		Teacher savedTeacher = teacherRepository.save(peanut);
		
		// when
		teacherRepository.deleteById(savedTeacher.getId());
		
		// then
		Optional<Teacher> optional = teacherRepository.findById(savedTeacher.getId());
		assertEquals(Optional.empty(), optional);
	}

}
