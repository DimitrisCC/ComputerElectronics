package com.computerelectronics.actions;
public final class Order extends ProductAction {

	// Needed, in order to give each order a unique code.
	private static int orderCode = 1170;
	
	private int currentCode;
	private String availableDate;
	private String status;
	
	// Used to determine the status of an order.
	public static final String ORDER_PENDING = "Pending";
	public static final String ORDER_COMPLETED = "Completed";
	
	/** Constructors */
	public Order() {
	
		super("< Unknown >", "< Unknown >", "< Unknown >", "< Unknown >", "< Unknown >", 0);
		this.currentCode = 0;
		this.availableDate = "< Unknown >";
		this.status = "< Unknown >";
	
	}
	
	public Order(String productType, String product, String clientName, String clientPhone, String date, String availableDate, double finalCost, String status) {
	
		super(productType, product, clientName, clientPhone, date, finalCost);
		this.currentCode = (Order.orderCode)++;
		this.availableDate = availableDate;
		this.status = status;
	
	}
	
	// Set an order as completed.
	public final boolean setCompleted() {
	
		if (this.status.equalsIgnoreCase(Order.ORDER_PENDING)) {
		
			this.status = Order.ORDER_COMPLETED;
			return true;
		
		}
		
		return false;
	
	}
	
	public final void setCode(int code) {
	
		this.currentCode = code;
		
		// If the current order code is changed, set the global order code variable to the next number available.
		Order.orderCode = ++code;
	
	}
	
	public final void setAvailableDate(String availableDate) {
	
		this.availableDate = availableDate;
	
	}
	
	public final void setStatus(String status) {
	
		this.status = status;
	
	}
	
	public final int getCode() {
	
		return this.currentCode;
	
	}
	
	public final String getAvailableDate() {
	
		return this.availableDate;
	
	}
	
	public final String getStatus() {
	
		return this.status;
	
	}
	
	// Display information about this order.
	@Override
	public final String toString() {
	
		return String.format("Order\n-------------\nCode: \t\t%d\nProduct Name: \t%s\nClient Name: \t\t%s\nClient Phone: \t%s\nOrder Date: \t\t%s\nAvailability Date: \t%s\nFinal Cost: \t\t%.2f Euros\nStatus: \t\t%s\n",
							 this.currentCode, super.getProduct(), super.getClientName(), super.getClientPhone(), super.getDate(), this.availableDate, super.getFinalCost(), this.status);
	
	}

}