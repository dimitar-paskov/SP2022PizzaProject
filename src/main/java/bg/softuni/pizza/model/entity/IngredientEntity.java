/**
 * @author dimitar
 *
 */
package bg.softuni.pizza.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "ingredients")
public class IngredientEntity extends BaseEntity {
	
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = true)
	private String brand;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}
	
	

}
