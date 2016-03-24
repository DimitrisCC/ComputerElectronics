package com.computerelectronics.ui;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.computerelectronics.actions.Order;
import com.computerelectronics.catalogs.OrdersCatalog;
import com.computerelectronics.catalogs.SalesCatalog;

// Class representing a tab with all the available orders, as a button list.
public final class OrdersTab extends TabPage {

	private static final long serialVersionUID = -1561637956669184270L;

	// Tab state identifier.
	public static enum OrdersTabState {
	
		DISPLAY_ORDERS, DISPLAY_ORDERS_CUSTOM_PRODUCT_TYPE, DISPLAY_ORDERS_CUSTOM_PRODUCT_NAME
	
	}
	
	// Tab state indicator.
	private OrdersTab.OrdersTabState state;
	
	private OrdersCatalog ordersCatalog;
	
	// Filter-out specific orders.
	private JButton showFilteredOrders;
	
	/** Constructor */
	public OrdersTab(OrdersCatalog ordersCatalog, SalesCatalog salesCatalog) {
	
		super(salesCatalog, true);
		this.state = OrdersTab.OrdersTabState.DISPLAY_ORDERS;
		this.contentPanelLayout.setHgap(10);
		this.contentPanelLayout.setVgap(52);
		this.ordersCatalog = ordersCatalog;
		this.showFilteredOrders = new JButton("Filter orders (Console)");
		this.showFilteredOrders.setBounds(26, 318, 180, 30);
		this.showFilteredOrders.addMouseListener(this);
		this.add(this.showFilteredOrders);
		this.updateTab(null);
	
	}
	
	// Helper method for updating the tab.
	@Override
	public void updateTab(String filter) {
	
		this.title.setText("Please select an order to view :");
		this.title.setBounds(25, 28, (int) (this.title.getPreferredSize().getWidth()), (int) (this.title.getPreferredSize().getHeight()));
		this.contentPanel.removeAll();
		Object[] catalogList = null;
		
		if ((this.ordersCatalog != null) && ((catalogList = this.ordersCatalog.getItems()) != null)) {
		
			switch (this.state) {
			
				case DISPLAY_ORDERS:
					{
					
						for (Object order : catalogList) {
						
							this.contentPanel.add(new DoubleClickButton(200, 100, String.format("Order No. %d", ((Order) (order)).getCode()), Font.PLAIN, 12, Color.RED, this));
						
						}
					
					} break;
				
				// Display orders for a specific product type.
				case DISPLAY_ORDERS_CUSTOM_PRODUCT_TYPE:
					{
					
						int count = 0;
						
						for (Object order : catalogList) {
						
							if (((Order) (order)).getProductType().equals(filter)) {
							
								this.contentPanel.add(new DoubleClickButton(200, 100, String.format("Order No. %d", ((Order) (order)).getCode()), Font.PLAIN, 12, Color.RED, this));
								count++;
							
							}
						
						}
						
						if (count == 0) this.noOrdersToDisplay();
					
					} break;
				
				// Display orders for a specific product.
				case DISPLAY_ORDERS_CUSTOM_PRODUCT_NAME:
					{
					
						int count = 0;
						
						for (Object order : catalogList) {
						
							if (((Order) (order)).getProduct().equals(filter)) {
							
								this.contentPanel.add(new DoubleClickButton(200, 100, String.format("Order No. %d", ((Order) (order)).getCode()), Font.PLAIN, 12, Color.RED, this));
								count++;
							
							}
						
						}
						
						if (count == 0) this.noOrdersToDisplay();
					
					} break;
			
			}
		
		} else this.noOrdersToDisplay();
		
		this.contentPanel.repaint();
	
	}
	
	// Helper method to inform the user when there are no orders to display.
	private final void noOrdersToDisplay() {
	
		JLabel noOrders = new JLabel("There are no orders to display.");
		noOrders.setFont(new Font("SansSerif", Font.PLAIN, 20));
		this.contentPanel.add(noOrders);
	
	}
	
	// Helper method for restoring the tab to its original/"factory" state.
	public final void resetTab() {
	
		if (this.state != OrdersTab.OrdersTabState.DISPLAY_ORDERS) {
		
			this.state = OrdersTab.OrdersTabState.DISPLAY_ORDERS;
		
		}
		
		this.updateTab(null);
	
	}
	
	// Mouse handler.
	@Override
	public final void mouseClicked(MouseEvent event) {
	
		if (!(event.isConsumed())) {
		
			event.consume();
			MainWindow mainWindow = (MainWindow) (this.getRootPane().getParent());
			Component source = (Component) (event.getSource());
			int clickCount = event.getClickCount();
			
			if (source.equals(this.showFilteredOrders) && (clickCount == 1)) {
			
				if (this.ordersCatalog.getSize() != 0) {
				
					mainWindow.setEnabled(false);
					String output = this.showCommandLineMenu(true);
					
					if (output != null) {
					
						if (output.equals("all")) {
						
							if (!(this.state == OrdersTab.OrdersTabState.DISPLAY_ORDERS)) this.state = OrdersTab.OrdersTabState.DISPLAY_ORDERS;
						
						} else if (output.startsWith("pn")) {     // See TabPage class, method "showCommandLineMenu()" for more information
																  // on "pn" and "pt" identifiers.
						
							output = output.replace("pn", "");
							
							if (!(this.state == OrdersTab.OrdersTabState.DISPLAY_ORDERS_CUSTOM_PRODUCT_NAME)) this.state = OrdersTab.OrdersTabState.DISPLAY_ORDERS_CUSTOM_PRODUCT_NAME;
						
						} else if (output.startsWith("pt")) {
						
							output = output.replace("pt", "");
							
							if (!(this.state == OrdersTab.OrdersTabState.DISPLAY_ORDERS_CUSTOM_PRODUCT_TYPE)) this.state = OrdersTab.OrdersTabState.DISPLAY_ORDERS_CUSTOM_PRODUCT_TYPE;
						
						}
						
						this.updateTab(output);
					
					}
					
					mainWindow.setEnabled(true);
				
				} else JOptionPane.showMessageDialog(mainWindow, "No orders available.", "Error", JOptionPane.ERROR_MESSAGE);
			
			} else if ((clickCount == 2) && (!(source.equals(this.showFilteredOrders)))) {
			
				new OrderDetailsWindow(mainWindow, this.salesCatalog, "Order Details", this.ordersCatalog.get(((JButton) (source)).getText().replace("Order No. ", "")), TabPage.DETAILS_WINDOW_WIDTH, TabPage.DETAILS_WINDOW_HEIGHT);
			
			}
		
		}
	
	}

}