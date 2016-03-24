package com.computerelectronics.products.components;
import com.computerelectronics.products.Product;

// Basic abstract class representing a computer component.
public abstract class Component extends Product {

	/** Constructor */
	protected Component(String type, String name, String manufacturer, String year, double price, int discount, int availableNumber) {
	
		super(type, name, manufacturer, year, price, discount, availableNumber);
	
	}

}