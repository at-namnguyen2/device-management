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

@SuppressWarnings("serial")
@Entity
public class Request extends BaseEntity {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "content", nullable = false)
	private String content;
	
	@Column(name = "content_reply", nullable = true)
	private String contentReply;
	
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false, foreignKey = @ForeignKey(name = "ref_request_user"))
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "request_type", nullable = false, foreignKey = @ForeignKey(name = "ref_request_app_param1"))
	private AppParamEntity requestType;

	@ManyToOne
	@JoinColumn(name = "request_status", nullable = false, foreignKey = @ForeignKey(name = "ref_request_app_param2"))
	private AppParamEntity requestStatus;
	public Request() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	

	public String getContentReply() {
		return contentReply;
	}

	public void setContentReply(String contentReply) {
		this.contentReply = contentReply;
	}

	public AppParamEntity getRequestType() {
		return requestType;
	}

	public void setRequestType(AppParamEntity requestType) {
		this.requestType = requestType;
	}

	public AppParamEntity getRequestStatus() {
		return requestStatus;
	}

	public void setRequestStatus(AppParamEntity requestStatus) {
		this.requestStatus = requestStatus;
	}

	public Request(String content, String type, String status, Date updateDate, User user) {
		super();
		this.content = content;
		this.user = user;
	}

}