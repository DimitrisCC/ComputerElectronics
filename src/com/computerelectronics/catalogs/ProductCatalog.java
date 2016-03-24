package com.computerelectronics.catalogs;
import java.util.ArrayList;

import com.computerelectronics.products.Product;

// Class representing a product catalog.
public final class ProductCatalog extends Catalog<Product> {

	/** Constructors */
	public ProductCatalog() {
	
		super();
	
	}
	
	public ProductCatalog(Object[] products) {
	
		super();
		
		for (int i = 0 ; i < products.length ; i++) {
		
			this.add((Product) (products[i]));
		
		}
	
	}
	
	// Method for searching a product by name.
	public final Product get(String name) {
	
		for (Object currentProduct : this.getItems()) {
		
			if (((Product) (currentProduct)).getName().equals(name)) return ((Product) (currentProduct));
		
		}
		
		return null;
	
	}
	
	// Get catalog items based on a filter.
	// The filter is a string value returned by the "getType()" method of the "Product" class.
	// See the "Product" class for more information.
	public final Object[] getFilteredItems(String filter) {
	
		ArrayList<Object> output = new ArrayList<Object>();
		Object[] products = this.getItems();
		
		if (products != null) {
		
			for (Object currentProduct : products) {
			
				if (!(((Product) (currentProduct)).getType().equals(filter))) continue;
				
				output.add(currentProduct);
			
			}
			
			if (output.size() == 0) return null;
			
			return output.toArray();
		
		}
		
		return null;
	
	}

}