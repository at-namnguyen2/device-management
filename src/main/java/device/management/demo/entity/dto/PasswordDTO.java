package device.management.demo.entity.dto;

import javax.validation.constraints.NotBlank;

import device.management.demo.validation.Password;

public class PasswordDTO {
	
	private String token;
	private String email;
	
	@NotBlank(message = "passCurrent must not blank")
	private String passwordCurrent;
	
	@NotBlank(message = "newPassword must not blank")
	@Password(message="Password has at least one number, one special character, one uppecase character and has from 6 to 10 character")
	private String newPassword;	
	
	@NotBlank(message = "newMatchingPassword mut not blank")
	private String newMatchingPassword;
	public PasswordDTO() {
		super();
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPasswordCurrent() {
		return passwordCurrent;
	}
	public void setPasswordCurrent(String passwordCurrent) {
		this.passwordCurrent = passwordCurrent;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public String getNewMatchingPassword() {
		return newMatchingPassword;
	}
	public void setNewMatchingPassword(String newMatchingPassword) {
		this.newMatchingPassword = newMatchingPassword;
	}

}
