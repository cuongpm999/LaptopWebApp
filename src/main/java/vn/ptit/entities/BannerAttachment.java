package vn.ptit.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_banner_attachment")
public class BannerAttachment extends BaseEntity {
	@Column(name = "name", length = 100, nullable = false)
	private String name;

	@Column(name = "path", length = 100, nullable = false)
	private String path;

	@Column(name = "mime", length = 100, nullable = false)
	private String mime;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "banner_id")
	private Banner banner;

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

	public Banner getBanner() {
		return banner;
	}

	public void setBanner(Banner banner) {
		this.banner = banner;
	}
	
}
