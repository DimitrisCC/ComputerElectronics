package com.computerelectronics.products.components;
public class CPU extends Component {

	//Static final variables of the CPU type for the Motherboard Class.
	public static final String TYPE_INTEL = "Intel";
	public static final String TYPE_AMD = "AMD";
	
	//Static final arrays representing the legal values of the local variables.
	public static final double legalCPUFrequency[] = {2.7, 2.9, 3.3, 3.5, 4.0};
	public static final int legalCPUCores[] = {2, 4, 6, 8};
	
	//Instance variables
	private double CPUFrequency;
	private int CPUCores;
	
	/** Constructors */
	public CPU() {
	
		super("cpu", "< Unknown >", "< Unknown >", "< Unknown >", 0, 0, 0);
		this.CPUFrequency = 0;
		this.CPUCores = 0;
	
	}
	
	public CPU (String name, String manufacturer, String year, double price, int discount, double CPUFrequency, int CPUCores, int availableNumber){
	
		super("cpu", name, manufacturer, year, price, discount, availableNumber);
		this.CPUFrequency = CPUFrequency;
		this.CPUCores = CPUCores;
	
	}
	
	//Setter methods.
	public void setCPUFrequency(double CPUFrequency) {
	
		this.CPUFrequency = CPUFrequency;
	
	}
	
	public void setCPuCores(int CPUCores) {
	
		this.CPUCores = CPUCores;
	
	}
	
	//Getter methods.
	public double getCPUFrequency() {
	
		return this.CPUFrequency;
	
	}
	
	public int getCPuCores() {
	
		return this.CPUCores;
	
	}
	
	// Overridden toString method.
	@Override
	public String toString() {
	
		return super.toString() + String.format("\nCPU Specifications\n-----------------------------\nFrequency: \t\t%.1f GHz\nNum. of Cores: \t%d\n\n\n >> Available Number: %d <<\n",
												this.CPUFrequency, this.CPUCores, super.getAvailableNumber());
	
	}

}