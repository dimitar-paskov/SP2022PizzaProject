/**
 * @author dimitar
 *
 */
package bg.softuni.pizza.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import bg.softuni.pizza.model.entity.OrderEntity;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

	List<OrderEntity> findAllByUserIdAndIsOrdered(Long id,boolean isOrdered);  
	
	List<OrderEntity> findAllByIsOrderedAndIsReady(boolean isOrdered, boolean isReady);
		
	List<OrderEntity> findAllByIsOrdered(boolean isOrdered);  

}
