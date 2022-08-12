/**
 * @author dimitar
 *
 */
package bg.softuni.pizza.web;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Locale;

import javax.servlet.http.Cookie;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class UserRegistrationControllerIT {
	
	@Autowired
	private MockMvc mockMvc;
	
	
	@Test
	void testRegistrationPageShown() throws Exception {
		
		mockMvc.perform(get("/users/register"))
			.andExpect(status().isOk())
			.andExpect(view().name("auth-register"));
		
	}
	
	@Test
	void testUserRegistration() throws Exception {
		
		mockMvc.perform(post("/users/register")
				.param("email", "anna@example.com")
				.param("firstName", "Anna")
				.param("lastName", "Petrova")
				.param("password", "topsecret")
				.param("confirmPassword", "topsecret")
				.cookie(new Cookie("lang", Locale.ENGLISH.getLanguage()))
				.with(csrf())
				
			).andExpect(status().is3xxRedirection())
			 .andExpect(redirectedUrl("/"));
		
	}
	
	@Test
	void testUserRegistrationFailing() throws Exception {
		
		mockMvc.perform(post("/users/register")
				.param("email", "anna@example.com")
				.param("firstName", "Anna")
				.param("lastName", "Petrova")
				.param("password", "topsecret")
				.param("confirmPassword", "topsecret")
				.cookie(new Cookie("lang", Locale.ENGLISH.getLanguage()))
				.with(csrf())
				
			).andExpect(status().is3xxRedirection())
			 .andExpect(redirectedUrl("/users/register"));
		
	}

}
