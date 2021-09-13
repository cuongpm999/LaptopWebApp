package vn.ptit.entities;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_laptop")
public class Laptop extends BaseEntity {
	@Column(name = "name", length = 1000, nullable = false)
	private String name;

	@Column(name = "price", precision = 13, scale = 0, nullable = false)
	private BigDecimal price;

	@Column(name = "soLuongNhap", nullable = false)
	private Integer soLuongNhap;

	@Column(name = "cpu", length = 1000, nullable = false)
	private String cpu;

	@Column(name = "oCung", length = 1000, nullable = false)
	private String oCung;

	@Column(name = "ram", length = 1000, nullable = false)
	private String ram;

	@Column(name = "vga", length = 1000, nullable = false)
	private String vga;

	@Column(name = "manHinh", length = 1000, nullable = false)
	private String manHinh;

	@Column(name = "video", length = 1000, nullable = true)
	private String video;
	
	@Column(name = "khuyenMai", nullable = true)
	private Integer khuyenMai;

	@Column(name = "table_content", nullable = false, columnDefinition = "text")
	private String table_content;
	
	@Column(name = "seo", length = 500, nullable = false)
	private String seo;
	
	@Column(name = "status", nullable = true)
	private Boolean status;
	

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "laptop_manufacturer_id")
	private LaptopManufacturer laptopManufacturer;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "laptop", fetch = FetchType.LAZY)
	private List<LaptopAttachment> laptopAttachments = new ArrayList<LaptopAttachment>();

	public void addLaptopAttachment(LaptopAttachment laptopAttachment) {
		laptopAttachments.add(laptopAttachment);
		laptopAttachment.setLaptop(this);
	}

	public void removeLaptopAttachment(LaptopAttachment laptopAttachment) {
		laptopAttachments.remove(laptopAttachment);
		laptopAttachment.setLaptop(null);
	}

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

	public Integer getSoLuongNhap() {
		return soLuongNhap;
	}

	public void setSoLuongNhap(Integer soLuongNhap) {
		this.soLuongNhap = soLuongNhap;
	}

	public String getCpu() {
		return cpu;
	}

	public void setCpu(String cpu) {
		this.cpu = cpu;
	}

	public String getoCung() {
		return oCung;
	}

	public void setoCung(String oCung) {
		this.oCung = oCung;
	}

	public String getRam() {
		return ram;
	}

	public void setRam(String ram) {
		this.ram = ram;
	}

	public String getVga() {
		return vga;
	}

	public void setVga(String vga) {
		this.vga = vga;
	}

	public String getManHinh() {
		return manHinh;
	}

	public void setManHinh(String manHinh) {
		this.manHinh = manHinh;
	}

	public String getVideo() {
		return video;
	}

	public void setVideo(String video) {
		this.video = video;
	}

	public String getTable_content() {
		return table_content;
	}

	public void setTable_content(String table_content) {
		this.table_content = table_content;
	}

	public LaptopManufacturer getLaptopManufacturer() {
		return laptopManufacturer;
	}

	public void setLaptopManufacturer(LaptopManufacturer laptopManufacturer) {
		this.laptopManufacturer = laptopManufacturer;
	}

	public List<LaptopAttachment> getLaptopAttachments() {
		return laptopAttachments;
	}

	public void setLaptopAttachments(List<LaptopAttachment> laptopAttachments) {
		this.laptopAttachments = laptopAttachments;
	}

	public Integer getKhuyenMai() {
		return khuyenMai;
	}

	public void setKhuyenMai(Integer khuyenMai) {
		this.khuyenMai = khuyenMai;
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
	
	public BigDecimal getGiaKhuyenMai() {
		if (khuyenMai != null) {
			BigDecimal x = new BigDecimal(100 - khuyenMai);
			BigDecimal y = new BigDecimal(100);
			return price.multiply(x).divide(y);
		} 
		return price;
	}
	
	public String getTrangThai() {
		if (soLuongNhap<=0) {
			return "Liên hệ";
		} 
		return "Còn hàng";
	}
	
}
