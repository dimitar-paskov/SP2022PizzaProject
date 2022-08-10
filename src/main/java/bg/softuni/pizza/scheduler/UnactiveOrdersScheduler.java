/**
 * @author dimitar
 *
 */
package bg.softuni.pizza.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import bg.softuni.pizza.service.OrderService;

@Component
public class UnactiveOrdersScheduler {

	
	private final OrderService orderService; 

	public UnactiveOrdersScheduler(OrderService orderService ) {
		this.orderService = orderService;

	}
	
	@Scheduled(cron = "0 */1 * * * * ")
	public void deleteUnactiveOrders() {
		orderService.removeUnactiveOrders();
	}
	
	
	
	
	
}
