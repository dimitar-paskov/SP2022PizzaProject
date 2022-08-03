package bg.softuni.pizza.service;

import java.util.List;
import java.util.Set;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import bg.softuni.pizza.model.dto.user.UserRegisterDTO;
import bg.softuni.pizza.model.entity.UserEntity;
import bg.softuni.pizza.model.entity.UserRoleEntity;
import bg.softuni.pizza.model.enums.UserRoleEnum;
import bg.softuni.pizza.model.mapper.UserMapper;
import bg.softuni.pizza.repository.UserRepository;
import bg.softuni.pizza.repository.UserRoleRepository;

@Service
public class UserService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final UserMapper userMapper;
	private final UserDetailsService userDetailsService;
	private final UserRoleRepository userRoleRepository;

	public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, UserMapper userMapper,
			UserDetailsService userDetailsService, UserRoleRepository userRoleRepository) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.userMapper = userMapper;
		this.userDetailsService = userDetailsService;
		this.userRoleRepository = userRoleRepository;
	}

	public void registerAndLogin(UserRegisterDTO userRegisterDTO) {

		UserEntity newUser = userMapper.userDtoToUserEntity(userRegisterDTO);
		newUser.setPassword(passwordEncoder.encode(userRegisterDTO.getPassword()));

		this.userRepository.save(newUser);
		login(newUser);
	}

	public void initUsersAndRoles() {
		initRoles();
		initUsers();
	}

	private void initUsers() {
		if (this.userRepository.count() == 0) {
			UserEntity userEntity = new UserEntity();
			UserRoleEntity adminRole = this.userRoleRepository.findByUserRole(UserRoleEnum.ADMIN);
			UserRoleEntity userRole = this.userRoleRepository.findByUserRole(UserRoleEnum.USER);

			userEntity.setFirstName("User").setLastName("Userov").setEmail("user@example.com").setPassword(this.passwordEncoder.encode("topsecret"))
					.setUserRoles(List.of(userRole));

			this.userRepository.save(userEntity);
			
			userEntity = new UserEntity();
			userEntity.setFirstName("Admin").setLastName("Adminov").setEmail("admin@example.com").setPassword(this.passwordEncoder.encode("topsecret"))
			.setUserRoles(List.of(userRole, adminRole));
			
			this.userRepository.save(userEntity);
			
			
		}
	}

	private void initRoles() {
		if (this.userRoleRepository.count() == 0) {
			UserRoleEntity admin = new UserRoleEntity();
			admin.setUserRole(UserRoleEnum.ADMIN);

			UserRoleEntity user = new UserRoleEntity();
			user.setUserRole(UserRoleEnum.USER);

			this.userRoleRepository.saveAll(Set.of(admin, user));
		}
	}

	private void login(UserEntity userEntity) {

		UserDetails userDetails = userDetailsService.loadUserByUsername(userEntity.getEmail());

		Authentication auth = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(),
				userDetails.getAuthorities());

		SecurityContextHolder.getContext().setAuthentication(auth);
	}

}
