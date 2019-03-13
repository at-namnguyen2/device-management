package device.management.demo.entity.dto;
import javax.validation.constraints.NotBlank;

public class EditCatalogDTO {
	
	//@NotBlank(message = "description must not blank")
	private String description;
	 @NotBlank(message = "name must not blank")
	private String name;
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}	
}
