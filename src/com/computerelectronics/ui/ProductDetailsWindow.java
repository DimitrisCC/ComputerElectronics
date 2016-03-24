package com.computerelectronics.ui;
import java.awt.event.ActionEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import com.computerelectronics.actions.Order;
import com.computerelectronics.actions.Sale;
import com.computerelectronics.catalogs.OrdersCatalog;
import com.computerelectronics.catalogs.SalesCatalog;
import com.computerelectronics.io.utils.ImageReader;
import com.computerelectronics.products.Product;

// Class representing a dialog window for displaying a product's details.
public final class ProductDetailsWindow extends ItemDetailsWindow {

	private static final long serialVersionUID = -808107345228509660L;
	
	private static final String PURCHACE_PROCESS_TITLE = "Purchase Details";
	private static final int DATE_YEAR_MIN = 2000;
	private static final int DATE_YEAR_MAX = 3000;
	
	// Required to reflect any changes made, by calling the "refreshTabs()" method of the MainWindow class.
	private MainWindow parentWindow;
	
	// Required for purchasing a product.
	private Product currentProduct;
	private OrdersCatalog ordersCatalog;
	private SalesCatalog salesCatalog;
	
	/** Constructor */
	public ProductDetailsWindow(MainWindow parentWindow, OrdersCatalog ordersCatalog, SalesCatalog salesCatalog, String dialogTitle, Product product, int width, int height) {
	
		super(parentWindow, dialogTitle, product.getName(), ImageReader.getImage(String.format("%s.png", product.getClass().getName())), product.toString(), width, height);
		JButton buyButton = new JButton("Buy");
		buyButton.setBounds(this.getWidth() - 238, this.getHeight() - 83, 100, 30);
		buyButton.addActionListener(this);
		JButton closeButton = new JButton("Close");
		closeButton.setBounds(this.getWidth() - 133, this.getHeight() - 83, 100, 30);
		closeButton.setActionCommand(closeButton.getText());
		closeButton.addActionListener(this);
		this.contentPanel.add(buyButton);
		this.contentPanel.add(closeButton);
		this.parentWindow = (MainWindow) (parentWindow);
		this.currentProduct = product;
		this.ordersCatalog = ordersCatalog;
		this.salesCatalog = salesCatalog;
		this.setVisible(true);
	
	}
	
