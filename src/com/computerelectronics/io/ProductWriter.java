package com.computerelectronics.io;
import java.io.IOException;

import com.computerelectronics.catalogs.ProductCatalog;
import com.computerelectronics.products.Product;
import com.computerelectronics.products.components.CPU;
import com.computerelectronics.products.components.GraphicsCard;
import com.computerelectronics.products.components.HardDrive;
import com.computerelectronics.products.components.Motherboard;
import com.computerelectronics.products.components.RAM;
import com.computerelectronics.products.peripherals.Keyboard;
import com.computerelectronics.products.peripherals.Monitor;
import com.computerelectronics.products.peripherals.Mouse;
import com.computerelectronics.products.peripherals.Printer;

// Class for writing the product catalog to a file.
public final class ProductWriter extends ApplicationWriter {

	public final void write(ProductCatalog products) throws Exception {
	
		if (this.getWriter() != null) {
		
			try {
			
				// Write the catalog's entry point.
				this.getWriter().write("ITEM_LIST\n{\n");
				
				// Write each product in the catalog, in the form of an information block.
				// For different product types, special (tagged) information is added.
				for (Object product : products.getItems()) {
				
					this.getWriter().write("\n\tITEM\n\t{\n\n");
					this.getWriter().write(String.format("\t\tITEM_TYPE %s\n", ((Product) (product)).getType()));
					this.getWriter().write(String.format("\t\tMODEL %s\n", ((Product) (product)).getName()));
					this.getWriter().write(String.format("\t\tMODEL_YEAR %s\n", ((Product) (product)).getYear()));
					this.getWriter().write(String.format("\t\tMANUFACTURER %s\n", ((Product) (product)).getManufacturer()));
					this.getWriter().write(String.format("\t\tPRICE %.2f\n", ((Product) (product)).getPrice()));
					this.getWriter().write(String.format("\t\tDISCOUNT %d\n", ((Product) (product)).getDiscount()));
					
					if (((Product) (product)).getType().equals("cpu")) {
					
						this.getWriter().write(String.format("\t\tFREQUENCY %.1f\n", ((CPU) (product)).getCPUFrequency()));
						this.getWriter().write(String.format("\t\tCORE_NUMBER %d\n", ((CPU) (product)).getCPuCores()));
					
					} else if (((Product) (product)).getType().equals("motherboard")) {
					
						this.getWriter().write(String.format("\t\tCPU_TYPE %s\n", ((Motherboard) (product)).getCPUType()));
						this.getWriter().write(String.format("\t\tMEMORY_CAPACITY %d\n", ((Motherboard) (product)).getMotherboardMemory()));
						this.getWriter().write(String.format("\t\tPORT_NUMBER %d\n", ((Motherboard) (product)).getMotherboardPorts()));
					
					} else if (((Product) (product)).getType().equals("ram")) {
					
						this.getWriter().write(String.format("\t\tTYPE %s\n", ((RAM) (product)).getMemoryType()));
						this.getWriter().write(String.format("\t\tFREQUENCY %d\n", ((RAM) (product)).getMemoryFrequency()));
						this.getWriter().write(String.format("\t\tMEMORY_CAPACITY %d\n", ((RAM) (product)).getMemoryCapacity()));
					
					} else if (((Product) (product)).getType().equals("graphicscard")) {
					
						this.getWriter().write(String.format("\t\tCHIPSET %s\n", ((GraphicsCard) (product)).getChipset()));
						this.getWriter().write(String.format("\t\tMEMORY_CAPACITY %d\n", ((GraphicsCard) (product)).getGraphicsCardMemory()));
					
					} else if (((Product) (product)).getType().equals("harddrive")) {
					
						this.getWriter().write(String.format("\t\tTYPE %s\n", ((HardDrive) (product)).getHardDriveType()));
						this.getWriter().write(String.format("\t\tSIZE %.1f\n", ((HardDrive) (product)).getHardDriveSize()));
						this.getWriter().write(String.format("\t\tCAPACITY %d\n", ((HardDrive) (product)).getHardDriveCapacity()));
					
					} else if (((Product) (product)).getType().equals("monitor")) {
					
						double[] monitorDimensions = ((Monitor) (product)).getMonDimension();
						int[] monitorResolution = ((Monitor) (product)).getMonResolution();
						this.getWriter().write(String.format("\t\tSCREEN_TYPE %s\n", ((Monitor) (product)).getMonType()));
						this.getWriter().write(String.format("\t\tDIMENSIONS %.1fx%.1fx%.1f\n", monitorDimensions[0], monitorDimensions[1], monitorDimensions[2]));
						this.getWriter().write(String.format("\t\tRESOLUTION %dx%d\n", monitorResolution[0], monitorResolution[1]));
						this.getWriter().write(String.format("\t\tINTERFACES \"%s\"\n", ((Monitor) (product)).getMonPorts()));
					
					} else if (((Product) (product)).getType().equals("keyboard")) {
					
						this.getWriter().write(String.format("\t\tCONNECTION_TYPE \"%s\"\n", ((Keyboard) (product)).getKeyConnection()));
					
					} else if (((Product) (product)).getType().equals("mouse")) {
					
						this.getWriter().write(String.format("\t\tTECHNOLOGY \"%s\"\n", ((Mouse) (product)).getMouseTechnology()));
						this.getWriter().write(String.format("\t\tCONNECTION_TYPE \"%s\"\n", ((Mouse) (product)).getMouseConnection()));
					
					} else if (((Product) (product)).getType().equals("printer")) {
					
						this.getWriter().write(String.format("\t\tTECHNOLOGY \"%s\"\n", ((Printer) (product)).getPrinterTechnology()));
						this.getWriter().write(String.format("\t\tTYPE \"%s\"\n", ((Printer) (product)).getPrinterType()));
					
					}
					
					this.getWriter().write(String.format("\t\tPIECES %d\n\n\t}\n", ((Product) (product)).getAvailableNumber()));
				
				}
				
				this.getWriter().write("\n}");
			
			} catch (IOException ioError) {
			
				throw new Exception("ERROR\nThere was an I/O error while attempting to write the product list.");
			
			}
		
		}
	
	}

}