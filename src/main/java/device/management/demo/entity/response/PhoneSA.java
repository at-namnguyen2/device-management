package device.management.demo.entity.response;

public class PhoneSA {
	
	private String name;
	private String phone;
	
	public PhoneSA() {
		super();
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}



	public PhoneSA(String phone) {
		super();
		this.phone = phone;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
}
