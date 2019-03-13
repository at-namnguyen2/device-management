package device.management.demo.entity.response;

public class DeviceQuantityResponse {
	private String catalogName;
	private String catalogIcon;
	private Long total;
	private Long woking;
	private Long error;
	private Long notUse;
	public DeviceQuantityResponse() {
		super();
	}
	
	public DeviceQuantityResponse(String catalogName, String catalogIcon, Long total, Long woking, Long error,
			Long notUse) {
		super();
		this.catalogName = catalogName;
		this.catalogIcon = catalogIcon;
		this.total = total;
		this.woking = woking;
		this.error = error;
		this.notUse = notUse;
	}

	public String getCatalogName() {
		return catalogName;
	}
	public void setCatalogName(String catalogName) {
		this.catalogName = catalogName;
	}
	public String getCatalogIcon() {
		return catalogIcon;
	}
	public void setCatalogIcon(String catalogIcon) {
		this.catalogIcon = catalogIcon;
	}
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
	public Long getWoking() {
		return woking;
	}
	public void setWoking(Long woking) {
		this.woking = woking;
	}
	public Long getError() {
		return error;
	}
	public void setError(Long error) {
		this.error = error;
	}
	public Long getNotUse() {
		return notUse;
	}
	public void setNotUse(Long notUse) {
		this.notUse = notUse;
	}
	
	
}
