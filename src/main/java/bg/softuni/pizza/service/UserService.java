package bg.softuni.pizza.service;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import bg.softuni.pizza.model.dto.user.UserDto;
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
		
		UserRoleEntity userRole = this.userRoleRepository.findByUserRole(UserRoleEnum.USER);
		
		newUser.getUserRoles().add(userRole);
		

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
			UserRoleEntity cookRole = this.userRoleRepository.findByUserRole(UserRoleEnum.COOK);

			userEntity.setFirstName("User").setLastName("Userov").setEmail("user@example.com")
					.setPassword(this.passwordEncoder.encode("topsecret")).setUserRoles(List.of(userRole));

			this.userRepository.save(userEntity);

			userEntity = new UserEntity();
			userEntity.setFirstName("Admin").setLastName("Adminov").setEmail("admin@example.com")
					.setPassword(this.passwordEncoder.encode("topsecret")).setUserRoles(List.of(userRole, adminRole, cookRole));

			this.userRepository.save(userEntity);

			userEntity = new UserEntity();
			userEntity.setFirstName("Cook").setLastName("Cookinski").setEmail("cook@example.com")
					.setPassword(this.passwordEncoder.encode("topsecret")).setUserRoles(List.of(cookRole));

			this.userRepository.save(userEntity);

		}
	}

	private void initRoles() {
		if (this.userRoleRepository.count() == 0) {
			UserRoleEntity admin = new UserRoleEntity();
			admin.setUserRole(UserRoleEnum.ADMIN);

			UserRoleEntity user = new UserRoleEntity();
			user.setUserRole(UserRoleEnum.USER);

			UserRoleEntity cook = new UserRoleEntity();
			cook.setUserRole(UserRoleEnum.COOK);

			this.userRoleRepository.saveAll(Set.of(admin, user, cook));
		}
	}

	private void login(UserEntity userEntity) {

		UserDetails userDetails = userDetailsService.loadUserByUsername(userEntity.getEmail());

		Authentication auth = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(),
				userDetails.getAuthorities());

		SecurityContextHolder.getContext().setAuthentication(auth);
	}

	public List<UserDto> findAllUsers() {

		List<UserEntity> userEntities = userRepository.findAll();

		return userEntities.stream().map(this::map).toList();
	}

	private UserDto map(UserEntity entity) {

		UserDto user = new UserDto();

		user.setId(entity.getId());
		user.setEmail(entity.getEmail());
		user.setFirstName(entity.getFirstName());
		user.setLastName(entity.getLastName());

		user.setAdmin(entity.getUserRoles().stream().map(ur -> ur.getUserRole().name())
				.filter(r -> r.equalsIgnoreCase("admin")).count() > 0);
		user.setCook(entity.getUserRoles().stream().map(ur -> ur.getUserRole().name())
				.filter(r -> r.equalsIgnoreCase("cook")).count() > 0);
		user.setUser(entity.getUserRoles().stream().map(ur -> ur.getUserRole().name())
				.filter(r -> r.equalsIgnoreCase("user")).count() > 0);

		return user;

	}

	public void addAdminRole(Long userId) {

		UserEntity user = userRepository.findById(userId).orElseThrow();
		UserRoleEntity adminRole = this.userRoleRepository.findByUserRole(UserRoleEnum.ADMIN);

		if (user.getUserRoles().stream().map(ur -> ur.getUserRole().name()).filter(r -> r.equalsIgnoreCase("admin"))
				.count() == 0) {

			user.getUserRoles().add(adminRole);
			userRepository.save(user);
		}

	}

	public void addUserRole(Long userId) {

		UserEntity user = userRepository.findById(userId).orElseThrow();
		UserRoleEntity userRole = this.userRoleRepository.findByUserRole(UserRoleEnum.USER);
		if (user.getUserRoles().stream().map(ur -> ur.getUserRole().name()).filter(r -> r.equalsIgnoreCase("user"))
				.count() == 0) {
			user.getUserRoles().add(userRole);
			userRepository.save(user);
		}
	}

	public void addCookRole(Long userId) {

		UserEntity user = userRepository.findById(userId).orElseThrow();
		UserRoleEntity cookRole = this.userRoleRepository.findByUserRole(UserRoleEnum.COOK);
		if (user.getUserRoles().stream().map(ur -> ur.getUserRole().name()).filter(r -> r.equalsIgnoreCase("cook"))
				.count() == 0) {
			user.getUserRoles().add(cookRole);
			userRepository.save(user);
		}
	}

	public void removeAdminRole(Long userId) {

		UserEntity user = userRepository.findById(userId).orElseThrow();

		for (Iterator<UserRoleEntity> iterator = user.getUserRoles().iterator(); iterator.hasNext();) {

			UserRoleEntity value = iterator.next();
			    if (value.getUserRole().equals(UserRoleEnum.ADMIN)) {
			        iterator.remove();
			    }

		}
		

		userRepository.save(user);
	}
	
	
	public void removeUserRole(Long userId) {

		UserEntity user = userRepository.findById(userId).orElseThrow();

		for (Iterator<UserRoleEntity> iterator = user.getUserRoles().iterator(); iterator.hasNext();) {

			UserRoleEntity value = iterator.next();
			    if (value.getUserRole().equals(UserRoleEnum.USER)) {
			        iterator.remove();
			    }

		}
		

		userRepository.save(user);
	}
	
	public void removeCookRole(Long userId) {

		UserEntity user = userRepository.findById(userId).orElseThrow();

		for (Iterator<UserRoleEntity> iterator = user.getUserRoles().iterator(); iterator.hasNext();) {

			UserRoleEntity value = iterator.next();
			    if (value.getUserRole().equals(UserRoleEnum.COOK)) {
			        iterator.remove();
			    }

		}
		

		userRepository.save(user);
	}

	public void deleteUser(Long userId) { 
		UserEntity user = userRepository.findById(userId).orElseThrow();
		userRepository.delete(user); 
	}

}
