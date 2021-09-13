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
@Table(name = "tbl_laptop_manufacturer")
public class LaptopManufacturer extends BaseEntity {
	@Column(name = "name", length = 100, nullable = false)
	private String name;
	
	@Column(name = "seo", length = 500, nullable = false)
	private String seo;
	
	@Column(name = "status", nullable = true)
	private Boolean status;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "laptopManufacturer", fetch = FetchType.LAZY)
	private List<Laptop> laptops = new ArrayList<Laptop>();
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Laptop> getLaptops() {
		return laptops;
	}
	public void setLaptops(List<Laptop> laptops) {
		this.laptops = laptops;
	}
	public String getSeo() {
		return seo;
	}
	public void setSeo(String seo) {
		this.seo = seo;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	
}
