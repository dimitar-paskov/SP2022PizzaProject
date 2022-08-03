/**
 * @author dimitar
 *
 */
package bg.softuni.pizza.web;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import bg.softuni.pizza.model.views.ProductView;
import bg.softuni.pizza.service.ProductService;

@Controller
public class ProductController {

	private final ProductService productService;

	public ProductController(ProductService productService) {
		this.productService = productService;

	}

	@GetMapping("/pizzas")
	public String getPizza(Model model) {

		List<ProductView> pizzas = productService.findAllPizzas();

		model.addAttribute("pizzas", pizzas);

		return "pizza";
	}
	
	@GetMapping("/pasta")
	public String getPasta(Model model) {

		List<ProductView> pastas = productService.findAllPasta();

		model.addAttribute("pastas", pastas);

		return "pasta";
	}
	
	
	@GetMapping("/salads")
	public String getSalads(Model model) {

		List<ProductView> salads = productService.findAllSalads();

		model.addAttribute("salads", salads);

		return "salad";
	}
	
	@GetMapping("/")
	public String getIndex() {

		return "redirect:/pizzas";
	}

}
