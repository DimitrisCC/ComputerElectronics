package com.computerelectronics.products.components;
public class HardDrive extends Component {

	public static final String HRD_TYPE_HDD = "HDD";
	public static final String HRD_TYPE_SSD = "SSD";
	
	//Static final arrays and variables representing the legal values of the local variables.
	public static final double legalHardDriveSize[] = {1.8, 2.5, 3.5};
	public static final int legalHardDriveCapacity[] = {320, 500, 750, 1000};
	
	/* Instance variables */
	private double hardDriveSize;
	private int hardDriveCapacity;
	private String hardDriveType;
	
	/** Constructors */
	public HardDrive() {
	
		super("harddrive", "< Unknown >", "< Unknown >", "< Unknown >", 0, 0, 0);
		this.hardDriveSize = 0;
		this.hardDriveCapacity = 0;
		this.hardDriveType = "< Unknown >";
	
	}
	
	public HardDrive(String name, String manufacturer, String year, double price, int discount, double hardDriveSize, int hardDriveCapacity, String hardDriveType, int availableNumber){
	
		super("harddrive", name, manufacturer, year, price, discount, availableNumber);
		this.hardDriveSize = hardDriveSize;
		this.hardDriveCapacity = hardDriveCapacity;
		this.hardDriveType = hardDriveType;
	
	}
	
	//Setter methods.
	public void setHardDriveSize(double hardDriveSize) {
	
		this.hardDriveSize = hardDriveSize;
	
	}
	
	public void setHardDriveCapacity(int hardDriveCapacity) {
	
		this.hardDriveCapacity = hardDriveCapacity;
	
	}
	
	public void setHardDriveType(String hardDriveType) {
	
		this.hardDriveType = hardDriveType;
	
	}
	
	//Getter methods.
	public double getHardDriveSize() {
	
		return this.hardDriveSize;
	
	}
	
	public int getHardDriveCapacity() {
	
		return this.hardDriveCapacity;
	
	}
	
	public String getHardDriveType() {
	
		return this.hardDriveType;
	
	}
	
	//Overridden toString method.
	@Override
	public String toString() {
	
		return super.toString() + String.format("\nHard Drive Specifications\n-------------------------------------\nType: \t\t%s\nSize: \t\t%.2f inches\nCapacity: \t\t%d GB\n\n\n >> Available Number: %d <<\n",
												this.hardDriveType, this.hardDriveSize, this.hardDriveCapacity, super.getAvailableNumber());
	
	}

}