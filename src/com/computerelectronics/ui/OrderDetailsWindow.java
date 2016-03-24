package com.computerelectronics.ui;
import java.awt.event.ActionEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import com.computerelectronics.actions.Order;
import com.computerelectronics.actions.Sale;
import com.computerelectronics.catalogs.SalesCatalog;
import com.computerelectronics.io.utils.ImageReader;

// Class representing a dialog window for displaying an order's details.
public final class OrderDetailsWindow extends ItemDetailsWindow {

	private static final long serialVersionUID = 3230172313061381127L;

	// Required to reflect any changes made, by calling the "refreshTabs()" method of the MainWindow class.
	private MainWindow parentWindow;
	
	// Required for completing an order.
	private Order currentOrder;
	private SalesCatalog salesCatalog;
	private JButton completeOrder;
	
	/** Constructor */
	public OrderDetailsWindow(MainWindow parentWindow, SalesCatalog salesCatalog, String dialogTitle, Order order, int width, int height) {
	
		super(parentWindow, dialogTitle, "Order", ImageReader.getImage(String.format("%s.png", order.getClass().getName())), order.toString(), width, height);
		completeOrder = new JButton("Complete Order & Deliver Product");
		completeOrder.setBounds(this.getWidth() - 388, this.getHeight() - 83, 250, 30);
		completeOrder.addActionListener(this);
		completeOrder.setEnabled(order.getStatus().equals(Order.ORDER_PENDING));
		JButton closeButton = new JButton("Close");
		closeButton.setBounds(this.getWidth() - 133, this.getHeight() - 83, 100, 30);
		closeButton.setActionCommand(closeButton.getText());
		closeButton.addActionListener(this);
		this.contentPanel.add(completeOrder);
		this.contentPanel.add(closeButton);
		this.parentWindow = parentWindow;
		this.currentOrder = order;
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
		
		Calendar calendar = Calendar.getInstance();
		DateFormat dateParser = new SimpleDateFormat("dd/MM/yyyy");
		String currentDateText = String.format("%02d/%02d/%02d", calendar.get(Calendar.DAY_OF_MONTH), ((calendar.get(Calendar.MONTH) == 12) ? 1 : (calendar.get(Calendar.MONTH) + 1)), calendar.get(Calendar.YEAR));
		Date currentDate;
		Date availableDate;
		
		try {
		
			currentDate = dateParser.parse(currentDateText);
			availableDate = dateParser.parse(this.currentOrder.getAvailableDate());
		
		} catch (Exception error) {
		
			JOptionPane.showMessageDialog(this, "There was an error completing the requested action.", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		
		}
		
		if (currentDate.after(availableDate) || currentDate.equals(availableDate)) {
		
			this.salesCatalog.add(new Sale(this.currentOrder.getProductType(), this.currentOrder.getProduct(), this.currentOrder.getClientName(), this.currentOrder.getClientPhone(), currentDateText, this.currentOrder.getFinalCost()));
			this.currentOrder.setCompleted();
			JOptionPane.showMessageDialog(this, "Order completed & product delivered.", "Order Status", JOptionPane.INFORMATION_MESSAGE);
			this.updateDetails(this.currentOrder.toString());
			this.completeOrder.setEnabled(false);
			this.parentWindow.refreshTabs();
			return;
		
		}
		
		// Cannot complete an order if the product is not available yet.
		JOptionPane.showMessageDialog(this, "Product is not available yet.\nOrder cannot be completed.", "Error", JOptionPane.WARNING_MESSAGE);
	
	}

}