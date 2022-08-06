/**
 * @author dimitar
 *
 */
package bg.softuni.pizza.model.dto.user;

public class UserDto {
	
	private Long id;

	private String email;

	private String firstName;

	private String lastName;
	
	private boolean isAdmin;
	
	private boolean isUser;
	
	private boolean isCook;
	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public boolean isUser() {
		return isUser;
	}

	public void setUser(boolean isUser) {
		this.isUser = isUser;
	}

	public boolean isCook() {
		return isCook;
	}

	public void setCook(boolean isCook) {
		this.isCook = isCook;
	}
	
	

}
