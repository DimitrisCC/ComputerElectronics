package com.computerelectronics.catalogs;

import java.util.ArrayList;

// Skeleton class representing a catalog.
// We do not extend the ArrayList<E> class, as we want specific functions and fields to be visible by subclasses and other application classes.
public abstract class Catalog<E> {

	// This class will represent a catalog of integer keys and E items mapped to them.
	private ArrayList<E> catalog;
	
	/** Constructor */
	protected Catalog() {
	
		// Empty the catalog when creating a new instance of the subclass.
		this.catalog = new ArrayList<E>();
	
	}
	
	public final void add(E item) {
	
		this.catalog.add(item);
	
	}
	
	// Required by writer classes, as well.
	public final Object[] getItems() {
	
		Object[] items = this.catalog.toArray();
		return ((items.length != 0) ? (items) : (null));
	
	}
	
	public final int getSize() {
	
		return this.catalog.size();
	
	}
	
	// Delete all the stored items from the catalog.
	public final void emptyCatalog() {
	
		if (this.catalog.size() != 0) {
		
			this.catalog.clear();
		
		}
	
	}

}