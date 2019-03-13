package device.management.demo.entity.response;

import java.util.Map;

import device.management.demo.entity.dto.EditDeviceDTO;

public class EditDeviceDTOResponse {
    private EditDeviceDTO editDeviceDTO;
    private boolean validated;
    private Map<String, String> errorMessages;
    
	public EditDeviceDTO getEditDeviceDTO() {
		return editDeviceDTO;
	}
	public void setEditDeviceDTO(EditDeviceDTO editDeviceDTO) {
		this.editDeviceDTO = editDeviceDTO;
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
