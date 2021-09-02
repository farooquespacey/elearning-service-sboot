package com.spacey.springboot.test.unit;

import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spacey.springboot.response.StatusResponse;
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
	
	// hardcoded IDs as well since the entire data is a stubbed one in unit tests
	private Teacher farooque = Teacher.builder().id(1L).firstName("farooque").lastName("spacey").build();
	private Teacher john = Teacher.builder().id(2L).firstName("john").lastName("doe").build();
	private Teacher alex = Teacher.builder().id(3L).firstName("alex").lastName("knight").build();
	private List<Teacher> allTeachers = Arrays.asList(farooque, john, alex);

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

	@Test
	public void whenValidInput_thenCreateTeacher() throws Exception {
		when(teacherService.createTeacher(any())).thenReturn(teacher);
		mockMvc.perform(post("/api/teacher").contentType(MediaType.APPLICATION_JSON).content(asJsonString(teacher)))
				.andExpect(status().isOk());
		verify(teacherService, times(1)).createTeacher(any());
	}
	
    @Test
    public void givenTeachers_whenGetTeachers_thenStatus200() throws Exception {
        when(teacherService.getAllTeachers()).thenReturn(allTeachers);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/teacher").
                contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(3))))
                .andExpect(jsonPath("$[0].first_name", is("farooque")))
                .andExpect(jsonPath("$[1].first_name", is("john")))
        		.andExpect(jsonPath("$[2].first_name", is("alex")));
        verify(teacherService,times(1)).getAllTeachers();
    }
    
	@Test
	public void whenDeleteTeacher_thenDeleteTeacher() {
		doNothing().when(teacherService).deleteTeacher(any());
		StatusResponse deletionResponse = teacherService.deleteTeacher(1L);
		assertEquals(deletionResponse.getStatus(), "success");
		verify(teacherService,times(1)).deleteTeacher(any());
	}

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
