package device.management.demo.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "device_detail")
public class DeviceDetail {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    
	//@JsonIgnoreProperties("device_detail")
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "device_id", nullable = false, foreignKey = @ForeignKey(name = "ref_user_role"))
	private Device device;

	@Column(name = "product_id", nullable = false)
	private String productId;
	
	@Column(name = "device_detail_description", nullable = true)
	private String descriptionDeviceDetail;

	@Column(name = "status", columnDefinition = "TINYINT(1) default 1", nullable = false)
	private long status = 1;


	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "update_date", nullable = false)
	@CreationTimestamp
	private Date updateDate;
    
	
	@JsonIgnoreProperties("deviceDetail")
	//@JsonIgnore
	@OneToMany(mappedBy = "deviceDetail", orphanRemoval = true)
	@Cascade({ CascadeType.ALL})
	private List<Device_Deliver_Receive> deviceDeliverReceive;


	public DeviceDetail() {
		super();
	}
	
	public DeviceDetail(Device device, String productId, long status, Date updateDate) {
		super();
		this.device = device;
		this.productId = productId;
		this.status = status;
		this.updateDate = updateDate;
	}

	public DeviceDetail(Long id, Device device, String productId, long status, Date updateDate, List<Device_Deliver_Receive> deviceDeliverReceive) {
		super();
		this.id = id;
		this.device = device;
		this.productId = productId;
		this.status = status;
		this.updateDate = updateDate;
		this.deviceDeliverReceive = deviceDeliverReceive;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	


	public Device getDevice() {
		return device;
	}

	public void setDevice(Device device) {
		this.device = device;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public long getStatus() {
		return status;
	}

	public void setStatus(long status) {
		this.status = status;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getDescriptionDeviceDetail() {
		return descriptionDeviceDetail;
	}
	public void setDescriptionDeviceDetail(String descriptionDeviceDetail) {
		this.descriptionDeviceDetail = descriptionDeviceDetail;
	}
	public List<Device_Deliver_Receive> getDeviceDeliverReceive() {
		return deviceDeliverReceive;
	}
	public void setDeviceDeliverReceive(List<Device_Deliver_Receive> deviceDeliverReceive) {
		this.deviceDeliverReceive = deviceDeliverReceive;
	}
	
}
