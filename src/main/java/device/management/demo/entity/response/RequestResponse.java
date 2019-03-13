package device.management.demo.entity.response;

import java.io.Serializable;
import java.util.Date;

import device.management.demo.entity.AppParamEntity;

public class RequestResponse implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String email;
	private String fullname;
	private String team;
	private String updatedate;
	private AppParamEntity type;
	private AppParamEntity status;
	private String content;
	private String contentReply;
	private String avatar;
	private long empId;
	
	
	
	public String getContentReply() {
		return contentReply;
	}

	public void setContentReply(String contentReply) {
		this.contentReply = contentReply;
	}
	
	public Long getEmpId() {
		return empId;
	}

	public void setEmpId(Long empId) {
		this.empId = empId;
	}

	public RequestResponse() {
		super();
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

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
	}

	public String getUpdatedate() {
		return updatedate;
	}

	public void setUpdatedate(String updatedate) {
		this.updatedate = updatedate;
	}

	public AppParamEntity getType() {
		return type;
	}

	public void setType(AppParamEntity type) {
		this.type = type;
	}

	public AppParamEntity getStatus() {
		return status;
	}

	public void setStatus(AppParamEntity status) {
		this.status = status;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	
}
