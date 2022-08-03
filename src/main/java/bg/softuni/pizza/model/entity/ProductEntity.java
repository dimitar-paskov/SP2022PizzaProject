/**
 * @author dimitar
 *
 */
package bg.softuni.pizza.model.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToMany;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.Table;

import bg.softuni.pizza.model.enums.ProductCategoryEnum;

@NamedEntityGraph(name = "product-ingredients", attributeNodes = {
		@NamedAttributeNode("ingredients")
})
@Entity
@Table(name = "products")
public class ProductEntity extends BaseEntity{
	
	private String name;
	
	private String imageUrl;
	
	@Column(nullable = false)
    private BigDecimal price;
	
	@Enumerated(EnumType.STRING)
    private ProductCategoryEnum category;
	
	@ManyToMany
	private List<IngredientEntity>ingredients = new ArrayList<>();
	
	@Column(nullable = true)
	private String description;

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

	public ProductCategoryEnum getCategory() {
		return category;
	}

	public void setCategory(ProductCategoryEnum category) {
		this.category = category;
	}

	public List<IngredientEntity> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<IngredientEntity> ingredients) {
		this.ingredients = ingredients;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}  
	
	
	
	

}
