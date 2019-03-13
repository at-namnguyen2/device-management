package device.management.demo.entity.response;

import java.io.Serializable;
import java.util.Date;


public class UserResponse2 implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	private String email;
	private Boolean active;
	private Boolean nonLocked;
	private String description;
	private Boolean nonDel;
	private String avatar;
	private String employeeName;
	private String team;
	private String phone;
	private Date birthday;
	private String address;
	private Boolean gender;
	private String roleName;
	private String pass;
	private String descriptionRole;
	
	public UserResponse2() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Boolean getNonLocked() {
		return nonLocked;
	}

	public void setNonLocked(Boolean nonLocked) {
		this.nonLocked = nonLocked;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getNonDel() {
		return nonDel;
	}

	public void setNonDel(Boolean nonDel) {
		this.nonDel = nonDel;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Boolean getGender() {
		return gender;
	}

	public void setGender(Boolean gender) {
		this.gender = gender;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getDescriptionRole() {
		return descriptionRole;
	}

	public void setDescriptionRole(String descriptionRole) {
		this.descriptionRole = descriptionRole;
	}
	

	
	}
