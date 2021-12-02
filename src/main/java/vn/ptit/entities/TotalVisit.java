package vn.ptit.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_total_visited")
public class TotalVisit extends BaseEntity{
	@Column(name = "ip_address", nullable = false)
	private String total;

	@Column(name = "date_visited", nullable = false)
	private Date date_visited;

	public TotalVisit(String total, Date date_visited) {
		super();
		this.total = total;
		this.date_visited = date_visited;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public Date getDate_visited() {
		return date_visited;
	}

	public void setDate_visited(Date date_visited) {
		this.date_visited = date_visited;
	}
	
}
