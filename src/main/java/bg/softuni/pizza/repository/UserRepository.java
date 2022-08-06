package bg.softuni.pizza.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import bg.softuni.pizza.model.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

	@EntityGraph(value = "user-userRoles")
	@Query("SELECT u FROM UserEntity u WHERE u.email = ?1")
	Optional<UserEntity> findByEmail(String email);
}
