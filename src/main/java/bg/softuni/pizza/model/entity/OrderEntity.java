/**
 * @author dimitar
 *
 */
package bg.softuni.pizza.model.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import bg.softuni.pizza.model.enums.ProductCategoryEnum;
import bg.softuni.pizza.model.enums.ProductSizeEnum;

@Entity
@Table(name = "orders")
public class OrderEntity extends BaseEntity{
	
	@Column(nullable = false)
	private Long productId;
	
	@Column(nullable = false)
	private String productName;
	
	@Enumerated(EnumType.STRING)
	private ProductCategoryEnum productType;
	
	@Enumerated(EnumType.STRING)
	private ProductSizeEnum productSize;
	
	private String imageUrl;
	
	@Column(nullable = false)
	private LocalDateTime dateTimeCreated;
	
	@Column(nullable = false)
	private BigDecimal price;
	
	@Column(nullable = false)
	private Integer quantity;
	
	@Column(nullable = false)
	private BigDecimal totalPrice;
	
	@Column(nullable = false)
	private Long userId;
	
	@Column(nullable = false)
	private String userFirstName;
	
	@Column(nullable = false)
	private String userLastName;
	
	@Column(nullable = false)
	private boolean isOrdered;

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public ProductCategoryEnum getProductType() {
		return productType;
	}

	public void setProductType(ProductCategoryEnum productType) {
		this.productType = productType;
	}

	public ProductSizeEnum getProductSize() {
		return productSize;
	}

	public void setProductSize(ProductSizeEnum productSize) {
		this.productSize = productSize;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public LocalDateTime getDateTimeCreated() {
		return dateTimeCreated;
	}

	public void setDateTimeCreated(LocalDateTime dateTimeCreated) {
		this.dateTimeCreated = dateTimeCreated;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
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

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserFirstName() {
		return userFirstName;
	}

	public void setUserFirstName(String userFirstName) {
		this.userFirstName = userFirstName;
	}

	public String getUserLastName() {
		return userLastName;
	}

	public void setUserLastName(String userLastName) {
		this.userLastName = userLastName;
	}

	public boolean isOrdered() {
		return isOrdered;
	}

	public void setOrdered(boolean isOrdered) {
		this.isOrdered = isOrdered;
	}
	
	
	
	
	
	

}
