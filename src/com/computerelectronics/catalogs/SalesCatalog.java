package com.computerelectronics.catalogs;
import com.computerelectronics.actions.Sale;

// Class representing a catalog for keeping sales.
public final class SalesCatalog extends Catalog<Sale> {

	/** Constructors */
	public SalesCatalog() {
	
		super();
	
	}
	
	public SalesCatalog(Object[] salesList) {
	
		super();
		
		for (int i = 0 ; i < salesList.length ; i++) {
		
			this.add((Sale) (salesList[i]));
		
		}
	
	}
	
	// Method for searching a sale by code (code in string value).
	public final Sale get(String code) {
	
		try {
		
			for (Object currentSale : this.getItems()) {
			
				if (Integer.toString(((Sale) (currentSale)).getCode()).equals(code)) return ((Sale) (currentSale));
			
			}
		
		} catch (Exception error) {}     // In case of an error, just return null.
		
		return null;
	
	}

}