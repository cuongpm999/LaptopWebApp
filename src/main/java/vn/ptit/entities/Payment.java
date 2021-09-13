package vn.ptit.entities;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_payment")
public class Payment extends BaseEntity{
	
	@Column(name = "payment_type", length = 1000, nullable = false)
	private String payment_type;

	@Column(name = "all_money", precision = 13, scale = 0, nullable = false)
	private BigDecimal all_money;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "bill_id")
	private Bill bill;

	public String getPayment_type() {
		return payment_type;
	}

	public void setPayment_type(String payment_type) {
		this.payment_type = payment_type;
	}

	public BigDecimal getAll_money() {
		return all_money;
	}

	public void setAll_money(BigDecimal all_money) {
		this.all_money = all_money;
	}

	public Bill getBill() {
		return bill;
	}

	public void setBill(Bill bill) {
		this.bill = bill;
	}
	
}
