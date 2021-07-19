package com.spacey.springboot.test.unit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import com.spacey.springboot.response.StatusResponse;
import com.spacey.springboot.teacher.business.TeacherService;
import com.spacey.springboot.teacher.data.Teacher;
import com.spacey.springboot.teacher.data.TeacherNotFoundException;
import com.spacey.springboot.teacher.data.TeacherRepository;

/**
 * The Service layer class ProductServiceImpl is responsible for using the
 * repository for performing CRUD operation.
 * 
 * We will write pure unit tests of the service implementation –
 * ProductServiceImpl . The reason is that unit tests are super-fast and
 * therefore cuts down developers’ time.
 * 
 * Note that in unit testing, when we have external dependencies, we mock the
 * dependencies. So in this example, we will mock the ProductRepository class.
 * 
 * For more information on mocking, refer to my post Mocking in Unit Tests with
 * Mockito.
 * 
 * Let’s start writing the code. The code for the unit test is this.
 * 
 * @author Spacey4uq
 *
 */
@ExtendWith(MockitoExtension.class)
class TeacherServiceUnitTest {
	@Mock
	private TeacherRepository teacherRepository;

	@Autowired // Required if the TeacherService is an interface instead
	@InjectMocks
	private TeacherService teacherService;

	// hardcoded IDs as well since the entire data is a stubbed one in unit tests
	private Teacher farooque = Teacher.builder().id(1L).firstName("farooque").lastName("spacey").build();
	private Teacher john = Teacher.builder().id(2L).firstName("john").lastName("doe").build();
	private Teacher alex = Teacher.builder().id(3L).firstName("alex").lastName("knight").build();
	private List<Teacher> allTeachers = Arrays.asList(farooque, john, alex);

	/**
	 * What we have done above:
	 * 
	 * This MockitoExtension is a part of the Mockito library that is used to
	 * perform mocking. It initializes mocks in test classes.
	 * 
	 * Then, we use the @Mock annotation on repository. At run time, Mockito will
	 * create a mock of that Repository.
	 * 
	 * Finally, we use the @Autowired annotation to autowire in Service.
	 * The @InjectMock annotation will initialize the Service object with the
	 * Repository mock.
	 * 
	 * Let's create some testcases now.
	 */

	@Test
	public void givenTeacherToAdd_thenReturnAddedTeacher() {
		when(teacherRepository.save(farooque)).thenReturn(farooque);

		Teacher createdTeacher = teacherService.createTeacher(farooque);

		assertThat(createdTeacher).isNotNull().isEqualTo(farooque);
		verifySaveIsCalledOnce();
	}

	@Test
	public void whenValidId_thenTeacherShouldBeFound() {
		when(teacherRepository.findById(farooque.getId())).thenReturn(Optional.of(farooque));

		Teacher found = teacherService.getTeacher(1L);

		assertThat(found).isEqualTo(farooque);
		verifyFindByIdIsCalledOnce();
	}

	@Test
	public void whenInvalidId_thenTeacherShouldNotBeFound() {
		doThrow(RuntimeException.class).when(teacherRepository).deleteById(-99L);

		TeacherNotFoundException thrown = assertThrows(TeacherNotFoundException.class,
				() -> teacherService.getTeacher(-99L), "expected getTeacher to throw!");

		assertTrue(thrown.getMessage().contains("not found"));
		verifyFindByIdIsCalledOnce();
	}

	@Test
	public void whenValidName_thenTeacherShouldBeFound() {
		when(teacherRepository.findByFirstNameAndLastName(farooque.getFirstName(), farooque.getLastName()))
				.thenReturn(Optional.of(farooque));

		Teacher found = teacherService.getTeacherByName("farooque", "spacey");

		verifyFindByNameIsCalledOnce(farooque.getFirstName(), farooque.getLastName());
		assertThat(found.getLastName()).isEqualTo("spacey");
	}
	
	@Test
	public void whenInvalidName_thenTeacherShouldBeFound() {
		when(teacherRepository.findByFirstNameAndLastName("wrong", "name"))
				.thenReturn(Optional.empty());

		Teacher found = teacherService.getTeacherByName("wrong", "name");

		verifyFindByNameIsCalledOnce("wrong", "name");
		assertThat(found).isNull();
	}

	@Test
	public void given3Teachers_whenGetAll_thenReturn3Records() {
		when(teacherRepository.findAll()).thenReturn(allTeachers);

		List<Teacher> newTeacherList = teacherService.getAllTeachers();

		assertThat(newTeacherList).isNotNull().isNotEmpty().hasSize(3).extracting(Teacher::getLastName)
				.contains(farooque.getLastName(), john.getLastName(), alex.getLastName());
		verifyFindAllEmployeesIsCalledOnce();
	}

	@Test
	public void givenIdToDelete_thenRecordShouldBeDeleted() {
		doNothing().when(teacherRepository).deleteById(farooque.getId());

		StatusResponse deletionResponse = teacherService.deleteTeacher(1L);

		assertEquals(deletionResponse.getStatus(), "success");
		verifyDeleteByIdIsCalledOnce();
	}

	@Test
	public void givenWrongIdToDelete_shouldThrowException() {
		doThrow(RuntimeException.class).when(teacherRepository).deleteById(-99L);

		TeacherNotFoundException thrown = assertThrows(TeacherNotFoundException.class,
				() -> teacherService.deleteTeacher(-99L), "expected deleteTeacher to throw!");

		assertTrue(thrown.getMessage().contains("not found"));
		verifyDeleteByIdIsCalledOnce();
	}

	private void verifyFindByNameIsCalledOnce(String first, String last) {
		verify(teacherRepository, times(1)).findByFirstNameAndLastName(first, last);
		reset(teacherRepository);
	}

	private void verifyFindByIdIsCalledOnce() {
		verify(teacherRepository, times(1)).findById(any(Long.class));
		reset(teacherRepository);
	}

	private void verifyFindAllEmployeesIsCalledOnce() {
		verify(teacherRepository, times(1)).findAll();
		reset(teacherRepository);
	}

	private void verifySaveIsCalledOnce() {
		verify(teacherRepository, times(1)).save(any(Teacher.class));
		reset(teacherRepository);
	}

	private void verifyDeleteByIdIsCalledOnce() {
		verify(teacherRepository, times(1)).deleteById(any(Long.class));
		reset(teacherRepository);
	}

}
