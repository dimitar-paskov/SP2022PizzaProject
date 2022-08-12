/**
 * @author dimitar
 *
 */
package bg.softuni.pizza.web;

import java.io.IOException;
import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import bg.softuni.pizza.model.dto.order.OrderDto;
import bg.softuni.pizza.model.views.OrderView;
import bg.softuni.pizza.service.OrderService;
import bg.softuni.pizza.service.ProductService;
import bg.softuni.pizza.service.exception.ObjectNotFoundException;

@Controller
public class OrderController {

	private final ProductService productService;
	private final OrderService orderService;

	public OrderController(ProductService productService, OrderService orderService) {
		this.productService = productService;
		this.orderService = orderService;
	}
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/order/{id}")
	public String getPizza(@PathVariable Long id, Model model) {

		OrderDto order = productService.findAById(id);

		model.addAttribute("order", order);
		return "order-new";
	}
	
	@PreAuthorize("isAuthenticated()")
	@PostMapping(value = "/order/{productId}", consumes = "application/json", produces = "application/json")
	@ResponseBody
	public ResponseEntity<Void> postNewOrder(@PathVariable("productId")  Long productId, @AuthenticationPrincipal UserDetails principal,
			@RequestBody @Valid OrderDto orderDto) {
		
		System.out.println(orderDto);
		
		orderService.createOrder(orderDto,principal);
		
		return ResponseEntity.status(HttpStatus.MOVED_PERMANENTLY).location(URI.create("/ordersForUser")).build(); 
	}
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/ordersForUser")
	public String getOrders(Model model,@AuthenticationPrincipal UserDetails principal) {

		List<OrderView> orders = orderService.findOrdersForCurrentUser(principal);

		model.addAttribute("orders", orders);
		return "cart";
	}
	
	@PreAuthorize("isAuthenticated()")
	@DeleteMapping(value = "/order/delete/{orderId}")
	public String deleteOrder(@PathVariable("orderId")  Long orderId, @AuthenticationPrincipal UserDetails principal) {
		
		
		orderService.deleteOrder(orderId,principal);
		
		return "redirect:/ordersForUser"; 
	}
	
	@PreAuthorize("isAuthenticated()")
	@PostMapping(value = "/order/activateOrder/", consumes = "application/json", produces = "application/json")
	@ResponseBody
	public void postActivateOrder(HttpServletResponse response,@AuthenticationPrincipal UserDetails principal,
			@RequestBody Long[] orderIds) throws IOException { 
		
		try {
		orderService.activateOrders(orderIds, principal);
		}catch(ObjectNotFoundException e) {
			response.sendRedirect("/ordersForUserWithErrorMessage");
			return;
		}
		
		response.sendRedirect("/ordersForUser");

	}
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/ordersForUserWithErrorMessage")
	public String getOrdersWithErrorMessage(Model model,@AuthenticationPrincipal UserDetails principal) {

		List<OrderView> orders = orderService.findOrdersForCurrentUser(principal);

		model.addAttribute("orders", orders);
		return "cart_with_error_msg";
	}

}
