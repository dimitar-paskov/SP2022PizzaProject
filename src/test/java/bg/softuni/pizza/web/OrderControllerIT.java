/**
 * @author dimitar
 *
 */
package bg.softuni.pizza.web;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;

import bg.softuni.pizza.model.dto.order.OrderDto;
import bg.softuni.pizza.service.ProductService;

@SpringBootTest
@AutoConfigureMockMvc
public class OrderControllerIT {
	
	@MockBean
	private ProductService productService;
	
	@Autowired
	private MockMvc mockMvc;
	
	
	@Test
	@WithUserDetails(value = "user@example.com")
	void testGetOrderWithProductId() throws Exception {
		
		OrderDto orderDto = new OrderDto();
		orderDto.setId(1L);
		orderDto.setName("test");
		orderDto.setCategory("PIZZA");
		orderDto.setImageUrl("testUrl"); 
		
		
		when(productService.findAById(1L)).thenReturn(orderDto);
		
		
		
		mockMvc.perform(get("/order/1"))
			.andExpect(status().isOk())
			.andExpect(model().attributeExists("order"))
			.andExpect(view().name("order-new"));
		
	}
	
	@Test
	void testGetOrderWithProductIdUnauthenticated() throws Exception {
		
		OrderDto orderDto = new OrderDto();
		orderDto.setId(1L);
		orderDto.setName("test");
		orderDto.setCategory("PIZZA");
		orderDto.setImageUrl("testUrl"); 
		
		
		when(productService.findAById(1L)).thenReturn(orderDto);
		
		
		
		mockMvc.perform(get("/order/1"))
			.andExpect(status().is3xxRedirection())
			.andExpect(redirectedUrl("http://localhost/users/login"));
		
	}
	
	

}
