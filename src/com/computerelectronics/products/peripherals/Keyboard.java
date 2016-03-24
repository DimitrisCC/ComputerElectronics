package com.computerelectronics.products.peripherals;
public class Keyboard extends Peripheral {

	public static final String KEYBOARD_CONNECTION_WIRED = "Wired";
	public static final String KEYBOARD_CONNECTION_WIRELESS = "Wireless";
	
	//Instance variables.
	private String connection;
	
	/** Constructors */
	public Keyboard() {
	
		super("keyboard", "< Unknown >", "< Unknown >", "< Unknown >", 0, 0, 0);
		this.connection = "< Unknown >";
	
	}
	public Keyboard(String name, String manufacturer, String year, double price, int discount, String connection, int availableNumber) {
	
		super("keyboard", name, manufacturer, year, price, discount, availableNumber);
		this.connection = connection;
	
	}
	
	//Setter methods.
	public void setKeyConnection(String connection) {
	
		this.connection = connection;
	
	}
	
	//Getter methods.
	public String getKeyConnection() {
	
		return this.connection;
	
	}
	
	// Overridden toString method.
	@Override
	public String toString() {
	
		return super.toString() + String.format("\nKeyboard Specifications\n------------------------------------\nConnection: \t\t%s\n\n\n >> Available Number: %d <<\n", 
												this.connection, super.getAvailableNumber());
	
	}

}