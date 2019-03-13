package device.management.demo.entity.response;

import java.util.Map;

import device.management.demo.entity.dto.CatalogDTO;
import device.management.demo.entity.dto.PasswordDTO;

public class PasswordDTOResponse {
	private PasswordDTO passwordDTO;
	private boolean validated;
	private Map<String, String> errorMessages;
	public PasswordDTO getPasswordDTO() {
		return passwordDTO;
	}
	public void setPasswordDTO(PasswordDTO passwordDTO) {
		this.passwordDTO = passwordDTO;
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
