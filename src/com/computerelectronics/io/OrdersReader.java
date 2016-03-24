package com.computerelectronics.io;
import java.awt.Component;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

import com.computerelectronics.actions.Order;
import com.computerelectronics.products.Product;
import com.computerelectronics.ui.ItemDetailsWindow;
import com.computerelectronics.ui.MainWindow;

// Class for reading saved orders.
public final class OrdersReader extends ApplicationReader {

	public final Object[] read(Component parentContainer, Object[] productList) throws Exception {
	
		if (this.getReader() != null) {
		
			try {
			
				// Start file parsing by trying to find the entry point.
				// If the entry point is invalid, throw an empty IOException.
				if (!(this.tagSearch("ORDERS_LIST", true))) throw new IOException();
				
				if (!(this.tagSearch("{", true))) throw new IOException();
				
				// Initialize the orders list.
				ArrayList<Order> orders = new ArrayList<Order>();
				
				String currentLine;
				
				// Parse the file.
				while (true) {
				
					// Search for a block of information.
					if (this.tagSearch("ORDER", false)) {
					
						this.tagSearch("ORDER", true);     // OK, block found, go to it.
					
					} else {     // No other blocks of information found, check for valid end-of-file.
					
						if (this.tagSearch("}", true) && (!(this.tagSearch("}", false)))) {
						
							break;     // OK, the end-of-file is valid, exit parsing process.
						
						} else throw new IOException();     // The end-of-file is invalid, discard any aquired information.
					
					}
					
					// Block of information found, check it for structure validity.
					if (!(this.tagSearch("{", true))) throw new IOException();
					
					Order currentOrder = null;
					String currentOrderProductName = null;
					
					// Parse the (tagged) information inside the current block.
					while (true) {
					
						if (this.getReader().ready()) {
						
							currentLine = this.getReader().readLine().trim();
						
						} else throw new IOException();
						
						// If the end of the current information block is reached, check if the aquired information is valid.
						if (currentLine.equalsIgnoreCase("}")) {
						
							// The basic tag was not found, cannot create the object, discard any aquired information, warn the user.
							if (currentOrderProductName == null) {
							
								JOptionPane.showMessageDialog(parentContainer, "WARNING\n\nOne or more items ignored.\nCheck the orders list file for invalid items.", MainWindow.APPLICATION_TITLE, JOptionPane.WARNING_MESSAGE);
								break;
							
							}
							
							// Add the current object to the list only if it is not null and, therefore, it may contain valid information.
							if (currentOrder != null) {
							
								orders.add(currentOrder);
								break;
							
							}
						
						}
						
						// The current information block is not closed with a "}" character and another block starts beneath, error.
						if (currentLine.equalsIgnoreCase("ORDER")) throw new IOException();
						
						// Continue searching for the basic tag, until it is found, or the end-of-block has been reached.
						if (currentOrderProductName == null) {
						
							String parsedProductName = this.parseStringValue(currentLine, "MODEL");
							
							// If there was no error parsing the current line, check the information parsed and, if valid, add them to the variable
							// that holds the basic tag's information.
							if ((parsedProductName != null) && (productList != null)) {
							
								for (int i = 0 ; i < productList.length ; i++) {
								
									if (!(parsedProductName.equalsIgnoreCase(((Product) (productList[i])).getName()))) continue;
									
									currentOrderProductName = parsedProductName;
									
									// After parsing the required information for creating an object, reset the reader to the top of the current
									// block, so that the rest of the available tags can be scanned (only once), and all the rest information
									// available can be aquired (if valid, see main parsing process below).
									this.getReader().reset();
									
									break;
								
								}
							
							}
						
						} else {     // OK, basic information available, continue parsing the rest of the tags.
						
							// Create a new object if it is null. This is done only if the required information is available.
							if (currentOrder == null) {
							
								currentOrder = new Order();
							
							}
							
							// Parse the rest available (tagged) information.
							currentOrder.setProduct(currentOrderProductName);
							int code = this.parseIntValue(currentLine, "NUMBER");
							String itemType = this.parseStringValue(currentLine, "ITEM_TYPE");
							String clientName = this.parseStringValue(currentLine, "NAME");
							String clientPhone = this.parseStringValue(currentLine, "PHONE");
							String orderDate = this.parseStringValue(currentLine, "ORDER_DATE");
							String deliveryDate = this.parseStringValue(currentLine, "DELIVERY_DATE");
							double price = this.parseDoubleValue(currentLine, "PRICE");
							String status = this.parseStringValue(currentLine, "STATUS");
							
							// Add the information available, to the object, only if it is valid (under certain rules applied to SOME of the tags).
							if (code >= 0) currentOrder.setCode(code);
							
							if (itemType != null) {
							
								for (int i = 0 ; i < productList.length ; i++) {
								
									if ((((Product) (productList[i])).getName().equalsIgnoreCase(currentOrderProductName)) && (((Product) (productList[i])).getType().equalsIgnoreCase(itemType))) currentOrder.setProductType(itemType);
								
								}
							
							}
							
							if ((clientName != null) && (clientName.length() <= ItemDetailsWindow.CLIENT_NAME_MAX)) currentOrder.setClientName(clientName);
							
							if ((clientPhone != null) && (clientPhone.length() <= ItemDetailsWindow.CLIENT_PHONE_MAX)) {
							
								char[] clientPhoneNumbers = clientPhone.toCharArray();
								
								for (int i = 0 ; i < clientPhoneNumbers.length ; i++) {
								
									if (!(Character.isDigit(clientPhoneNumbers[i]))) break;
									
									if (i == (clientPhoneNumbers.length - 1)) currentOrder.setClientPhone(clientPhone);
								
								}
							
							}
							
							if (orderDate != null) {
							
								String[] parsedOrderDate = orderDate.split("/", 3);
								
								if (parsedOrderDate.length == 3) {
								
									for (int i = 0 ; i < parsedOrderDate.length ; i++) {
									
										char[] currentParsedOrderDateNumbers = parsedOrderDate[i].toCharArray();
									
										for (int j = 0 ; j < currentParsedOrderDateNumbers.length ; j++) {
										
											if (!(Character.isDigit(currentParsedOrderDateNumbers[j]))) break;
											
											if ((i == (parsedOrderDate.length - 1)) && (j == currentParsedOrderDateNumbers.length - 1)) currentOrder.setDate(orderDate);
										
										}
									
									}
								
								}
							
							}
							
							if (deliveryDate != null) {
							
								String[] parsedDeliveryDate = deliveryDate.split("/", 3);
								
								if (parsedDeliveryDate.length == 3) {
								
									for (int i = 0 ; i < parsedDeliveryDate.length ; i++) {
									
										char[] currentParsedDeliveryDateNumbers = parsedDeliveryDate[i].toCharArray();
									
										for (int j = 0 ; j < currentParsedDeliveryDateNumbers.length ; j++) {
										
											if (!(Character.isDigit(currentParsedDeliveryDateNumbers[j]))) break;
											
											if ((i == (parsedDeliveryDate.length - 1)) && (j == currentParsedDeliveryDateNumbers.length - 1)) currentOrder.setAvailableDate(deliveryDate);
										
										}
									
									}
								
								}
							
							}
							
							if (price >= 0) currentOrder.setFinalCost(price);
							
							if (status != null) currentOrder.setStatus(status);
						
						}
					
					}
				
				}
				
				// If the file parsing process is complete, return a list of all the available objects, in the form of a 1-dimensional object array.
				return orders.toArray();
			
			} catch (IOException ioError) {
			
				throw new Exception("ERROR\n\nThere was an I/O error while attempting to read the orders list.");
			
			}
		
		}
		
		return null;
	
	}

}