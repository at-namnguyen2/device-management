package device.management.demo.entity.response;

import java.util.Map;

public class UserResponse2Return {
	
	private UserResponse2 userResponse2;
	private boolean validated;
	private Map<String, String> errorMessages;
	
	public UserResponse2 getUserResponse2() {
		return userResponse2;
	}
	public void setUserResponse2(UserResponse2 userResponse2) {
		this.userResponse2 = userResponse2;
	}
	public boolean isValidated() {
		return validated;
	}
	public void setValidated(boolean validated) {
		this.validated = validated;
	}
	public Map<String, String> getErrorMessages() {
		return errorMessages;
	}
	public void setErrorMessages(Map<String, String> errorMessages) {
		this.errorMessages = errorMessages;
	}
	
	
	
}