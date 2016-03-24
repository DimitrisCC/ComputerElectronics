package com.computerelectronics.actions;
public final class Sale extends ProductAction {

	// Needed, in order to give each sale a unique code.
	private static int saleCode = 1270;
	
	private int currentCode;
	
	/** Constructors */
	public Sale() {
	
		super("< Unknown >", "< Unknown >", "< Unknown >", "< Unknown >", "< Unknown >", 0);
		this.currentCode = 0;
	
	}
	
	public Sale(String productType, String product, String clientName, String clientPhone, String date, double finalCost) {
	
		super(productType, product, clientName, clientPhone, date, finalCost);
		this.currentCode = (Sale.saleCode)++;
	
	}
	
	public final void setCode(int code) {
	
		this.currentCode = code;
		
		// If the current sale code is changed, set the global sale code variable to the next number available.
		Sale.saleCode = ++code;
	
	}
	
	public final int getCode() {
	
		return this.currentCode;
	
	}
	
	// Display information about this sale.
	@Override
	public final String toString() {
	
		return String.format("Sale\n-----------\nCode: \t\t%d\nProduct Name: \t%s\nClient Name: \t\t%s\nClient Phone: \t%s\nOrder Date: \t\t%s\nFinal Cost: \t\t%.2f Euros\n",
							 this.currentCode, super.getProduct(), super.getClientName(), super.getClientPhone(), super.getDate(), super.getFinalCost());
	
	}

}