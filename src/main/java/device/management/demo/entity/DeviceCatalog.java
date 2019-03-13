package device.management.demo.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "device_catalog")
public class DeviceCatalog {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "description", nullable = true)

	private String description;
    
	@OneToMany(mappedBy = "deviceCatalog", orphanRemoval=true)
    private List<Device> device;
	
	public DeviceCatalog() {
		super();
	}

	public DeviceCatalog(Long id) {
		this.id = id;
	}
	
	public DeviceCatalog(String name) {
		super();
		this.name = name;
	}


	public DeviceCatalog(Long id, String name, String description) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
	}
	public DeviceCatalog(Long id, String name, String description, List<Device> device) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.device = device;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Device> getDevice() {
		return device;
	}

	public void setDevice(List<Device> device) {
		this.device = device;
	}
}
