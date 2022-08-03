/**
 * @author dimitar
 *
 */
package bg.softuni.pizza.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import bg.softuni.pizza.model.entity.IngredientEntity;

@Repository
public interface IngredientRepository extends JpaRepository<IngredientEntity, Long> {
	
	Optional<IngredientEntity> findByName(String name);

}
