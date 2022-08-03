/**
 * @author dimitar
 *
 */
package bg.softuni.pizza.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import bg.softuni.pizza.model.entity.UserEntity;
import bg.softuni.pizza.model.entity.UserRoleEntity;
import bg.softuni.pizza.model.user.PizzaUserDetails;
import bg.softuni.pizza.repository.UserRepository;

public class PizzaUserDetailsService implements UserDetailsService{
	
	private final UserRepository userRepository;

	public PizzaUserDetailsService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		return userRepository.findByEmail(username).map(this::map)
		.orElseThrow(()->new UsernameNotFoundException("User with email" + username + " not found"));

	}
	
	private UserDetails map(UserEntity userEntity) {
		
		return new PizzaUserDetails( 
				
				userEntity.getPassword(), 
				userEntity.getEmail(), 
				userEntity.getFirstName(), 
				userEntity.getLastName(),
				userEntity.getUserRoles().stream().map(this::map).toList()
			);
		
		
//		return User.builder()
//				.username(userEntity.getEmail())
//				.password(userEntity.getPassword())
//				.authorities(userEntity.getUserRoles().stream().map(this::map).toList())
//				.build();

		
	}
	
	private GrantedAuthority map(UserRoleEntity userRole) {
		
		return new SimpleGrantedAuthority("ROLE_" + userRole.getUserRole().name());
		
	}

}
