/**
 * @author dimitar
 *
 */
package bg.softuni.pizza.web;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;

import bg.softuni.pizza.service.ProductService;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerIT {
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	void testGetIndex() throws Exception {
		
		mockMvc.perform(get("/"))
			.andExpect(status().is3xxRedirection())
			.andExpect(redirectedUrl("/pizzas"));
		
	}
	
		
	@Test
	@WithUserDetails(value = "user@example.com")
	void testGetPizzas() throws Exception {
		
		mockMvc.perform(get("/pizzas"))
			.andExpect(status().isOk())
			.andExpect(model().attributeExists("pizzas"))
			.andExpect(view().name("pizza"));
		
	}
	
	@Test
	@WithUserDetails(value = "user@example.com")
	void testGetSalads() throws Exception {
		
		mockMvc.perform(get("/salads"))
			.andExpect(status().isOk())
			.andExpect(model().attributeExists("salads"))
			.andExpect(view().name("salad"));
		
	}
	
	@Test
	@WithUserDetails(value = "user@example.com")
	void testGetPasta() throws Exception {
		
		mockMvc.perform(get("/pasta"))
			.andExpect(status().isOk())
			.andExpect(model().attributeExists("pastas"))
			.andExpect(view().name("pasta"));
		
	}
	
	@Test
	@WithUserDetails(value = "admin@example.com")
	void testGetProductAdd() throws Exception {
		
		mockMvc.perform(get("/product/add"))
			.andExpect(status().isOk())
			.andExpect(model().attributeExists("product"))
			.andExpect(view().name("add-product"));
		
	}
	
	@Test
	void testPostProductAddUnauthorised() throws Exception {
		
		mockMvc.perform(post("/products/add"))
			.andExpect(status().isForbidden());
		
	}
	
	@Test
	@WithUserDetails(value = "admin@example.com")
	void testPostProductAddPizza() throws Exception {
		
//		Path path = Paths.get("/SP2022PizzaProject/src/main/resources/static/images/hawai.png");
//		String name = "hawai.png";
//		String originalFileName = "hawai.png";
//		String contentType = "image/png";
//		byte[] content = null;
//		try {
//		    content = Files.readAllBytes(path);
//		} catch (final IOException e) {
//		}
//		
//		MultipartFile multipartFile = new MockMultipartFile(name,
//                originalFileName, contentType, content);
//		
//		
//		ObjectMapper objectMapper = new ObjectMapper();
		
		mockMvc.perform(multipart("/products/add")
				.param("name", "Nova Pizza")
				.param("description", "Cheese, tomatoJuice")
				.param("category", "PIZZA")
				.param("price", "17.00")
				.with(csrf())
				.contentType("multipart/form-data")
				)
		.andExpect(status().is3xxRedirection())
		.andExpect(redirectedUrl("/pizzas"));
		
	}
	
	@Test
	@WithUserDetails(value = "admin@example.com")
	void testPostProductAddPizza_WithValidationErrors() throws Exception {
		
		
		mockMvc.perform(multipart("/products/add")
				.param("name", "")
				.param("description", "Cheese, tomatoJuice")
				.param("category", "PIZZA")
				.param("price", "17.00")
				.with(csrf())
				.contentType("multipart/form-data")
				)
		.andExpect(status().is3xxRedirection())
		.andExpect(redirectedUrl("/products/add"));
		
	}
	
	@Test
	@WithUserDetails(value = "admin@example.com")
	void testPostProductAddPasta() throws Exception {
		
		
		mockMvc.perform(multipart("/products/add")
				.param("name", "Nova Pasta")
				.param("description", "Cheese, tomatoJuice")
				.param("category", "PASTA")
				.param("price", "17.00")
				.with(csrf())
				.contentType("multipart/form-data")
				)
		.andExpect(status().is3xxRedirection())
		.andExpect(redirectedUrl("/pasta"));
		
	}
	
	@Test
	@WithUserDetails(value = "admin@example.com")
	void testPostProductAddSalad() throws Exception {

		
		mockMvc.perform(multipart("/products/add")
				.param("name", "Nova Salad")
				.param("description", "Cheese, tomatoJuice")
				.param("category", "SALAD")
				.param("price", "17.00")
				.with(csrf())
				.contentType("multipart/form-data")
				)
		.andExpect(status().is3xxRedirection())
		.andExpect(redirectedUrl("/salads"));
		
	}
	
	@Test
	@WithUserDetails(value = "admin@example.com")
	void testGetProductDeletePizza() throws Exception {
		
		mockMvc.perform(get("/product/delete/pizza"))
			.andExpect(status().isOk())
			.andExpect(model().attributeExists("pizzas"))
			.andExpect(view().name("delete_pizza"));
		
	}
	
	@Test
	@WithUserDetails(value = "admin@example.com")
	void testGetProductDeletePasta() throws Exception {
		
		mockMvc.perform(get("/product/delete/pasta"))
			.andExpect(status().isOk())
			.andExpect(model().attributeExists("pastas"))
			.andExpect(view().name("delete_pasta"));
		
	}
	
	@Test
	@WithUserDetails(value = "admin@example.com")
	void testGetProductDeleteSalad() throws Exception {
		
		mockMvc.perform(get("/product/delete/salad"))
			.andExpect(status().isOk())
			.andExpect(model().attributeExists("salads"))
			.andExpect(view().name("delete_salad"));
		
	}
	
	@Test
	void testGetProductDeletePizzaUnauthenticated() throws Exception {
		
		mockMvc.perform(get("/product/delete/pizza"))
			.andExpect(status().is3xxRedirection())
			.andExpect(redirectedUrl("http://localhost/users/login"));
		
	}
	
	@Test
	@WithUserDetails(value = "user@example.com")
	void testGetProductDeletePizzaUnauthorised() throws Exception {
		
		mockMvc.perform(get("/product/delete/pizza"))
			.andExpect(status().isForbidden());
		
	}
	
	@Test
	void testGetProductDeletePastaUnauthenticated() throws Exception {
		
		mockMvc.perform(get("/product/delete/pasta"))
			.andExpect(status().is3xxRedirection())
			.andExpect(redirectedUrl("http://localhost/users/login"));
		
	}
	
	@Test
	@WithUserDetails(value = "user@example.com")
	void testGetProductDeletePastaUnauthorised() throws Exception {
		
		mockMvc.perform(get("/product/delete/pasta"))
			.andExpect(status().isForbidden());
		
	}
	
	@Test
	void testGetProductDeleteSaladUnauthenticated() throws Exception {
		
		mockMvc.perform(get("/product/delete/salad"))
			.andExpect(status().is3xxRedirection())
			.andExpect(redirectedUrl("http://localhost/users/login"));
		
	}
	
	@Test
	@WithUserDetails(value = "user@example.com")
	void testGetProductDeleteSaladUnauthorised() throws Exception {
		
		mockMvc.perform(get("/product/delete/salad"))
			.andExpect(status().isForbidden());
		
	}
	
	@Test
	@WithUserDetails(value = "admin@example.com")
	void testPostProductDeletePizza() throws Exception {
		
		int expectedCount = productService.findAllPizzas().size() -1;
		Long id = productService.findAllPizzas().get(0).getId();
		
		mockMvc.perform(delete("/product/delete/PIZZA/" + id)
				.with(csrf()))
		.andExpect(status().is3xxRedirection())
		.andExpect(redirectedUrl("/pizzas"));
		
		Assertions.assertEquals(expectedCount, productService.findAllPizzas().size()); 
		
	}
	
	@Test
	@WithUserDetails(value = "admin@example.com")
	void testPostProductDeletePasta() throws Exception {
		
		int expectedCount = productService.findAllPasta().size() -1;
		Long id = productService.findAllPasta().get(0).getId();
		
		mockMvc.perform(delete("/product/delete/PASTA/" + id)
				.with(csrf()))
		.andExpect(status().is3xxRedirection())
		.andExpect(redirectedUrl("/pasta"));
		
		Assertions.assertEquals(expectedCount, productService.findAllPasta().size()); 
		
	}
	
	@Test
	@WithUserDetails(value = "admin@example.com")
	void testPostProductDeleteSalad() throws Exception {
		
		int expectedCount = productService.findAllSalads().size() -1;
		Long id = productService.findAllSalads().get(0).getId();
		
		mockMvc.perform(delete("/product/delete/SALAD/" + id)
				.with(csrf()))
		.andExpect(status().is3xxRedirection())
		.andExpect(redirectedUrl("/salads"));
		
		Assertions.assertEquals(expectedCount, productService.findAllSalads().size()); 
		
	}
	
	

}
