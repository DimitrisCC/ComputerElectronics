package com.computerelectronics.products.peripherals;
public class Monitor extends Peripheral {

	//Private static final variables.
	public static final String MON_TYPE_LCD = "LCD";
	public static final String MON_TYPE_LED = "LED";
	public static final String MON_PORTS_HDMI = "HDMI";
	public static final String MON_PORTS_PVI = "PVI";
	public static final String MON_PORTS_COMPOSITE = "Composite";
	
	//Instance variables.
	private int[] monResolution;
	private String monType;
	private double[] monDimension;
	private String monPorts;
	
	/** Constructors */
	public Monitor() {
	
		super("monitor", "< Unknown >", "< Unknown >", "< Unknown >", 0, 0, 0);
		this.monType = "< Unknown >";
		this.monDimension = new double[] {0, 0, 0};
		this.monResolution = new int[] {0, 0};
		this.monPorts = "< Unknown >";
	
	}
	
	public Monitor (String name, String manufacturer, String year, double price, int discount, String monType, double[] monDimension, int[] monResolution, String monPorts, int availableNumber) {
	
		super("monitor", name, manufacturer, year, price, discount, availableNumber);
		this.monType = monType;
		this.monDimension = monDimension;
		this.monResolution = monResolution;
		this.monPorts = monPorts;
	
	}
	
	//Setter methods.
	public void setMonType(String monType) {
	
		this.monType = monType;
	
	}
	
	public void setMonResolution(int[] monResolution) {
	
		this.monResolution = monResolution;
	
	}
	
	public void setMonPorts(String monPorts) {
	
		this.monPorts = monPorts;
	
	}
	
	public void setMonDimension (double[] monDimension) {
	
		this.monDimension = monDimension;
	
	}
	
	//Getter methods.
	public String getMonType() {
	
		return this.monType;
	
	}
	
	public int[] getMonResolution() {
	
		return this.monResolution;
	
	}
	
	public String getMonPorts() {
	
		return this.monPorts;
	
	}
	
	public double[] getMonDimension(){
	
		return this.monDimension;
	
	}
	
	// Overridden toString method.
	@Override
	public String toString() {
	
		return super.toString() + String.format("\nMonitor Specifications\n---------------------------------\nType: \t\t%s\nResolution: \t\t%d x %d\nDimensions: \t\t%.1f x %.1f x %.1f\nPorts: \t\t%s\n\n\n >> Available Number: %d <<\n",
												this.monType, this.monResolution[0], this.monResolution[1], this.monDimension[0], this.monDimension[1], this.monDimension[2], this.monPorts, super.getAvailableNumber());
	
	}

}