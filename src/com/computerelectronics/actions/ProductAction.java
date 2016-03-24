package com.computerelectronics.actions;
// Skeleton class representing an action taken upon a product selection.
public abstract class ProductAction {

	private String productType;
	private String product;
	private String clientName;
	private String clientPhone;
	private String date;
	private double finalCost;
	
	/** Constructor */
	protected ProductAction(String productType, String product, String clientName, String clientPhone, String date, double finalCost) {
	
		this.productType = productType;
		this.product = product;
		this.clientName = clientName;
		this.clientPhone = clientPhone;
		this.date = date;
		this.finalCost = finalCost;
	
	}
	
	public final void setProductType(String productType) {
	
		this.productType = productType;
	
	}
	
	public final void setProduct(String product) {
	
		this.product = product;
	
	}
	
	public final void setClientName(String clientName) {
	
		this.clientName = clientName;
	
	}
	
	public final void setClientPhone(String clientPhone) {
	
		this.clientPhone = clientPhone;
	
	}
	
	public final void setDate(String date) {
	
		this.date = date;
	
	}
	
	public final void setFinalCost(double finalCost) {
	
		this.finalCost = finalCost;
	
	}
	
	public final String getProductType() {
	
		return this.productType;
	
	}
	
	public final String getProduct() {
	
		return this.product;
	
	}
	
	public final String getClientName() {
	
		return this.clientName;
	
	}
	
	public final String getClientPhone() {
	
		return this.clientPhone;
	
	}
	
	public final String getDate() {
	
		return this.date;
	
	}
	
	public final double getFinalCost() {
	
		return this.finalCost;
	
	}
	
	// This method MUST be overridden by subclasses, in order to display action information properly.
	public abstract String toString();

}