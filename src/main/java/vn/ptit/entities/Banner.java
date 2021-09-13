package vn.ptit.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_banner")
public class Banner extends BaseEntity {
	
	@Column(name = "ngayTao", length = 1000, nullable = false)
	private String ngayTao;
	
	@Column(name = "status", nullable = true)
	private Boolean status;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "banner", fetch = FetchType.LAZY)
	private List<BannerAttachment> bannerAttachments = new ArrayList<BannerAttachment>();

	public void addBannerAttachment(BannerAttachment bannerAttachment) {
		bannerAttachments.add(bannerAttachment);
		bannerAttachment.setBanner(this);
	}

	public void removeBannerAttachment(BannerAttachment bannerAttachment) {
		bannerAttachments.remove(bannerAttachment);
		bannerAttachment.setBanner(null);
	}

	public List<BannerAttachment> getBannerAttachments() {
		return bannerAttachments;
	}

	public void setBannerAttachments(List<BannerAttachment> bannerAttachments) {
		this.bannerAttachments = bannerAttachments;
	}

	public String getNgayTao() {
		return ngayTao;
	}

	public void setNgayTao(String ngayTao) {
		this.ngayTao = ngayTao;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}
	
}
