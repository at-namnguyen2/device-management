package device.management.demo.entity;

import java.util.Date;
import java.util.List;


import javax.persistence.CascadeType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import javax.persistence.PrimaryKeyJoinColumn;

import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @OneToOne
	@JoinColumn(name = "user_id", nullable = false,foreignKey=@ForeignKey(name="ref_user_employee"))
	private User user;
    

   // @OneToOne(mappedBy = "employee")
    //@PrimaryKeyJoinColumn
   	//private User user;

    @Column(name = "employee_name", nullable = false)
    private String employeeName;
    
    @Column(name = "address", nullable = true)
    private String address;
    
    @Column(name = "phone", nullable = true)
    private String phone;
    
    @Column(name = "gender", nullable = false, columnDefinition = "TINYINT(1) default 1")
    private Boolean gender = true;
    

    @Temporal(TemporalType.DATE)
	@Column(name = "date_of_birth", nullable = true)
	@CreationTimestamp
	private Date dateOfBirth;
    
    @Column(name = "team", nullable = false)
    private String team;
    
    @Column(name = "avatar", nullable = true)
    private String avatar;
    
    //@JsonIgnore
    @JsonIgnoreProperties("employee")
    @OneToMany(mappedBy = "employee",orphanRemoval = true)
    private List<Device_Deliver_Receive> deviceDeliverReceive;

	public Employee() {
		super();
	}

	public Employee(Long id, User user, String employeeName, String address, String phone, Boolean gender,
			Date dateOfBirth, String team, String avatar, List<Device_Deliver_Receive> deviceDeliverReceive) {
		super();
		this.id = id;
		this.user = user;
		this.employeeName = employeeName;
		this.address = address;
		this.phone = phone;
		this.gender = gender;
		this.dateOfBirth = dateOfBirth;
		this.team = team;
		this.avatar = avatar;
		this.deviceDeliverReceive = deviceDeliverReceive;
	}
	public Employee(String fullName, String phone, String address, Boolean gender, Date birthDay) {
		this.employeeName = fullName;
		this.phone = phone;
		this.address = address;
		this.gender = gender;
		this.dateOfBirth = birthDay;
	}
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Boolean getGender() {
		return gender;
	}

	public void setGender(Boolean gender) {
		this.gender = gender;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
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

	public List<Device_Deliver_Receive> getDeviceDeliverReceive() {
		return deviceDeliverReceive;
	}

	public void setDeviceDeliverReceive(List<Device_Deliver_Receive> deviceDeliverReceive) {
		this.deviceDeliverReceive = deviceDeliverReceive;
	}
}
