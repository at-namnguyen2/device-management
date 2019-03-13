package device.management.demo.entity.response;

import java.util.Map;

import device.management.demo.entity.dto.DeviceDTO;

public class DeviceDTOResponse {
	private DeviceDTO deviceDTO;
	private boolean validated;
	private Map<String, String> errorMessages;
	public DeviceDTO getDeviceDTO() {
		return deviceDTO;
	}
	public void setDeviceDTO(DeviceDTO deviceDTO) {
		this.deviceDTO = deviceDTO;
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
