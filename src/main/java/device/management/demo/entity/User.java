package device.management.demo.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import javax.persistence.PrimaryKeyJoinColumn;

import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "user")
public class User implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "email", nullable = false, unique = true)
    private String email;
    
    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "active", nullable = false, columnDefinition = "TINYINT(1) default 0")
    private Boolean active = false;
    
    @Column(name = "non_del", nullable = false, columnDefinition = "TINYINT(1) default 1")
    private Boolean nonDel = true;
    
    @Column(name = "non_locked", nullable = false, columnDefinition = "TINYINT(1) default 1")
    private Boolean nonLocked = true;
    

    @Column(name = "description", nullable = true)
    private String description;
    
    // @JsonIgnore
    @JsonIgnoreProperties(value= "user")
	@OneToOne(mappedBy = "user",orphanRemoval=true)
	private TokenVerifition tokenVerifition;
    
    //@JsonIgnore
    @JsonIgnoreProperties(value= "user")
	@OneToOne(mappedBy = "user",cascade=CascadeType.ALL, orphanRemoval=true)
    //@OneToOne(mappedBy = "user")
	private BlockUser blockUser;
    
    //@JsonIgnore
    @JsonIgnoreProperties(value = "user")
	//@OneToOne(mappedBy = "user",cascade=CascadeType.ALL, orphanRemoval=true)
    @OneToOne(mappedBy = "user",cascade=CascadeType.ALL, orphanRemoval=true)

//	private Employee employee;
    
//    @OneToOne(cascade = {CascadeType.ALL})
//   // @PrimaryKeyJoinColumn
    private Employee employee;

    @JsonIgnore
    //@JsonIgnoreProperties(value = "user")
    //@OneToMany(mappedBy = "user",orphanRemoval=true)
	@OneToMany(mappedBy = "user")
	private List<UserRole> userRoles;
    
    public User() {
    	super();
    }
    public User(String email, String password) {
    	super();
    	this.email = email;
    	this.password = password;
    }

    
	public User(String email) {
		super();
		this.email = email;
	}
	public User(Long id, String email, String password, Boolean active, Boolean nonDel, Boolean nonLocked,

			String description, Employee employee, BlockUser blockUser, TokenVerifition tokenVeriftion, List<UserRole> userRoles) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;

		this.active = active;

		this.nonDel = nonDel;
		this.nonLocked = nonLocked;
		this.description = description;
		this.employee = employee;
		this.blockUser = blockUser;
		this.tokenVerifition = tokenVeriftion;
		this.userRoles = userRoles;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;

	}
	public Boolean getNonDel() {
		return nonDel;
	}
	public void setNonDel(Boolean nonDel) {
		this.nonDel = nonDel;
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
	public List<UserRole> getUserRoles() {
		return userRoles;
	}
	public void setUserRoles(List<UserRole> userRoles) {
		this.userRoles = userRoles;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public TokenVerifition getTokenVerifition() {
		return tokenVerifition;
	}
	public void setTokenVerifition(TokenVerifition tokenVerifition) {
		this.tokenVerifition = tokenVerifition;
	}
	public BlockUser getBlockUser() {
		return blockUser;
	}
	public void setBlockUser(BlockUser blockUser) {
		this.blockUser = blockUser;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	@Override
	public String toString() {

		return "User [id=" + id + ", email=" + email + ", password=" + password + ", enable=" + active + ", nonDel="

				+ nonDel + ", nonLocked=" + nonLocked + ", description=" + description + ", tokenVerifition="
				+ tokenVerifition + ", blockUser=" + blockUser + ", employee=" + employee + ", userRoles=" + userRoles
				+ "]";
	}

	public User (String mota, String email, Boolean active, Boolean nonDel) {
		this.description = mota;
		this.email = email;
		this.active = active;

		this.nonDel = nonDel;
	}
	
}
