/**
 * @author dimitar
 *
 */
package bg.softuni.pizza.model.dto.product;

import java.math.BigDecimal;

import org.springframework.web.multipart.MultipartFile;

public class ProductDto {
	
	private String name;
	
	private String imageUrl;
	
    private BigDecimal price;
	
    private String category;
    
	private String description;
	
	private MultipartFile picture;

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

	public MultipartFile getPicture() {
		return picture;
	}

	public void setPicture(MultipartFile picture) {
		this.picture = picture;
	}
    
    
    

}
