package device.management.demo.entity.response;

import java.util.Map;

import device.management.demo.entity.dto.CatalogDTO;

public class CatalogDTOResponse {
	
	private CatalogDTO catalogDTO;
	private boolean validated;
	private Map<String, String> errorMessages;
	
	public CatalogDTO getCatalogDTO() {
		return catalogDTO;
	}
	public void setCatalogDTO(CatalogDTO catalogDTO) {
		this.catalogDTO = catalogDTO;
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