package device.management.demo.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "employee_device_detail")
public class Device_Deliver_Receive {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    //@JsonIgnoreProperties("employee_device_detail")

    @ManyToOne
	@JoinColumn(name = "employee_id", nullable = false,foreignKey=@ForeignKey(name="ref_employee_device_detail"))
	private Employee employee;
    
    @JsonIgnoreProperties("deviceDeliverReceive")
   // @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "device_detail_id", nullable = false, foreignKey=@ForeignKey(name="ref_device_detail_employee"))
    private DeviceDetail deviceDetail;
    
    @Column(name = "date_deliver_receive")
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	private Date dateDeliverReceive;
    
    @Column(name = "date_return")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateReturn;

	public Device_Deliver_Receive() {
		super();
	}
	public Device_Deliver_Receive(Employee employee) {
		this.employee = employee;
	}

	public Device_Deliver_Receive(Long id, Employee employee, DeviceDetail deviceDetail, Date dateDeliverReceive,
			Date dateReturn) {
		super();
		this.id = id;
		this.employee = employee;
		this.deviceDetail = deviceDetail;
		this.dateDeliverReceive = dateDeliverReceive;
		this.dateReturn = dateReturn;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public DeviceDetail getDeviceDetail() {
		return deviceDetail;
	}

	public void setDeviceDetail(DeviceDetail deviceDetail) {
		this.deviceDetail = deviceDetail;
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
	
	@Override
	public String toString() {

		return "DevDeRe [id=" + id + ", deviceDetail=" + deviceDetail + ", dateDeliverReceive=" + dateDeliverReceive + ", dateReturn=" + dateReturn  + ", employee=" + employee + "]";
	}
}
