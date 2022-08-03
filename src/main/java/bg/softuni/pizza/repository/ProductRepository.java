/**
 * @author dimitar
 *
 */
package bg.softuni.pizza.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import bg.softuni.pizza.model.entity.ProductEntity;
import bg.softuni.pizza.model.enums.ProductCategoryEnum;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

	List<ProductEntity> findAllByCategory(ProductCategoryEnum pizza); 

}
