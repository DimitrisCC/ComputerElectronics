package com.computerelectronics.io;
import java.io.IOException;

import com.computerelectronics.actions.Order;
import com.computerelectronics.catalogs.OrdersCatalog;

// Class for writing the orders catalog to a file.
public final class OrdersWriter extends ApplicationWriter {

	public final void write(OrdersCatalog orders) throws Exception {
	
		if (this.getWriter() != null) {
		
			try {
			
				// Write the catalog's entry point.
				this.getWriter().write("ORDERS_LIST\n{\n");
				
				// Write each order in the catalog, in the form of an information block.
				for (Object order : orders.getItems()) {
				
					this.getWriter().write("\n\tORDER\n\t{\n\n");
					this.getWriter().write(String.format("\t\tNUMBER %s\n", ((Order) (order)).getCode()));
					this.getWriter().write(String.format("\t\tITEM_TYPE %s\n", ((Order) (order)).getProductType()));
					this.getWriter().write(String.format("\t\tMODEL %s\n", ((Order) (order)).getProduct()));
					this.getWriter().write(String.format("\t\tNAME %s\n", ((Order) (order)).getClientName()));
					this.getWriter().write(String.format("\t\tPHONE %s\n", ((Order) (order)).getClientPhone()));
					this.getWriter().write(String.format("\t\tORDER_DATE %s\n", ((Order) (order)).getDate()));
					this.getWriter().write(String.format("\t\tDELIVERY_DATE %s\n", ((Order) (order)).getAvailableDate()));
					this.getWriter().write(String.format("\t\tPRICE %.2f\n", ((Order) (order)).getFinalCost()));
					this.getWriter().write(String.format("\t\tSTATUS %s\n", ((Order) (order)).getStatus()));
					this.getWriter().write(String.format("\n\t}\n"));
				
				}
				
				this.getWriter().write("\n}");
			
			} catch (IOException ioError) {
			
				throw new Exception("ERROR\nThere was an I/O error while attempting to write the orders list.");
			
			}
		
		}
	
	}

}