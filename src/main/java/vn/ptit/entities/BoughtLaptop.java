package vn.ptit.entities;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_bought_laptop")
public class BoughtLaptop extends BaseEntity {
	
	@Column(name = "amount", nullable = false)
	private Integer amount;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "bill_id")
	private Bill bill;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "laptop_id")
	private Laptop laptop;

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Bill getBill() {
		return bill;
	}

	public void setBill(Bill bill) {
		this.bill = bill;
	}

	public Laptop getLaptop() {
		return laptop;
	}

	public void setLaptop(Laptop laptop) {
		this.laptop = laptop;
	}

}
