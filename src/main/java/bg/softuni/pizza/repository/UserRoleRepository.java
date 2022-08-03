package bg.softuni.pizza.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import bg.softuni.pizza.model.entity.UserRoleEntity;
import bg.softuni.pizza.model.enums.UserRoleEnum;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRoleEntity, Long> {

    UserRoleEntity findByUserRole(UserRoleEnum roleEnum);
}