package device.management.demo.entity.response;
import java.util.Map;

import device.management.demo.entity.dto.EditCatalogDTO;

public class EditCatalogDTOResponse {
	
	private EditCatalogDTO editCatalogDTOO;
	private boolean validated;
	private Map<String, String> errorMessages;
	public EditCatalogDTO getEditCatalogDTOO() {
		return editCatalogDTOO;
	}
	public void setEditCatalogDTOO(EditCatalogDTO editCatalogDTOO) {
		this.editCatalogDTOO = editCatalogDTOO;
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