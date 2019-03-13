package device.management.demo.entity.dto;

import java.io.Serializable;
import java.util.Date;

public class DetailDTO implements Serializable {

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
	private long status;
	private Date updatedate;

	public DetailDTO() {
		super();
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

	public long getStatus() {
		return status;
	}

	public void setStatus(long status) {
		this.status = status;
	}

	public Date getUpdatedate() {
		return updatedate;
	}

	public void setUpdatedate(Date updatedate) {
		this.updatedate = updatedate;
	}

	@Override
	public String toString() {

		return "details [catalogname=" + catalogname + ", devicename=" + devicename + ", price=" + price
				+ ", decription=" + decription + ", productid=" + productid + ", status=" + status + ", updatedate="
						+ updatedate + "]";
	}

}
