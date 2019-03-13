package device.management.demo.entity.response;

import java.io.Serializable;
import java.util.Date;

public class DetailResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String catalogname;
	private String devicename;
	private Double price;
	private String decription;
	private String productid;
	private Date updatedate;
	private Long status;
	private String iconCatalog;
	public DetailResponse() {
		super();
	}
	
	


	public String getIconCatalog() {
		return iconCatalog;
	}




	public void setIconCatalog(String iconCatalog) {
		this.iconCatalog = iconCatalog;
	}




	public Long getStatus() {
		return status;
	}


	public void setStatus(Long status) {
		this.status = status;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCatalogname() {
		return catalogname;
	}
	public void setCatalogname(String catalogname) {
		this.catalogname = catalogname;
	}
	public String getDevicename() {
		return devicename;
	}
	public void setDevicename(String devicename) {
		this.devicename = devicename;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getDecription() {
		return decription;
	}
	public void setDecription(String decription) {
		this.decription = decription;
	}
	public String getProductid() {
		return productid;
	}
	public void setProductid(String productid) {
		this.productid = productid;
	}
	public Date getUpdatedate() {
		return updatedate;
	}
	public void setUpdatedate(Date updatedate) {
		this.updatedate = updatedate;
	}
	
}
