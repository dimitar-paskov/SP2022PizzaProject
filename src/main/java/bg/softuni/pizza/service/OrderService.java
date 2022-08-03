/**
 * @author dimitar
 *
 */
package bg.softuni.pizza.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.validation.Valid;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import bg.softuni.pizza.model.dto.order.OrderDto;
import bg.softuni.pizza.model.entity.OrderEntity;
import bg.softuni.pizza.model.entity.ProductEntity;
import bg.softuni.pizza.model.enums.ProductSizeEnum;
import bg.softuni.pizza.model.user.PizzaUserDetails;
import bg.softuni.pizza.model.views.OrderView;
import bg.softuni.pizza.repository.OrderRepository;
import bg.softuni.pizza.repository.ProductRepository;
import bg.softuni.pizza.service.exception.ObjectNotFoundException;

@Service
public class OrderService {

	private final OrderRepository orderRepository;
	private final PizzaUserDetailsService pizzaUserDetailsService;
	private final ProductRepository productRepository;

	public OrderService(OrderRepository orderRepository, PizzaUserDetailsService pizzaUserDetailsService,
			ProductRepository productRepository) {
		this.orderRepository = orderRepository;
		this.pizzaUserDetailsService = pizzaUserDetailsService;
		this.productRepository = productRepository;

	}

	public List<OrderView> findOrdersForCurrentUser(@AuthenticationPrincipal UserDetails principal) {

		UserDetails userDetails = pizzaUserDetailsService.loadUserByUsername(principal.getUsername());

		List<OrderEntity> orders = orderRepository.findAllByUserIdAndIsOrdered(((PizzaUserDetails) userDetails).getId(),
				false);

		return orders.stream().map(this::map).toList();
	}

	private OrderView map(OrderEntity entity) {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

		OrderView view = new OrderView();
		view.setId(entity.getId());
		view.setProductId(entity.getProductId());
		view.setProductName(entity.getProductName());
		view.setProductType(entity.getProductType().name());
		view.setImageUrl(entity.getImageUrl());
		view.setDateTimeCreated(entity.getDateTimeCreated().format(formatter));
		view.setQuantity(entity.getQuantity());
		view.setPrice(entity.getPrice());
		view.setTotalPrice(entity.getTotalPrice());
		view.setUserId(entity.getUserId());
		view.setUserFirstName(entity.getUserFirstName());
		view.setUserLastName(entity.getUserLastName());
		view.setOrdered(entity.isOrdered());
		if (entity.getProductSize() != null) {
			view.setProductSize(entity.getProductSize().name());
		}

		return view;

	}

	public void createOrder(@Valid OrderDto orderDto, @AuthenticationPrincipal UserDetails principal) {

		OrderEntity orderEntity = new OrderEntity();

		ProductEntity productEntity = productRepository.findById(orderDto.getId())
				.orElseThrow(() -> new ObjectNotFoundException(orderDto.getId()));

		orderEntity.setProductId(productEntity.getId());
		orderEntity.setProductName(productEntity.getName());
		orderEntity.setProductType(productEntity.getCategory());
		if (orderDto.getSize() != null && !orderDto.getSize().equalsIgnoreCase("null")) {
			orderEntity.setProductSize(ProductSizeEnum.valueOf(orderDto.getSize().toUpperCase()));
		}
		orderEntity.setDateTimeCreated(LocalDateTime.now());
		orderEntity.setImageUrl(productEntity.getImageUrl());
		orderEntity.setOrdered(false);

		orderEntity.setQuantity(orderDto.getQuantity());

		BigDecimal adjustedPricePrice = productEntity.getPrice();
		if (orderDto.getSize().equals("Large")) {
			adjustedPricePrice = adjustedPricePrice.add(BigDecimal.valueOf(2.00));
		} else if (orderDto.getSize().equals("ExtraLarge")) {
			adjustedPricePrice = adjustedPricePrice.add(BigDecimal.valueOf(3.00));
		}
		orderEntity.setPrice(adjustedPricePrice);

		BigDecimal totalPrice = adjustedPricePrice.multiply(BigDecimal.valueOf(orderDto.getQuantity()));

		orderEntity.setTotalPrice(totalPrice);

		UserDetails userDetails = pizzaUserDetailsService.loadUserByUsername(principal.getUsername());

		orderEntity.setUserId(((PizzaUserDetails) userDetails).getId());
		orderEntity.setUserFirstName(((PizzaUserDetails) userDetails).getFirstName());
		orderEntity.setUserLastName(((PizzaUserDetails) userDetails).getLastName());

		orderRepository.save(orderEntity);

	}

	public void deleteOrder(Long orderId, @AuthenticationPrincipal UserDetails principal) {

		OrderEntity order = orderRepository.findById(orderId).orElseThrow(() -> new ObjectNotFoundException(orderId));

		UserDetails userDetails = pizzaUserDetailsService.loadUserByUsername(principal.getUsername());

		if (((PizzaUserDetails) userDetails).getId() == order.getUserId()) {

			orderRepository.deleteById(orderId);
		}

	}

	public void activateOrder(Long orderId, @AuthenticationPrincipal UserDetails principal) {

		OrderEntity order = orderRepository.findById(orderId).orElseThrow(() -> new ObjectNotFoundException(orderId));
		
		order.setOrdered(true); 

		UserDetails userDetails = pizzaUserDetailsService.loadUserByUsername(principal.getUsername());

		if (((PizzaUserDetails) userDetails).getId() == order.getUserId()) {

			orderRepository.save(order);
		}
	}

}
