package device.management.demo.entity.response;

import java.io.Serializable;
import java.util.Date;

public class countResponse implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	Long quantity;
	Long working;
	Date lastUpdate;
	public countResponse() {
		super();
	}
	public Long getQuantity() {
		return quantity;
	}
	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}
	public Long getWorking() {
		return working;
	}
	public void setWorking(Long working) {
		this.working = working;
	}
	public Date getLastUpdate() {
		return lastUpdate;
	}
	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	
}
