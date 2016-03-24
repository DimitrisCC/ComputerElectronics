package com.computerelectronics.products.components;
public class Motherboard extends Component {

	// Static final arrays representing the legal values of the local variables.
	public static final int legalMotherboardMemory[] = {32, 64, 128};
	public static final int legalMotherboardPorts[] = {4, 5, 6};
	
	// Instance variables.
	private String CPUType;
	private int motherboardMemory;
	private int motherboardPorts;
	
	/** Constructors */
	public Motherboard() {
	
		super("motherboard", "< Unknown >", "< Unknown >", "< Unknown >", 0, 0, 0);
		this.CPUType = "< Unknown >";
		this.motherboardMemory = 0;
		this.motherboardPorts = 0;
	
	}
	
	public Motherboard (String name, String manufacturer, String year, double price, int discount, String CPUType, int motherboardMemory, int motherboardPorts, int availableNumber){
	
		super("motherboard", name, manufacturer, year, price, discount, availableNumber);
		this.CPUType = CPUType;
		this.motherboardMemory = motherboardMemory;
		this.motherboardPorts = motherboardPorts;
	
	}
	
	//Setter methods.
	public void setCPUType(String CPUType) {
	
		this.CPUType = CPUType;
	
	}
	
	public void setMotherboardMemory(int motherboardMemory) {
	
		this.motherboardMemory = motherboardMemory;
	
	}
	
	public void setMotherboardPorts(int motherboardPorts) {
	
		this.motherboardPorts = motherboardPorts;
	
	}
	
	//Getter methods.
	public String getCPUType() {
	
		return this.CPUType;
	
	}
	
	public int getMotherboardMemory() {
	
		return this.motherboardMemory;
	
	}
	
	public int getMotherboardPorts() {
	
		return this.motherboardPorts;
	
	}
	
	// Overridden toString method.
	@Override
	public String toString() {
	
		return super.toString() + String.format("\nMotherboard Specifications\n----------------------------------------\nCPU Type: \t\t%s\nMax. Mem. Cap. : \t%d GB\nPort Number: \t\t%d\n\n\n >> Available Number: %d <<\n",
												this.CPUType, this.motherboardMemory, this.motherboardPorts, super.getAvailableNumber());
	
	}

}