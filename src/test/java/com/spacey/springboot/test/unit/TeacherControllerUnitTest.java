package com.spacey.springboot.test.unit;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.spacey.springboot.teacher.business.TeacherService;
import com.spacey.springboot.teacher.data.Teacher;
import com.spacey.springboot.teacher.web.TeacherController;

/**
 * We will also write a pure unit test for the controller.
 * 
 * @author Spacey4uq
 *
 */
@ExtendWith(MockitoExtension.class)
public class TeacherControllerUnitTest {

	/**
	 * Since the controller has a dependency on the service class. So in our test,
	 * we will use Mockito to mock Serivce and inject the mock on Controller.
	 */
	@Mock
	private TeacherService teacherService;
	private Teacher teacher;
	private List<Teacher> teacherList;

	@InjectMocks
	private TeacherController teacherController;

	@Autowired
	private MockMvc mockMvc;

	@BeforeEach
	public void setup() {
		teacher = Teacher.builder().id(1L).firstName("farooque").lastName("spacey").build();
		mockMvc = MockMvcBuilders.standaloneSetup(teacherController).build();
	}

	@AfterEach
	public void teardown() {
		teacher = null;
	}

	/**
	 * What we have done above:
	 * 
	 * we use the @Mock annotation on Service class. At run time, Mockito will
	 * create a mock of that Service.
	 * 
	 * Next, we use @Autowired annotation to autowire in MockMvc. The @InjectMock
	 * annotation will initialize the Controller object.
	 * 
	 * We need to send HTTP requests to the controller from our test class to assert
	 * they are responding as expected. For that, we use MockMvc.
	 * 
	 * MockMvc provides a powerful way to mock Spring MVC. Through @MockMvc you can
	 * send MockHttp request to a controller and test how the controller responds.
	 * 
	 * You can create instance of mockMvc through two methods of MockMvcBuilders. I
	 * have used standaloneSetup which registers the controller instances. The other
	 * one is the webappContextSetup method.
	 */
	
	
}
