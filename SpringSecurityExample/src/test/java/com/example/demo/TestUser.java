package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.example.demo.controller.LoginController;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.rest.model.LoginPOJO;
import com.example.demo.services.LoginServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class TestUser {

	@InjectMocks
	private LoginController userController;

	@MockBean
	private UserRepository userRepository;
	
	@Autowired
	private LoginServiceImpl loginService;
	
	@Autowired
	private ObjectMapper mapper;

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void testRegistration() {
		User user = new User();
		user.setId("dhf");
		user.setFirstName("Bhoomi");
		user.setLastName("Thakkar");
		user.setEmailId("Bhoomi@gmail.com");
		user.setPassword("bhoomi123");
		user.setRole("Admin");

		Mockito.when(userRepository.save(user)).thenReturn(user);
		try {
			RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/user/registration")
					.accept(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(user))
					.contentType(MediaType.APPLICATION_JSON);

			MvcResult result = mockMvc.perform(requestBuilder).andReturn();
			MockHttpServletResponse response = result.getResponse();

			User entity = mapper.readValue(response.getContentAsString(), user.getClass());
			assertThat(entity.getFirstName()).isEqualTo(user.getFirstName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testLogin() {
		User user = new User();
		user.setId("dhfd");
		user.setFirstName("Bhoomi");
		user.setLastName("Thakkar");
		user.setEmailId("Bhoomi1@gmail.com");
		user.setPassword("bhoomi1234");
		user.setRole("Admin");
		LoginPOJO login = new LoginPOJO();
		login.setEmailId("Bhoom1i@gmail.com");
		login.setPassword("bhoomi1234");
		Mockito.when(userRepository.login(login.getEmailId(), loginService.passwordEncryption(login.getPassword()))).thenReturn(user);
		try {
			RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/user/login")
					.accept(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(login))
					.contentType(MediaType.APPLICATION_JSON);

			MvcResult result = mockMvc.perform(requestBuilder).andReturn();
			MockHttpServletResponse response = result.getResponse();
			User entity = mapper.readValue(response.getContentAsString(), User.class);

			assertThat(entity.getFirstName()).isEqualTo(user.getFirstName());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testForgotpassword() {
		User user = new User();
		user.setId("dhf");
		user.setFirstName("Bhoomi");
		user.setLastName("Thakkar");
		user.setEmailId("Bhoomi12@gmail.com");
		user.setPassword("bhoomi123");
		user.setRole("Admin");

		Mockito.when(userRepository.findByEmail(user.getEmailId())).thenReturn(user);
		try {
			RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/user/forgotPassword")
					.accept(MediaType.APPLICATION_JSON).content(user.getEmailId())
					.contentType(MediaType.APPLICATION_JSON);
			MvcResult result = mockMvc.perform(requestBuilder).andReturn();
			MockHttpServletResponse response = result.getResponse();

			User entity = mapper.readValue(response.getContentAsString(), User.class);
			assertThat(entity.getFirstName()).isEqualTo(user.getFirstName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
