package com.computerelectronics.catalogs;
import com.computerelectronics.actions.Order;

// Class representing a catalog for keeping orders.
public final class OrdersCatalog extends Catalog<Order> {

	/** Constructors */
	public OrdersCatalog() {
	
		super();
	
	}
	
	public OrdersCatalog(Object[] ordersList) {
	
		super();
		
		for (int i = 0 ; i < ordersList.length ; i++) {
		
			this.add((Order) (ordersList[i]));
		
		}
	
	}
	
	// Method for searching an order by code (code in integer value).
	public final Order get(int code) {
	
		for (Object currentOrder : this.getItems()) {
		
			if (((Order) (currentOrder)).getCode() == code) return ((Order) (currentOrder));
		
		}
		
		return null;
	
	}
	
	// Method for searching an order by code (code in string value).
	public final Order get(String code) {
	
		try {
		
			for (Object currentOrder : this.getItems()) {
			
				if (Integer.toString(((Order) (currentOrder)).getCode()).equals(code)) return ((Order) (currentOrder));
			
			}
		
		} catch (Exception error) {}     // In case of an error, just return null.
		
		return null;
	
	}
	
	public final boolean markOrderAsComplete(String code) {
	
		try {
		
			return this.get(code).setCompleted();
		
		} catch (Exception exception) { return false; }
	
	}

}