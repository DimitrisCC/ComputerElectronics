package com.computerelectronics.io;
import java.io.IOException;

import com.computerelectronics.actions.Sale;
import com.computerelectronics.catalogs.SalesCatalog;

// Class for writing the sales catalog to a file.
public final class SalesWriter extends ApplicationWriter {

	public final void write(SalesCatalog sales) throws Exception {
	
		if (this.getWriter() != null) {
		
			try {
			
				// Write the catalog's entry point.
				this.getWriter().write("SALES_LIST\n{\n");
				
				// Write each sale in the catalog, in the form of an information block.
				for (Object sale : sales.getItems()) {
				
					this.getWriter().write("\n\tSALE\n\t{\n\n");
					this.getWriter().write(String.format("\t\tNUMBER %s\n", ((Sale) (sale)).getCode()));
					this.getWriter().write(String.format("\t\tITEM_TYPE %s\n", ((Sale) (sale)).getProductType()));
					this.getWriter().write(String.format("\t\tMODEL %s\n", ((Sale) (sale)).getProduct()));
					this.getWriter().write(String.format("\t\tNAME %s\n", ((Sale) (sale)).getClientName()));
					this.getWriter().write(String.format("\t\tPHONE %s\n", ((Sale) (sale)).getClientPhone()));
					this.getWriter().write(String.format("\t\tSALE_DATE %s\n", ((Sale) (sale)).getDate()));
					this.getWriter().write(String.format("\t\tPRICE %.2f\n", ((Sale) (sale)).getFinalCost()));
					this.getWriter().write(String.format("\n\t}\n"));
				
				}
				
				this.getWriter().write("\n}");
			
			} catch (IOException ioError) {
			
				throw new Exception("ERROR\nThere was an I/O error while attempting to write the sales list.");
			
			}
		
		}
	
	}

}