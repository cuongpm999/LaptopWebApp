package vn.ptit.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_total_visit")
public class TotalVisit extends BaseEntity{
	@Column(name = "total", nullable = false)
	private Integer total;

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}
	
	
}
