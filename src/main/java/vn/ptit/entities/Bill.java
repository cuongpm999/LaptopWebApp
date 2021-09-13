package vn.ptit.entities;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_bill")
public class Bill extends BaseEntity {
	
	@Column(name = "date_create", nullable = false)
	private Date date_create;
	
	@Column(name = "status", nullable = true)
	private Boolean status;
	
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "bill", fetch = FetchType.EAGER)
	private Payment payment = new Payment();
	
	public void addPayment(Payment payment) {
		this.payment = payment;
		payment.setBill(this);
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "shipment_id")
	private Shipment shipment;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	private UserInfo userInfo;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "bill", fetch = FetchType.LAZY)
	private List<BoughtLaptop> boughtLaptops = new ArrayList<>();
	
	public void addBoughtProduct(BoughtLaptop boughtLaptop) {
		boughtLaptops.add(boughtLaptop);
		boughtLaptop.setBill(this);
	}

	public Date getDate_create() {
		return date_create;
	}

	public void setDate_create(Date date_create) {
		this.date_create = date_create;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public Shipment getShipment() {
		return shipment;
	}

	public void setShipment(Shipment shipment) {
		this.shipment = shipment;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	public List<BoughtLaptop> getBoughtLaptops() {
		return boughtLaptops;
	}

	public void setBoughtLaptops(List<BoughtLaptop> boughtLaptops) {
		this.boughtLaptops = boughtLaptops;
	}
	
}
