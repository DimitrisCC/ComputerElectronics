package com.computerelectronics.products.peripherals;
public class Mouse extends Peripheral {

	//Private static final variables.
	public static final String MOUSE_TECH_LASER = "Laser";
	public static final String MOUSE_TECH_OPTICAL = "Optical";
	public static final String MOUSE_CONN_WIRED = "Wired";
	public static final String MOUSE_CONN_WIRELESS = "Wireless";
	
	//Instance variables.
	private String mouseTechnology;
	private String mouseConnection;
	
	/** Constructors */
	public Mouse() {
	
		super("mouse", "< Unknown >", "< Unknown >", "< Unknown >", 0, 0, 0);
		this.mouseTechnology = "< Unknown >";
		this.mouseConnection = "< Unknown >";
	
	}
	
	public Mouse(String name, String manufacturer, String year, double price, int discount, String mouseTechnology, String mouseConnection, int availableNumber) {
	
		super("mouse", name, manufacturer, year, price, discount, availableNumber);
		this.mouseTechnology = mouseTechnology;
		this.mouseConnection = mouseConnection;
	
	}
	
	//Setter methods.
	public void setMouseTechnology(String mouseTechnology) {
	
		this.mouseTechnology = mouseTechnology;
	
	}
	
	public void setMouseConnection(String mouseConnection) {
	
		this.mouseConnection = mouseConnection;
	
	}
	
	//Getter methods.
	public String getMouseTechnology() {
	
		return this.mouseTechnology;
	
	}
	
	public String getMouseConnection() {
	
		return this.mouseConnection;
	
	}
	
	//Overridden toString method.
	@Override
	public String toString() {
	
		return super.toString() + String.format("\nMouse Specifications\n--------------------------------\nTechnology: \t\t%s\nConnection: \t\t%s\n\n\n >> Available Number: %d <<\n",
												this.mouseTechnology, this.mouseConnection, super.getAvailableNumber());
	
	}

}