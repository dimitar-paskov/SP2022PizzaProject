/**
 * @author dimitar
 *
 */
package bg.softuni.pizza.model.user;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class PizzaUserDetails implements UserDetails {

	private static final long serialVersionUID = 1L;
	private final Long id;
	private final String password;
	private final String username;
	private final String firstName;
	private final String lastName;
	private final Collection<GrantedAuthority> authorities;

	public PizzaUserDetails(Long id,String password, String username, String firstName, String lastName,
			Collection<GrantedAuthority> authorities) {
		this.id = id;
		this.password = password;
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.authorities = authorities;

	}
	
	

	public Long getId() {
		return id;
	}



	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getFullName() {

		StringBuilder fullName = new StringBuilder();
		if (firstName != null) {
			fullName.append(firstName);
		}
		if (lastName != null) {
			if (!fullName.isEmpty()) {
				fullName.append(" ");
			}

			fullName.append(lastName);

		}

		return fullName.toString();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		return authorities;
	}

	@Override
	public String getPassword() {

		return password;
	}

	@Override
	public String getUsername() {

		return username;
	}

	@Override
	public boolean isAccountNonExpired() {

		return true;
	}

	@Override
	public boolean isAccountNonLocked() {

		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {

		return true;
	}

	@Override
	public boolean isEnabled() {

		return true;
	}

}
