package com.computerelectronics.products.components;
public class GraphicsCard extends Component {

	public static final String CHIP_NVIDIA = "nVidia";
	public static final String CHIP_AMD = "AMD";
	
	//Static final arrays and variables representing the legal values of the local variables.
	public static final int legalGraphicCardMemory[] = {2, 4, 6};
	
	//Instance variables.
	private String chipset;
	private int graphicsCardMemory;
	
	/** Constructors */
	public GraphicsCard() {
	
		super("graphicscard", "< Unknown >", "< Unknown >", "< Unknown >", 0, 0, 0);
		this.chipset = "< Unknown >";
		this.graphicsCardMemory = 0;
	
	}
	
	public GraphicsCard (String name, String manufacturer, String year, double price, int discount, String chipset, int graphicsCardMemory, int availableNumber) {
	
		super("graphicscard", name, manufacturer, year, price, discount, availableNumber);
		this.chipset = chipset;
		this.graphicsCardMemory = graphicsCardMemory;
	
	}
	
	//Setter methods.
	public void setChipset(String chipset) {
	
		this.chipset = chipset;
	
	}
	
	public void setGraphicsCardMemory(int graphicsCardMemory) {
	
		this.graphicsCardMemory = graphicsCardMemory;
	
	}
	
	//Getter methods.
	public String getChipset() {
	
		return this.chipset;
	
	}
	
	public int getGraphicsCardMemory() {
	
		return this.graphicsCardMemory;
	
	}
	
	//Overridden toString method.
	@Override
	public String toString() {
	
		return super.toString() + String.format("\nGraphics Card Specifications\n------------------------------------------\nChipset: \t\t%s\nMem. Capacity: \t%d GB\n\n\n >> Available Number: %d <<\n",
												this.chipset, this.graphicsCardMemory, super.getAvailableNumber());
	
	}

}