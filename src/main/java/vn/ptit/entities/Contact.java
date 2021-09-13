package vn.ptit.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Luu tru du lieu nguoi dung day len tu tang View
 * @author Pham Cuong
 *
 */
@Entity
@Table(name = "tbl_contact")
public class Contact extends BaseEntity {
	
	@Column(name = "fullName", length = 100, nullable = false)
	private String fullName;
	
	@Column(name = "email", length = 100, nullable = false)
	private String email;

	@Column(name = "mobile", length = 100, nullable = false)
	private String mobile;
	
	@Column(name = "content", length = 1000, nullable = true)
	private String content;
	
	@Column(name = "status", nullable = true)
	private Boolean status;

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

}
