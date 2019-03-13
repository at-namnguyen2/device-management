package device.management.demo.entity.dto;

import java.io.Serializable;
import java.util.Date;

public class EmpDeviceDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String employeeName;
	private String email;
	private String team;
	private String avatar;
	private String deviceName;
	private String productId;
	private Date dateDeliverReceive;
	private Date dateReturn;
	private Long detailId;
	private Long employeeId;

	public EmpDeviceDTO() {
		super();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
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
	public Long getDetailId() {
		return detailId;
	}
	public void setDetailId(Long detailId) {
		this.detailId = detailId;
	}
	public Long getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}
	
}
