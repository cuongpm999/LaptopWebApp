package vn.ptit.beans;

public class ProductReport {
	private String nameProduct;
	private int amount;
	
	public ProductReport() {
		// TODO Auto-generated constructor stub
	}
	
	public ProductReport(String nameProduct, int amount) {
		super();
		this.nameProduct = nameProduct;
		this.amount = amount;
	}
	
	public String getNameProduct() {
		return nameProduct;
	}
	public void setNameProduct(String nameProduct) {
		this.nameProduct = nameProduct;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}

}
