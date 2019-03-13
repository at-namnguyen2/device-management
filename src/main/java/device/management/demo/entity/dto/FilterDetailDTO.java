package device.management.demo.entity.dto;

import java.io.Serializable;

public class FilterDetailDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String catalog;
	private String name;
	public FilterDetailDTO() {
		super();
	}
	public String getCatalog() {
		return catalog;
	}
	public void setCatalog(String catalog) {
		this.catalog = catalog;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
