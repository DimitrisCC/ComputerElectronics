package com.computerelectronics.products;

// Basic abstract class representing a product.
public abstract class Product {

	private String type;
	private String name;
	private String manufacturer;
	private String year;
	private double price;
	private int discount;
	private int availableNumber;
	
	/** Constructor */
	protected Product(String type, String name, String manufacturer, String year, double price, int discount, int availableNumber) {
	
		this.type = type;
		this.name = name;
		this.manufacturer = manufacturer;
		this.year = year;
		this.price = price;
		this.discount = discount;
		this.availableNumber = availableNumber;
	
	}
	
	public final void setType(String type) {
	
		this.type = type;
	
	}
	
	public final void setName(String name) {
	
		this.name = name;
	
	}
	
	public final void setManufacturer(String manufacturer) {
	
		this.manufacturer = manufacturer;
	
	}
	
	public final void setYear(String year) {
	
		this.year = year;
	
	}
	
	public final void setPrice(double price) {
	
		this.price = price;
	
	}
	
	public final void setDiscount(int discount) {
	
		this.discount = discount;
	
	}
	
	public final void setAvailableNumber(int availableNumber) {
	
		if (availableNumber >= 0) {     // Available number cannot be a negative number.
		
			this.availableNumber = availableNumber;
		
		}
	
	}
	
	public final String getType() {
	
		return this.type;
	
	}
	
	public final String getName() {
	
		return this.name;
	
	}
	
	public final String getManufacturer() {
	
		return this.manufacturer;
	
	}
	
	public final String getYear() {
	
		return this.year;
	
	}
	
	public final double getPrice() {
	
		return this.price;
	
	}
	
	public final int getDiscount() {
	
		return this.discount;
	
	}
	
	public final int getAvailableNumber() {
	
		return this.availableNumber;
	
	}
	
	// This is used when a product is available, and an order of one of the available pieces was cancelled.
	public final void incrementAvailableNumber() {
	
		this.availableNumber++;
	
	}
	
	// This is used when a product is available, and one of the available pieces was ordered/sold.
	public final void decrementAvailableNumber() {
	
		if (this.availableNumber > 0) this.availableNumber--;
	
	}
	
	// This method MUST be overridden by subclasses, in order to display product information properly.
	public String toString() {
	
		return String.format("Product Name: \t%s\nManufacturer: \t%s\nYear: \t\t%s\nPrice: \t\t%.2f Euros\nDiscount: \t\t%d%%\n",
							 this.name, this.manufacturer, this.year, this.price, this.discount);
	
	}

}