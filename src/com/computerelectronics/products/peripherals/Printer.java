package com.computerelectronics.products.peripherals;
public class Printer extends Peripheral {

	//Private static final variables.
	public static final String PRINTER_TECH_LASER = "Laser";
	public static final String PRINTER_TECH_INKJET = "Inkjet";
	public static final String PRINTER_PRINT_TYPE_COLOR = "Color";
	public static final String PRINTER_PRINT_TYPE_BW = "Black & White";
	
	//Instance variables.
	private String printerTechnology;
	private String printerType;
	
	/** Constructors */
	public Printer() {
	
		super("printer", "< Unknown >", "< Unknown >", "< Unknown >", 0, 0, 0);
		this.printerTechnology = "< Unknown >";
		this.printerType = "< Unknown >";
	
	}
	
	public Printer(String name, String manufacturer, String year, double price, int discount, String printerTechnology, String printerType, int availableNumber) {
	
		super("printer", name, manufacturer, year, price, discount, availableNumber);
		this.printerTechnology = printerTechnology;
		this.printerType = printerType;
	
	}
	
	//Setter methods.
	public void setPrinterTechnology(String printerTechnology) {
	
		this.printerTechnology = printerTechnology;
	
	}
	
	public void setPrinterType(String printerType) {
	
		this.printerType = printerType;
	
	}
	
	//Getter methods.
	public String getPrinterTechnology() {
	
		return this.printerTechnology;
	
	}
	
	public String getPrinterType() {
	
		return this.printerType;
	
	}
	
	//Overridden toString method.
	@Override
	public String toString() {
	
		return super.toString() + String.format("\nPrinter Specifications\n--------------------------------\nTechnology: \t\t%s\nType: \t\t%s\n\n\n >> Available Number: %d <<\n",
												this.printerTechnology, this.printerType, super.getAvailableNumber());
	
	}

}