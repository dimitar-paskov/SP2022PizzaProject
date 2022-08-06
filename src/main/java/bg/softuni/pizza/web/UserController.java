package bg.softuni.pizza.web;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import bg.softuni.pizza.model.dto.user.UserDto;
import bg.softuni.pizza.service.UserService;

@Controller
@RequestMapping("/users")
public class UserController {
	
	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

  @GetMapping("/login")
  public String login() {
    return "auth-login";
  }
  
  @GetMapping("/all")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public String getAll(Model model) {
	  
	
	List<UserDto> users = userService.findAllUsers(); 
	
	model.addAttribute("users", users);
	  
	  
    return "users_all";
  }
  
  @PostMapping("/addAdminRole/{userId}")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public String addAdminRole(@PathVariable("userId") Long userId) {
	  
	  userService.addAdminRole(userId);
	  
	  return "redirect:/users/all";
	  
  }
  
  @PostMapping("/addUserRole/{userId}")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public String addUserRole(@PathVariable("userId") Long userId) {
	  
	  userService.addUserRole(userId);
	  
	  return "redirect:/users/all";
	  
  }
  
  @PostMapping("/addCookRole/{userId}")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public String addCookRole(@PathVariable("userId") Long userId) {
	  
	  userService.addCookRole(userId);
	  
	  return "redirect:/users/all";
	  
  }
  
  
  @PostMapping("/removeAdminRole/{userId}")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public String removeAdminRole(@PathVariable("userId") Long userId) {
	  
	  userService.removeAdminRole(userId);
	  
	  return "redirect:/users/all";
	  
  }
  
  @PostMapping("/removeUserRole/{userId}")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public String removeUserRole(@PathVariable("userId") Long userId) {
	  
	  userService.removeUserRole(userId);
	  
	  return "redirect:/users/all";
	  
  }
  
  @PostMapping("/removeCookRole/{userId}")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public String removeCookRole(@PathVariable("userId") Long userId) {
	  
	  userService.removeCookRole(userId);
	  
	  return "redirect:/users/all";
	  
  }
  @DeleteMapping("/delete/{userId}")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public String deleteUser(@PathVariable("userId") Long userId) {
	  
	  userService.deleteUser(userId);
	  
	  return "redirect:/users/all";
	  
  }

}
