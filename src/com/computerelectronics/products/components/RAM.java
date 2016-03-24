package com.computerelectronics.products.components;
public class RAM extends Component {

	//Static final arrays and variables representing the legal values of the local variables.
	public static final int legalMemoryCapacity [] = {1, 2, 4, 6, 8};
	public static final int legalMemoryFrequency [] = {800, 1600, 2000};
	
	//Private static final variables.
	public static final String MEM_DDR = "DDR";
	public static final String MEM_DDR2 = "DDR2";
	public static final String MEM_DDR3 = "DDR3";
	
	//Instance variables.
	private int memoryCapacity;
	private int memoryFrequency;
	private String memoryType;
	
	/** Constructors */
	public RAM() {
	
		super("ram", "< Unknown >", "< Unknown >", "< Unknown >", 0, 0, 0);
		this.memoryCapacity = 0;
		this.memoryFrequency = 0;
		this.memoryType = "< Unknown >";
	
	}
	
	public RAM (String name, String manufacturer, String year, double price, int discount, int memoryCapacity, int memoryFrequency, String memoryType, int availableNumber){
	
		super ("ram", name, manufacturer, year, price, discount, availableNumber);
		this.memoryCapacity = memoryCapacity;
		this.memoryFrequency = memoryFrequency;
		this.memoryType = memoryType;
	
	}
	
	//Setter methods.
	public void setMemoryFrequency(int memoryFrequency) {
	
		this.memoryFrequency = memoryFrequency;
	
	}
	
	public void setMemoryCapacity(int memoryCapacity) {
	
		this.memoryCapacity = memoryCapacity;
	
	}
	
	public void setMemoryType(String memoryType) {
	
		this.memoryType = memoryType;
	
	}
	
	//Getter methods.
	public int getMemoryFrequency() {
	
		return this.memoryFrequency;
	
	}
	
	public int getMemoryCapacity() {
	
		return this.memoryCapacity;
	
	}
	
	public String getMemoryType() {
	
		return this.memoryType;
	
	}
	
	//Overridden toString method.
	@Override
	public String toString() {
	
		return super.toString() + String.format("\nRAM Specifications\n------------------------------\nType: \t\t%s\nFrequency: \t\t%d MHz\nCapacity: \t\t%d GB\n\n\n >> Available Number: %d <<\n",
												this.memoryType, this.memoryFrequency, this.memoryCapacity, super.getAvailableNumber());
	
	}

}