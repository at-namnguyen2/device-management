package device.management.demo.entity.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class EditDeviceDTO {
	
	    @NotBlank(message = "name must not blank")
	    private String name;
	    //@NotBlank(message = "quantity must not blank")
	    @NotNull
		private Long quantity;
	    //@NotBlank(message = "price must not blank")
	    @NotNull
		private Double price;
	   // @NotBlank(message = "description must not blank")
		private String description;
	
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public Long getQuantity() {
			return quantity;
		}
		public void setQuantity(Long quantity) {
			this.quantity = quantity;
		}
		public Double getPrice() {
			return price;
		}
		public void setPrice(Double price) {
			this.price = price;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public EditDeviceDTO(String name, Long quantity, Double price, String description) {
			super();
			this.name = name;
			this.quantity = quantity;
			this.price = price;
			this.description = description;
		}
		
}
