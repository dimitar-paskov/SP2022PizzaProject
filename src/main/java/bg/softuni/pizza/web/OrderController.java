/**
 * @author dimitar
 *
 */
package bg.softuni.pizza.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import bg.softuni.pizza.model.dto.order.OrderDto;
import bg.softuni.pizza.model.dto.order.OrderPizzaDto;
import bg.softuni.pizza.model.enums.ProductCategoryEnum;
import bg.softuni.pizza.service.ProductService;

@Controller
public class OrderController {
	
	private final ProductService productService;


	public OrderController(ProductService productService) {
		this.productService = productService;
	}
	
	
	@GetMapping("/order/{id}")
	public String getPizza(@PathVariable Long id, Model model) {

		OrderDto order = productService.findAById(id);
		
		if(order.getCategory().equals(ProductCategoryEnum.PIZZA.name())) {
			
			OrderPizzaDto orderPizzaDto = (OrderPizzaDto)order; 
			
			model.addAttribute("order", orderPizzaDto);
			
			return "order-new-pizza";
		}else {
			
			model.addAttribute("order", order);
			return "order-new";
		}

	}
	
	

}
