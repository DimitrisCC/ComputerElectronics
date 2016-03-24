package com.computerelectronics.ui;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.computerelectronics.actions.Sale;
import com.computerelectronics.catalogs.SalesCatalog;

// Class representing a tab with all the available sales, as a button list.
public final class SalesTab extends TabPage {

	private static final long serialVersionUID = -7133963341719927938L;

	// Tab state identifier.
	public static enum SalesTabState {
	
		DISPLAY_SALES, DISPLAY_SALES_CUSTOM_PRODUCT_TYPE, DISPLAY_SALES_CUSTOM_PRODUCT_NAME
	
	}
	
	// Tab state indicator.
	private SalesTab.SalesTabState state;
	
	// Filter-out specific sales.
	private JButton showFilteredSales;
	
	/** Constructor */
	public SalesTab(SalesCatalog salesCatalog) {
	
		super(salesCatalog, true);
		this.state = SalesTab.SalesTabState.DISPLAY_SALES;
		this.contentPanelLayout.setHgap(10);
		this.contentPanelLayout.setVgap(52);
		this.showFilteredSales = new JButton("Filter sales (Console)");
		this.showFilteredSales.setBounds(26, 318, 180, 30);
		this.showFilteredSales.addMouseListener(this);
		this.add(this.showFilteredSales);
		this.updateTab(null);
	
	}
	
	// Helper method for updating the tab.
	@Override
	public void updateTab(String filter) {
	
		this.title.setText("Please select a sale to view :");
		this.title.setBounds(25, 28, (int) (this.title.getPreferredSize().getWidth()), (int) (this.title.getPreferredSize().getHeight()));
		this.contentPanel.removeAll();
		Object[] catalogList = null;
		
		if ((this.salesCatalog != null) && ((catalogList = this.salesCatalog.getItems()) != null)) {
		
			switch (this.state) {
			
				case DISPLAY_SALES:
					{
					
						for (Object sale : catalogList) {
						
							this.contentPanel.add(new DoubleClickButton(200, 100, String.format("Sale No. %d", ((Sale) (sale)).getCode()), Font.PLAIN, 12, Color.BLUE, this));
						
						}
					
					} break;
				
				// Display sales for a specific product type.
				case DISPLAY_SALES_CUSTOM_PRODUCT_TYPE:
					{
					
						int count = 0;
						
						for (Object sale : catalogList) {
						
							if (((Sale) (sale)).getProductType().equals(filter)) {
							
								this.contentPanel.add(new DoubleClickButton(200, 100, String.format("Sale No. %d", ((Sale) (sale)).getCode()), Font.PLAIN, 12, Color.BLUE, this));
								count++;
							
							}
						
						}
						
						if (count == 0) this.noSalesToDisplay();
					
					} break;
				
				// Display sales for a specific product.
				case DISPLAY_SALES_CUSTOM_PRODUCT_NAME:
					{
					
						int count = 0;
						
						for (Object sale : catalogList) {
						
							if (((Sale) (sale)).getProduct().equals(filter)) {
							
								this.contentPanel.add(new DoubleClickButton(200, 100, String.format("Sale No. %d", ((Sale) (sale)).getCode()), Font.PLAIN, 12, Color.BLUE, this));
								count++;
							
							}
						
						}
						
						if (count == 0) this.noSalesToDisplay();
					
					} break;
			
			}
		
		} else this.noSalesToDisplay();
		
		this.contentPanel.repaint();
	
	}
	
	// Helper method to inform the user when there are no sales to display.
	private final void noSalesToDisplay() {
	
		JLabel noSales = new JLabel("There are no sales to display.");
		noSales.setFont(new Font("SansSerif", Font.PLAIN, 20));
		this.contentPanel.add(noSales);
	
	}
	
	// Helper method for restoring the tab to its original/"factory" state.
	public final void resetTab() {
	
		if (this.state != SalesTab.SalesTabState.DISPLAY_SALES) {
		
			this.state = SalesTab.SalesTabState.DISPLAY_SALES;
		
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
			
			if (source.equals(this.showFilteredSales) && (clickCount == 1)) {
			
				if (this.salesCatalog.getSize() != 0) {
				
					mainWindow.setEnabled(false);
					String output = this.showCommandLineMenu(false);
					
					if (output != null) {
					
						if (output.equals("all")) {
						
							if (!(this.state == SalesTab.SalesTabState.DISPLAY_SALES)) this.state = SalesTab.SalesTabState.DISPLAY_SALES;
						
						} else if (output.startsWith("pn")) {     // See TabPage class, method "showCommandLineMenu()" for more information
																  // on "pn" and "pt" identifiers.
						
							output = output.replace("pn", "");
							
							if (!(this.state == SalesTab.SalesTabState.DISPLAY_SALES_CUSTOM_PRODUCT_NAME)) this.state = SalesTab.SalesTabState.DISPLAY_SALES_CUSTOM_PRODUCT_NAME;
						
						} else if (output.startsWith("pt")) {
						
							output = output.replace("pt", "");
							
							if (!(this.state == SalesTab.SalesTabState.DISPLAY_SALES_CUSTOM_PRODUCT_TYPE)) this.state = SalesTab.SalesTabState.DISPLAY_SALES_CUSTOM_PRODUCT_TYPE;
						
						}
						
						this.updateTab(output);
					
					}
					
					mainWindow.setEnabled(true);
				
				} else JOptionPane.showMessageDialog(mainWindow, "No sales available.", "Error", JOptionPane.ERROR_MESSAGE);
			
			} else if ((clickCount == 2) && (!(source.equals(this.showFilteredSales)))) {
			
				new SaleDetailsWindow(mainWindow, "Sale Details", this.salesCatalog.get(((JButton) (source)).getText().replace("Sale No. ", "")), TabPage.DETAILS_WINDOW_WIDTH, TabPage.DETAILS_WINDOW_HEIGHT);
			
			}
		
		}
	
	}

}