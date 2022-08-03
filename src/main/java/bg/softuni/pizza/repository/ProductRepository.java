/**
 * @author dimitar
 *
 */
package bg.softuni.pizza.repository;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import bg.softuni.pizza.model.entity.ProductEntity;
import bg.softuni.pizza.model.enums.ProductCategoryEnum;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

	@EntityGraph(value = "product-ingredients")
	@Query("SELECT p FROM ProductEntity p WHERE p.category = ?1")
	List<ProductEntity> findAllByCategory(ProductCategoryEnum pizza); 

}
