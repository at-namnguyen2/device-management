package device.management.demo.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "token_verifition")
public class TokenVerifition {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne
	@JoinColumn(name = "user_id", unique = true, nullable = false)
	private User user;
	
	@Column(name = "token_code", nullable = false)
	private String tokenCode;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "expire_time", nullable = false)
	private Date expireTime;
	
	
	public TokenVerifition() {
		super();
	}

	public TokenVerifition(User user, String tokenCode, Date expireTime) {
		super();
		this.user = user;
		this.tokenCode = tokenCode;
		this.expireTime = expireTime;
		
	}
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getTokenCode() {
		return tokenCode;
	}

	public void setTokenCode(String tokenCode) {
		this.tokenCode = tokenCode;
	}

	public Date getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(Date expireTime) {
		this.expireTime = expireTime;
	}
	
}
