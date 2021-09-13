package vn.ptit.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_user_attachment")
public class UserAttachment extends BaseEntity {
	@Column(name = "name", length = 100, nullable = false)
	private String name;

	@Column(name = "path", length = 100, nullable = false)
	private String path;

	@Column(name = "mime", length = 100, nullable = false)
	private String mime;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	private UserInfo userInfo;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getMime() {
		return mime;
	}

	public void setMime(String mime) {
		this.mime = mime;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}
	
}
