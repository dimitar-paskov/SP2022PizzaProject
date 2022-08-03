/**
 * @author dimitar
 *
 */
package bg.softuni.pizza.model.dto.order;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class OrderDto {
	
	@NotNull
	private Long id;
	
	@NotNull
	private String name;
	
	private String imageUrl;
	
	@NotNull
    private BigDecimal price;
	
    @NotNull
    private String category;
	
	private String description;
	
	@Positive
	@NotNull
	private Integer quantity = 1;
	
	@NotNull
	private BigDecimal totalPrice;
	
	private String size;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	@Override
	public String toString() {
		return "OrderDto [id=" + id + ", name=" + name + ", imageUrl=" + imageUrl + ", price=" + price + ", category="
				+ category + ", description=" + description + ", quantity=" + quantity + ", totalPrice=" + totalPrice
				+ ", size=" + size + "]";
	}
	
	

}
