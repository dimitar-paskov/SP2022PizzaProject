/**
 * @author dimitar
 *
 */
package bg.softuni.pizza.web;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import bg.softuni.pizza.model.views.OrderView;
import bg.softuni.pizza.service.OrderService;

@Controller
public class CookingController {
	
	private final OrderService orderService;

	public CookingController(OrderService orderService) {
		this.orderService = orderService;
	}
	
	@PreAuthorize("hasRole('ROLE_COOK')")
	@GetMapping("/cooking/getAllWaitingOrders")
	public String getAllWaitingOrders(Model model) {

		List<OrderView> orders = orderService.findWaitingOrders();

		model.addAttribute("orders", orders);
		return "orders_for_cooks";
	}
	
	@PreAuthorize("hasRole('ROLE_COOK')")
	@GetMapping("/cooking/getCookedOrders")
	public String getCookedOrders(Model model) {
		
		List<OrderView> orders = orderService.findCookedOrders();
		
		model.addAttribute("orders", orders);
		return "cooked_orders";
	}
	
	@PreAuthorize("hasRole('ROLE_COOK')")
	@PostMapping(value = "/cooking/start/{orderId}")
	public String startOrder(@PathVariable("orderId")  Long orderId, @AuthenticationPrincipal UserDetails principal) {
		
		orderService.startCooking(orderId, principal);
		
		return "redirect:/cooking/getAllWaitingOrders";  
	}
	
	
	@PreAuthorize("hasRole('ROLE_COOK')")
	@PostMapping(value = "/cooking/stop/{orderId}")
	public String stopOrder(@PathVariable("orderId")  Long orderId, @AuthenticationPrincipal UserDetails principal) {
		
		orderService.stopCooking(orderId, principal);
		
		return "redirect:/cooking/getAllWaitingOrders";  
	}
	
	

}
