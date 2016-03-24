package com.computerelectronics.io;
import java.awt.Component;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

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
import com.computerelectronics.ui.MainWindow;

// Class for reading saved products.
public final class ProductReader extends ApplicationReader {

	public final Object[] read(Component parentContainer) throws Exception {
	
		if (this.getReader() != null) {
		
			try {
			
				// Start file parsing by trying to find the entry point.
				// If the entry point is invalid, throw an empty IOException.
				if (!(this.tagSearch("ITEM_LIST", true))) throw new IOException();
				
				if (!(this.tagSearch("{", true))) throw new IOException();
				
				// Initialize the product list.
				ArrayList<Product> products = new ArrayList<Product>();
				String currentLine;
				
				// Parse the file.
				while (true) {
				
					// Search for a block of information.
					if (this.tagSearch("ITEM", false)) {
					
						this.tagSearch("ITEM", true);     // OK, block found, go to it.
					
					} else {     // No other blocks of information found, check for valid end-of-file.
					
						if (this.tagSearch("}", true) && (!(this.tagSearch("}", false))) && (products.size() > 0)) {
						
							break;     // OK, the end-of-file is valid, exit parsing process.
						
						} else throw new IOException();     // The end-of-file is invalid, discard any aquired information.
					
					}
					
					// Block of information found, check it for structure validity.
					if (!(this.tagSearch("{", true))) throw new IOException();
					
					Product currentProduct = null;
					String currentProductType = null;
					String currentProductName = null;
					double currentProductPrice = -1;
					
					// Parse the (tagged) information inside the current block.
					while (true) {
					
						if (this.getReader().ready()) {
						
							currentLine = this.getReader().readLine().trim();
						
						} else throw new IOException();
						
						// If the end of the current information block is reached, check if the aquired information is valid.
						if (currentLine.equalsIgnoreCase("}")) {
						
							// The basic tags were not found, cannot create the object, discard any aquired information, warn the user.
							if ((currentProductType == null) || (currentProductName == null) || (currentProductPrice == -1)) {
							
								JOptionPane.showMessageDialog(parentContainer, "WARNING\n\nOne or more items ignored.\nCheck the product list file for invalid items.", MainWindow.APPLICATION_TITLE, JOptionPane.WARNING_MESSAGE);
								break;
							
							}
							
							// Add the current object to the list only if it is not null and, therefore, it may contain valid information.
							if (currentProduct != null) {
							
								products.add(currentProduct);
								break;
							
							}
						
						}
						
						// The current information block is not closed with a "}" character and another block starts beneath, error.
						if (currentLine.equalsIgnoreCase("ITEM")) throw new IOException();
						
						// Continue searching for the basic tags, until they are found, or the end-of-block has been reached.
						if ((currentProductType == null) || (currentProductName == null) || (currentProductPrice == -1)) {
						
							// If there was no error parsing the current line, check the information parsed and, if valid, add them to the variable
							// that holds the basic tag's information, according to the type of information.
							
							if (currentProductType == null) {
							
								currentProductType = this.parseStringValue(currentLine, "ITEM_TYPE");
							
							}
							
							if (currentProductName == null) {
							
								currentProductName = this.parseStringValue(currentLine, "MODEL");
							
							}
							
							if (currentProductPrice == -1) {
							
								double parsedValue = this.parseDoubleValue(currentLine, "PRICE");
								
								if (parsedValue >= 0) currentProductPrice = parsedValue;
							
							}
							
							// After parsing the required information for creating an object, reset the reader to the top of the current
							// block, so that the rest of the available tags can be scanned (only once), and all the rest information
							// available can be aquired (if valid, see main parsing process below).
							if ((currentProductType != null) && (currentProductName != null) && (currentProductPrice != -1)) this.getReader().reset();
						
						} else {     // OK, basic information available, continue parsing the rest of the tags.
						
							// Create a new object if it is null. This is done only if the required information is available.
							// The new object is created based on the aquired information for the current product's type.
							if (currentProduct == null) {
							
								if (currentProductType.equalsIgnoreCase("cpu")) {
								
									currentProduct = new CPU();
								
								} else if (currentProductType.equalsIgnoreCase("motherboard")) {
								
									currentProduct = new Motherboard();
								
								} else if (currentProductType.equalsIgnoreCase("ram")) {
								
									currentProduct = new RAM();
								
								} else if (currentProductType.equalsIgnoreCase("graphicscard")) {
								
									currentProduct = new GraphicsCard();
								
								} else if (currentProductType.equalsIgnoreCase("harddrive")) {
								
									currentProduct = new HardDrive();
								
								} else if (currentProductType.equalsIgnoreCase("monitor")) {
								
									currentProduct = new Monitor();
								
								} else if (currentProductType.equalsIgnoreCase("keyboard")) {
								
									currentProduct = new Keyboard();
								
								} else if (currentProductType.equalsIgnoreCase("mouse")) {
								
									currentProduct = new Mouse();
								
								} else if (currentProductType.equalsIgnoreCase("printer")) {
								
									currentProduct = new Printer();
								
								}
							
							}
							
							// Parse the rest available (tagged) information.
							currentProduct.setName(currentProductName);
							currentProduct.setPrice(currentProductPrice);
							String currentProductYear = this.parseStringValue(currentLine, "MODEL_YEAR");
							String currentProductManufacturer = this.parseStringValue(currentLine, "MANUFACTURER");
							int currentProductDiscount = this.parseIntValue(currentLine, "DISCOUNT");
							int currentProductAvailableNumber = this.parseIntValue(currentLine, "PIECES");
							
							// Add the information available, to the object, only if it is valid (under certain rules applied to SOME of the tags).
							// Depending on the current product's type, add any other "special" information found.
							if ((currentProductYear != null) && (currentProductYear.length() == 4)) currentProduct.setYear(currentProductYear);
							
							if (currentProductManufacturer != null) currentProduct.setManufacturer(currentProductManufacturer);
							
							if (currentProductDiscount >= 0) currentProduct.setDiscount(currentProductDiscount);
							
							if (currentProductAvailableNumber >= 0) currentProduct.setAvailableNumber(currentProductAvailableNumber);
							
							if (currentProductType.equalsIgnoreCase("cpu")) {
							
								double cpuFrequency = this.parseDoubleValue(currentLine, "FREQUENCY");
								int cpuCores = this.parseIntValue(currentLine, "CORE_NUMBER");
								
								if (cpuFrequency >= 0) ((CPU) (currentProduct)).setCPUFrequency(cpuFrequency);
								
								if (cpuCores >= 0) ((CPU) (currentProduct)).setCPuCores(cpuCores);
							
							} else if (currentProductType.equalsIgnoreCase("motherboard")) {
							
								String cpuType = this.parseStringValue(currentLine, "CPU_TYPE");
								int memoryCapacity = this.parseIntValue(currentLine, "MEMORY_CAPACITY");
								int portNumber = this.parseIntValue(currentLine, "PORT_NUMBER");
								
								if (cpuType != null) ((Motherboard) (currentProduct)).setCPUType(cpuType);
								
								if (memoryCapacity >= 0) ((Motherboard) (currentProduct)).setMotherboardMemory(memoryCapacity);
								
								if (portNumber >= 0) ((Motherboard) (currentProduct)).setMotherboardPorts(portNumber);
							
							} else if (currentProductType.equalsIgnoreCase("ram")) {
							
								String type = this.parseStringValue(currentLine, "TYPE");
								int frequency = this.parseIntValue(currentLine, "FREQUENCY");
								int capacity = this.parseIntValue(currentLine, "MEMORY_CAPACITY");
								
								if (type != null) ((RAM) (currentProduct)).setMemoryType(type);
								
								if (frequency >= 0) ((RAM) (currentProduct)).setMemoryFrequency(frequency);
								
								if (capacity >= 0) ((RAM) (currentProduct)).setMemoryCapacity(capacity);
							
							} else if (currentProductType.equalsIgnoreCase("graphicscard")) {
							
								String chipset = this.parseStringValue(currentLine, "CHIPSET");
								int capacity = this.parseIntValue(currentLine, "MEMORY_CAPACITY");
								
								if (chipset != null) ((GraphicsCard) (currentProduct)).setChipset(chipset);
								
								if (capacity >= 0) ((GraphicsCard) (currentProduct)).setGraphicsCardMemory(capacity);
							
							} else if (currentProductType.equalsIgnoreCase("harddrive")) {
							
								String type = this.parseStringValue(currentLine, "TYPE");
								double size = this.parseDoubleValue(currentLine, "SIZE");
								int capacity = this.parseIntValue(currentLine, "CAPACITY");
								
								if (type != null) ((HardDrive) (currentProduct)).setHardDriveType(type);
								
								if (size >= 0) ((HardDrive) (currentProduct)).setHardDriveSize(size);
								
								if (capacity >= 0) ((HardDrive) (currentProduct)).setHardDriveCapacity(capacity);
							
							} else if (currentProductType.equalsIgnoreCase("monitor")) {
							
								String type = this.parseStringValue(currentLine, "SCREEN_TYPE");
								String dimensions = this.parseStringValue(currentLine, "DIMENSIONS");
								String resolution = this.parseStringValue(currentLine, "RESOLUTION");
								String ports = this.parseStringValue(currentLine, "INTERFACES");
								
								if (type != null) ((Monitor) (currentProduct)).setMonType(type);
								
								try {
								
									String[] dimensionParts = dimensions.split("x", 3);
									
									if (dimensionParts.length == 3) {
									
										double[] parsedDimensions = new double[3];
										
										for (int i = 0 ; i < dimensionParts.length ; i++) {
										
											dimensionParts[i] = dimensionParts[i].replace(",", ".");
											parsedDimensions[i] = Double.parseDouble(dimensionParts[i]);
										
										}
										
										if ((parsedDimensions[0] >= 0) && (parsedDimensions[1] >= 0)) ((Monitor) (currentProduct)).setMonDimension(parsedDimensions);
									
									}
								
								} catch (Exception dimensionParseError) {}
								
								try {
								
									String[] resolutionParts = resolution.split("x", 2);
									
									if (resolutionParts.length == 2) {
									
										int[] parsedResolution = new int[2];
										
										for (int i = 0 ; i < resolutionParts.length ; i++) {
										
											parsedResolution[i] = Integer.parseInt(resolutionParts[i]);
										
										}
										
										if ((parsedResolution[0] >= 0) && (parsedResolution[1] >= 0)) ((Monitor) (currentProduct)).setMonResolution(parsedResolution);
									
									}
								
								} catch (Exception resolutionParseError) {}
								
								if (ports != null) ((Monitor) (currentProduct)).setMonPorts(ports);
							
							} else if (currentProductType.equalsIgnoreCase("keyboard")) {
							
								String conection = this.parseStringValue(currentLine, "CONNECTION_TYPE");
								
								if (conection != null) ((Keyboard) (currentProduct)).setKeyConnection(conection);
							
							} else if (currentProductType.equalsIgnoreCase("mouse")) {
							
								String technology = this.parseStringValue(currentLine, "TECHNOLOGY");
								String conection = this.parseStringValue(currentLine, "CONNECTION_TYPE");
								
								if (technology != null) ((Mouse) (currentProduct)).setMouseTechnology(technology);
								
								if (conection != null) ((Mouse) (currentProduct)).setMouseConnection(conection);
							
							} else if (currentProductType.equalsIgnoreCase("printer")) {
							
								String technology = this.parseStringValue(currentLine, "TECHNOLOGY");
								String type = this.parseStringValue(currentLine, "TYPE");
								
								if (technology != null) ((Printer) (currentProduct)).setPrinterTechnology(technology);
								
								if (type != null) ((Printer) (currentProduct)).setPrinterType(type);
							
							}
						
						}
					
					}
				
				}
				
				// If the file parsing process is complete, return a list of all the available objects, in the form of a 1-dimensional object array.
				return products.toArray();
			
			} catch (IOException ioError) {
			
				throw new Exception("ERROR\n\nThere was an I/O error while attempting to read the product list.");
			
			}
		
		}
		
		return null;
	
	}

}