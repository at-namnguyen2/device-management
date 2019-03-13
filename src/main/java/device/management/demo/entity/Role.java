package device.management.demo.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "role")
public class Role {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "role_name", nullable = false)
    private String roleName;
    
    @Column(name = "description", nullable = true)
    private String description;
    
    //@JsonIgnoreProperties({"role","user"})
	@OneToMany(mappedBy = "role")
	private List<UserRole> userRoles;
	
	public Role() {
		super();
	}
	
	public Role(String roleName, String description) {
		this.roleName = roleName;
		this.description = description;
	}

	public Role(Long id, String roleName, String description, List<UserRole> userRoles) {
		super();
		this.id = id;
		this.roleName = roleName;
		this.description = description;
		this.userRoles = userRoles;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
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
	 
}
