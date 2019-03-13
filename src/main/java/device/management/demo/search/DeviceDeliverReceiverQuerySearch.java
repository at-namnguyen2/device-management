package device.management.demo.search;

import java.util.Date;

public class DeviceDeliverReceiverQuerySearch {
    private String employeeName;
	private String email;
	private String team;
	private String deviceName;
	private String productId;
	private Date dateDeliverReceive;
	private Date dateReturn;
	
	public DeviceDeliverReceiverQuerySearch() {
		super();
	}



	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public Date getDateDeliverReceive() {
		return dateDeliverReceive;
	}

	public void setDateDeliverReceive(Date dateDeliverReceive) {
		this.dateDeliverReceive = dateDeliverReceive;
	}

	public Date getDateReturn() {
		return dateReturn;
	}

	public void setDateReturn(Date dateReturn) {
		this.dateReturn = dateReturn;
	}
	
	
}
