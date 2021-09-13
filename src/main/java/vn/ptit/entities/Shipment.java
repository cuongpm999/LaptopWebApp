package vn.ptit.entities;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_shipment")
public class Shipment extends BaseEntity{
	@Column(name = "name", length = 100, nullable = false)
	private String name;

	@Column(name = "price", precision = 13, scale = 0, nullable = false)
	private BigDecimal price;
	
	@Column(name = "mobile", length = 100, nullable = false)
	private String mobile;
	
	@Column(name = "status", nullable = true)
	private Boolean status;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

}
