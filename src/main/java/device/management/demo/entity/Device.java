package device.management.demo.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "device")
public class Device {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "device_catalog_id", nullable = false,foreignKey=@ForeignKey(name="ref_device_catalog"))
	private DeviceCatalog deviceCatalog;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "quantity")
	private Long quantity;
	
	@Column(name = "price", nullable = false)
	private Double price;
	
	@Column(name = "description", nullable = true)
	private String description;
	
	@JsonIgnore
	@OneToMany(mappedBy = "device", cascade=CascadeType.ALL)
	private List<DeviceDetail> deviceDetail;

	public Device() {
		super();
	}

	public Device(Long id) {
		this.id = id;
	}
	
	public Device(DeviceCatalog deviceCatalog, String name, Long quantity, Double price, String description) {
		super();
		this.deviceCatalog = deviceCatalog;
		this.name = name;
		this.quantity = quantity;
		this.price = price;
		this.description = description;
	}


	public Device(Long id, DeviceCatalog deviceCatalog, String name, Long quantity, Double price, String description,
			List<DeviceDetail> deviceDetail) {
		super();
		this.id = id;
		this.deviceCatalog = deviceCatalog;
		this.name = name;
		this.quantity = quantity;
		this.price = price;
		this.description = description;
		this.deviceDetail = deviceDetail;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public DeviceCatalog getDeviceCatalog() {
		return deviceCatalog;
	}

	public void setDeviceCatalog(DeviceCatalog deviceCatalog) {
		this.deviceCatalog = deviceCatalog;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<DeviceDetail> getDeviceDetail() {
		return deviceDetail;
	}

	public void setDeviceDetail(List<DeviceDetail> deviceDetail) {
		this.deviceDetail = deviceDetail;
	}   
}
