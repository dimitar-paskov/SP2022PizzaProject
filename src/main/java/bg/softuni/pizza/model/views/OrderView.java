/**
 * @author dimitar
 *
 */
package bg.softuni.pizza.model.views;

import java.math.BigDecimal;

public class OrderView {
	
	private Long id;

	private Long productId;
	
	private String productName;
	
	private String productType;
	
	private String productSize;
	
	private String imageUrl;

	private String dateTimeCreated;
	
	private BigDecimal price;

	private Integer quantity;

	private BigDecimal totalPrice;
	
	private Long userId;

	private String userFirstName;
	
	private String userLastName;

	private boolean isOrdered;
	
	private String dateTimeStarted;
	
	private String dateTimeReady;
	
	private Long cookId;
	
	private String cookFirstName;
	
	private String cookLastName;
	
	private boolean isReady;
	
	private boolean isStarted;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getProductSize() {
		return productSize;
	}

	public void setProductSize(String productSize) {
		this.productSize = productSize;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getDateTimeCreated() {
		return dateTimeCreated;
	}

	public void setDateTimeCreated(String dateTimeCreated) {
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

	public String getDateTimeStarted() {
		return dateTimeStarted;
	}

	public void setDateTimeStarted(String dateTimeStarted) {
		this.dateTimeStarted = dateTimeStarted;
	}

	public String getDateTimeReady() {
		return dateTimeReady;
	}

	public void setDateTimeReady(String dateTimeReady) {
		this.dateTimeReady = dateTimeReady;
	}

	public Long getCookId() {
		return cookId;
	}

	public void setCookId(Long cookId) {
		this.cookId = cookId;
	}

	public String getCookFirstName() {
		return cookFirstName;
	}

	public void setCookFirstName(String cookFirstName) {
		this.cookFirstName = cookFirstName;
	}

	public String getCookLastName() {
		return cookLastName;
	}

	public void setCookLastName(String cookLastName) {
		this.cookLastName = cookLastName;
	}

	public boolean isReady() {
		return isReady;
	}

	public void setReady(boolean isReady) {
		this.isReady = isReady;
	}

	public boolean isStarted() {
		return isStarted;
	}

	public void setStarted(boolean isStarted) {
		this.isStarted = isStarted;
	}
	
	
	

}