	// Mouse handler.
	@Override
	public final void actionPerformed(ActionEvent event) {
	
		if (event.getActionCommand().equals("Close")) {
		
			this.setVisible(false);
			this.dispose();
			return;
		
		}
		
		boolean order = false;
		
		// Make an offer to order the selected product if it is not available.
		if (this.currentProduct.getAvailableNumber() == 0) {
		
			int result = JOptionPane.showConfirmDialog(this, "This product is currently unavailable.\nWould you like to order it ?", ProductDetailsWindow.PURCHACE_PROCESS_TITLE, JOptionPane.YES_NO_OPTION);
			
			if (result != JOptionPane.YES_OPTION) return;
			
			order = true;
		
		}
		
		String clientName;
		
		while (true) {
		
			clientName = JOptionPane.showInputDialog(this, "Please fill in your name :", ProductDetailsWindow.PURCHACE_PROCESS_TITLE, JOptionPane.PLAIN_MESSAGE);
			
			if (clientName == null) return;
			
			if (clientName.toCharArray().length <= ItemDetailsWindow.CLIENT_NAME_MAX) break;
			
			JOptionPane.showMessageDialog(this, "Name must be less or equal to 30 characters.", ProductDetailsWindow.PURCHACE_PROCESS_TITLE, JOptionPane.WARNING_MESSAGE);
		
		}
		
		String clientPhone;
		
		while (true) {
		
			clientPhone = JOptionPane.showInputDialog(this, "Please fill in your phone number :", ProductDetailsWindow.PURCHACE_PROCESS_TITLE, JOptionPane.PLAIN_MESSAGE);
			
			if (clientPhone == null) return;
			
			char[] clientPhoneNumbers = clientPhone.toCharArray();
			
			try {
			
				if (clientPhoneNumbers.length <= ItemDetailsWindow.CLIENT_PHONE_MAX) {
				
					for (int i = 0 ; i < clientPhoneNumbers.length ; i++) {
					
						if (!(Character.isDigit(clientPhoneNumbers[i]))) throw new Exception("Phone number can contain only numeric values.");
					
					}
					
					break;
				
				} else throw new Exception("Phone number must be equal to 10 numbers.");
			
			} catch (Exception error) {
			
				JOptionPane.showMessageDialog(this, error.getMessage(), ProductDetailsWindow.PURCHACE_PROCESS_TITLE, JOptionPane.WARNING_MESSAGE);
			
			}
		
		}
		
		Calendar calendar = Calendar.getInstance();
		DateFormat dateParser = new SimpleDateFormat("dd/MM/yyyy");
		String orderDateText = String.format("%02d/%02d/%02d", calendar.get(Calendar.DAY_OF_MONTH), ((calendar.get(Calendar.MONTH) == 12) ? 1 : (calendar.get(Calendar.MONTH) + 1)), calendar.get(Calendar.YEAR));
		
		if (order) {
		
			String availableDateText;
			
			while (true) {
			
				availableDateText = (String) (JOptionPane.showInputDialog(this, "Please fill in the date you wish the product to be available :", ProductDetailsWindow.PURCHACE_PROCESS_TITLE, JOptionPane.PLAIN_MESSAGE, null, null, orderDateText));
				
				if (availableDateText == null) return;
				
				try {
				
					String[] parsedAvailableDate = availableDateText.split("/", 3);
					
					if (parsedAvailableDate.length != 3) throw new Exception();
					
					for (int i = 0 ; i < parsedAvailableDate.length ; i++) {
					
						char[] currentParsedAvailableDateNumbers = parsedAvailableDate[i].toCharArray();
						
						// Check for a valid day or month or year number.
						if (((i < 2) && (currentParsedAvailableDateNumbers.length != 2)) || ((i == 2) && (currentParsedAvailableDateNumbers.length != 4))) throw new Exception();
						
						for (int j = 0 ; j < currentParsedAvailableDateNumbers.length ; j++) {
						
							if (!(Character.isDigit(currentParsedAvailableDateNumbers[j]))) throw new Exception();
						
						}
					
					}
					
					int year = Integer.parseInt(parsedAvailableDate[2]);
					
					if (!((year >= ProductDetailsWindow.DATE_YEAR_MIN) && (year <= ProductDetailsWindow.DATE_YEAR_MAX))) throw new Exception();
					
					int month = Integer.parseInt(parsedAvailableDate[1]);
					
					if (!((month >= 1) && (month <= 12))) throw new Exception();
					
					int day = Integer.parseInt(parsedAvailableDate[0]);
					
					if (!((day >= 1) && (day <= calendar.getActualMaximum(Calendar.DAY_OF_MONTH)))) throw new Exception();
					
					Date orderDate = dateParser.parse(orderDateText);
					Date availableDate = dateParser.parse(availableDateText);
					
					if (!(availableDate.after(orderDate) || availableDate.equals(orderDate))) throw new Exception();
					
					break;
				
				} catch (Exception error) {
				
					JOptionPane.showMessageDialog(this, "The date entered is not valid.", ProductDetailsWindow.PURCHACE_PROCESS_TITLE, JOptionPane.WARNING_MESSAGE);
				
				}
			
			}
			
			this.ordersCatalog.add(new Order(this.currentProduct.getClass().getName().toLowerCase(), this.currentProduct.getName(), clientName, clientPhone, orderDateText, availableDateText, this.currentProduct.getPrice() - (this.currentProduct.getPrice() * (((double) (this.currentProduct.getDiscount())) / 100)), Order.ORDER_PENDING));
		
		} else {
		
			this.salesCatalog.add(new Sale(this.currentProduct.getClass().getName().toLowerCase(), this.currentProduct.getName(), clientName, clientPhone, orderDateText, this.currentProduct.getPrice() - (this.currentProduct.getPrice() * (((double) (this.currentProduct.getDiscount())) / 100))));
		
		}
		
		this.currentProduct.decrementAvailableNumber();
		JOptionPane.showMessageDialog(this, String.format("Thank you for purchasing product : %s.", this.currentProduct.getName()), "Purchase Successful", JOptionPane.INFORMATION_MESSAGE);
		this.updateDetails(this.currentProduct.toString());
		this.parentWindow.refreshTabs();
	
	}

}