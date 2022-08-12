/**
 * @author dimitar
 *
 */
package bg.softuni.pizza.web;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import bg.softuni.pizza.model.dto.product.ProductDto;
import bg.softuni.pizza.model.views.ProductView;
import bg.softuni.pizza.service.ProductService;

@Controller
public class ProductController {

	private final ProductService productService;

	public ProductController(ProductService productService) {
		this.productService = productService;

	}
	
	@GetMapping("/")
	public String getIndex() {

		return "redirect:/pizzas";
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



	@GetMapping("/product/add")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public String getAddProductForm(Model model) {

		ProductDto product = new ProductDto();

		model.addAttribute("product", product);

		return "add-product";
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/products/add")
	public String add(@Valid ProductDto productDto, BindingResult bindingResult,
			RedirectAttributes redirectAttributes) throws IOException {

		if (bindingResult.hasErrors()) {
			redirectAttributes.addFlashAttribute("product", productDto).addFlashAttribute(
					"org.springframework.validation.BindingResult.ProductDto", bindingResult);

			return "redirect:/products/add";
		}

		this.productService.add(productDto);
		
		if(productDto.getCategory().equals("PIZZA")) {
			return "redirect:/pizzas";
		}
		
		if(productDto.getCategory().equals("SALAD")) {
			return "redirect:/salads";
		}
		
		if(productDto.getCategory().equals("PASTA")) {
			return "redirect:/pasta";
		}

		return "redirect:/pizzas";
	}
	

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/product/delete/pizza")
	public String getDeletePizza(Model model) {
		
		List<ProductView> pizzas = productService.findAllPizzas();
		
		model.addAttribute("pizzas", pizzas);
		
		return "delete_pizza";
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/product/delete/pasta")
	public String getDeletePasta(Model model) {
		
		List<ProductView> pastas = productService.findAllPasta();
		
		model.addAttribute("pastas", pastas);
		
		return "delete_pasta";
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/product/delete/salad")
	public String getDeleteSalad(Model model) {
		
		List<ProductView> salads = productService.findAllSalads();
		
		model.addAttribute("salads", salads);
		
		return "delete_salad";
	}
	
	
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@DeleteMapping(value = "/product/delete/{category}/{productId}")
	public String deleteProduct(@PathVariable("productId")  Long productId, @PathVariable("category") String category,  @AuthenticationPrincipal UserDetails principal) {
		
		
		productService.deleteProduct(productId,principal);
		
		if(category.equals("PIZZA")) {
			return "redirect:/pizzas";
		}
		
		if(category.equals("SALAD")) {
			return "redirect:/salads";
		}
		
		
		if(category.equals("PASTA")) {
			return "redirect:/pasta";
		}
		
		return "redirect:/pizzas"; 
	}

}
