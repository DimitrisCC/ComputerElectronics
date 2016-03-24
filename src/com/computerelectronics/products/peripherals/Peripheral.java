package com.computerelectronics.products.peripherals;
import com.computerelectronics.products.Product;

// Basic abstract class representing a computer peripheral.
public abstract class Peripheral extends Product {

	/** Constructor */
	protected Peripheral(String type, String name, String manufacturer, String year, double price, int discount, int availableNumber) {
	
		super(type, name, manufacturer, year, price, discount, availableNumber);
	
	}

}