/**
 * @author dimitar
 *
 */
package bg.softuni.pizza.service;

import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import bg.softuni.pizza.model.entity.UserEntity;
import bg.softuni.pizza.model.entity.UserRoleEntity;
import bg.softuni.pizza.model.enums.UserRoleEnum;
import bg.softuni.pizza.model.user.PizzaUserDetails;
import bg.softuni.pizza.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
public class PizzaUserDetailsServiceTest {

	private PizzaUserDetailsService toTest;

	@Mock
	private UserRepository mockUserRepository;

	@BeforeEach
	void setUp() {
		toTest = new PizzaUserDetailsService(mockUserRepository);
	}

	@Test
	void testLoadUserByUsername_UserExists() {
		// arrange
		UserEntity testUserEntity = new UserEntity()
				.setEmail("pesho@example.com")
				.setPassword("topsecret")
				.setFirstName("Pesho")
				.setLastName("Petrov")
				.setActive(true)
				.setImageUrl("http://image.com/image")
				.setUserRoles(List.of(
						new UserRoleEntity().setUserRole(UserRoleEnum.ADMIN),
						new UserRoleEntity().setUserRole(UserRoleEnum.USER)
				));

		when(mockUserRepository.findByEmail(testUserEntity.getEmail())).thenReturn(Optional.of(testUserEntity));

		// act
		PizzaUserDetails userDetails = (PizzaUserDetails) toTest.loadUserByUsername(testUserEntity.getEmail());

		// assert
		Assertions.assertEquals(testUserEntity.getEmail(), userDetails.getUsername());

		Assertions.assertEquals(testUserEntity.getFirstName(), userDetails.getFirstName());
		Assertions.assertEquals(testUserEntity.getLastName(), userDetails.getLastName());
		Assertions.assertEquals(testUserEntity.getPassword(), userDetails.getPassword());
		Assertions.assertEquals(testUserEntity.getFirstName() + " " + testUserEntity.getLastName(),
				userDetails.getFullName());

		var authorities = userDetails.getAuthorities();
		Assertions.assertEquals(2, authorities.size());

		var authoritiesIter = authorities.iterator();

		Assertions.assertEquals("ROLE_" + UserRoleEnum.ADMIN.name(), authoritiesIter.next().getAuthority());
		Assertions.assertEquals("ROLE_" + UserRoleEnum.USER.name(), authoritiesIter.next().getAuthority());

	}

	@Test
	void testLoadUserByUsername_UserDoesNotExist() {

		Assertions.assertThrows(UsernameNotFoundException.class,
				() -> toTest.loadUserByUsername("non-existent@example.com"));

	}

}
