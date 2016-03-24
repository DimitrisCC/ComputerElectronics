package com.computerelectronics.ui;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JLabel;

import com.computerelectronics.catalogs.OrdersCatalog;
import com.computerelectronics.catalogs.ProductCatalog;
import com.computerelectronics.catalogs.SalesCatalog;
import com.computerelectronics.products.Product;

// Class representing the products tab.
public final class ProductsTab extends TabPage {

	private static final long serialVersionUID = 5523354811623889878L;

	// Indicate the tab's state by using enum values.
	public static enum ProductsTabState {
	
		START_PAGE, COMPONENTS_PAGE, PERIPHERALS_PAGE, COMPONENT_ITEMS_PAGE, PERIPHERAL_ITEMS_PAGE
	
	}
	
	// Tab state indicator.
	private ProductsTab.ProductsTabState state;
	
	private JButton backButton;
	
	// Required for viewing and purchasing a product.
	private ProductCatalog productCatalog;
	private OrdersCatalog ordersCatalog;
	
	/** Constructor */
	public ProductsTab(ProductCatalog productCatalog, OrdersCatalog ordersCatalog, SalesCatalog salesCatalog) {
	
		super(salesCatalog, false);
		this.state = ProductsTab.ProductsTabState.START_PAGE;
		this.contentPanelLayout.setVgap(52);
		this.backButton = new JButton("Back");
		this.backButton.setBounds(26, 318, 100, 30);
		this.backButton.addMouseListener(this);
		this.add(this.backButton);
		this.productCatalog = productCatalog;
		this.ordersCatalog = ordersCatalog;
		this.updateTab(null);
	
	}
	
	// Helper method for updating the tab.
	@Override
	public final void updateTab(String filter) {
	
		this.contentPanel.removeAll();
		
		switch (this.state) {
		
			case START_PAGE:
				{
				
					if (this.backButton.isVisible()) this.backButton.setVisible(false);
					
					this.title.setText("Please select a category :");
					this.title.setBounds(25, 28, (int) (this.title.getPreferredSize().getWidth()), (int) (this.title.getPreferredSize().getHeight()));
					this.contentPanelLayout.setHgap(66);
					DoubleClickButton components = new DoubleClickButton(230, 100, "Components", Font.PLAIN, 18, Color.ORANGE, this);
					DoubleClickButton peripherals = new DoubleClickButton(230, 100, "Peripherals", Font.PLAIN, 18, Color.ORANGE, this);
					this.contentPanel.add(components);
					this.contentPanel.add(peripherals);
				
				} break;
			
			case COMPONENTS_PAGE:
				{
				
					if (!(this.backButton.isVisible())) this.backButton.setVisible(true);
					
					this.title.setText("Please select a Component category :");
					this.title.setBounds(25, 28, (int) (this.title.getPreferredSize().getWidth()), (int) (this.title.getPreferredSize().getHeight()));
					this.contentPanelLayout.setHgap(10);
					DoubleClickButton cpus = new DoubleClickButton(120, 100, "CPUs", Font.PLAIN, 14, Color.ORANGE, this);
					DoubleClickButton motherboards = new DoubleClickButton(120, 100, "Motherboards", Font.PLAIN, 14, Color.ORANGE, this);
					DoubleClickButton rams = new DoubleClickButton(120, 100, "RAMs", Font.PLAIN, 14, Color.ORANGE, this);
					DoubleClickButton graphicsCards = new DoubleClickButton(120, 100, "Graphics Cards", Font.PLAIN, 12, Color.ORANGE, this);
					DoubleClickButton hardDrives = new DoubleClickButton(120, 100, "Hard Drives", Font.PLAIN, 14, Color.ORANGE, this);
					this.contentPanel.add(cpus);
					this.contentPanel.add(motherboards);
					this.contentPanel.add(rams);
					this.contentPanel.add(graphicsCards);
					this.contentPanel.add(hardDrives);
				
				} break;
			
			case PERIPHERALS_PAGE:
				{
				
					if (!(this.backButton.isVisible())) this.backButton.setVisible(true);
					
					this.title.setText("Please select a Peripheral category :");
					this.title.setBounds(25, 28, (int) (this.title.getPreferredSize().getWidth()), (int) (this.title.getPreferredSize().getHeight()));
					this.contentPanelLayout.setHgap(10);
					DoubleClickButton monitors = new DoubleClickButton(151, 100, "Monitors", Font.PLAIN, 14, Color.ORANGE, this);
					DoubleClickButton keyboards = new DoubleClickButton(151, 100, "Keyboards", Font.PLAIN, 14, Color.ORANGE, this);
					DoubleClickButton mice = new DoubleClickButton(151, 100, "Mice", Font.PLAIN, 14, Color.ORANGE, this);
					DoubleClickButton printers = new DoubleClickButton(151, 100, "Printers", Font.PLAIN, 14, Color.ORANGE, this);
					this.contentPanel.add(monitors);
					this.contentPanel.add(keyboards);
					this.contentPanel.add(mice);
					this.contentPanel.add(printers);
				
				} break;
			
			// Show available items.
			case COMPONENT_ITEMS_PAGE:
			case PERIPHERAL_ITEMS_PAGE:
				{
				
					this.title.setText("Please select a product :");
					this.title.setBounds(25, 28, (int) (this.title.getPreferredSize().getWidth()), (int) (this.title.getPreferredSize().getHeight()));
					Object[] catalogList = null;
					
					if ((filter != null) && (this.productCatalog != null) && ((catalogList = this.productCatalog.getFilteredItems(filter)) != null)) {
					
						this.contentPanelLayout.setHgap(10);
						
						for (Object product : catalogList) {
						
							this.contentPanel.add(new DoubleClickButton(200, 100, ((Product) (product)).getName(), Font.PLAIN, 12, Color.ORANGE, this));
						
						}
					
					} else {
					
						JLabel noComponents = new JLabel("There are no available products to display.");
						noComponents.setFont(new Font("SansSerif", Font.PLAIN, 20));
						this.contentPanel.add(noComponents);
					
					}
				
				} break;
		
		}
		
		// Remember to reflect the changes made by forcing the tab to repaint itself.
		this.contentPanel.repaint();
	
	}
	
	// Helper method for restoring the tab to its original/"factory" state.
	public final void resetTab() {
	
		if (this.state != ProductsTab.ProductsTabState.START_PAGE) {
		
			this.state = ProductsTab.ProductsTabState.START_PAGE;
			this.updateTab(null);
		
		}
	
	}
	
	// Mouse handler.
	@Override
	public final void mouseClicked(MouseEvent event) {
	
		if (!(event.isConsumed())) {
		
			event.consume();
			Component source = event.getComponent();
			int clickCount = event.getClickCount();
			
			if (source.equals(this.backButton) && (clickCount == 1)) {     // Go back one level.
			
				switch (this.state) {
				
					case COMPONENT_ITEMS_PAGE:
						{
						
							this.state = ProductsTab.ProductsTabState.COMPONENTS_PAGE;
						
						} break;
					
					case PERIPHERAL_ITEMS_PAGE:
						{
						
							this.state = ProductsTab.ProductsTabState.PERIPHERALS_PAGE;
						
						} break;
					
					case COMPONENTS_PAGE:
					case PERIPHERALS_PAGE:
						{
						
							this.state = ProductsTab.ProductsTabState.START_PAGE;
						
						} break;
					
					default: break;
				
				}
				
				this.updateTab(null);
			
			} else if ((clickCount == 2) && (!(source.equals(this.backButton)))) {     // A "NullPointerException" may be thrown while browsing, so
																					   // we use "(!(source.equals(this.backButton)))" to avoid this.
				String sourceName = source.getName();
				String filter = null;     // Filter will be always be something like "cpu" for a CPU component, "motherboard" for
										  // a Motherboard component, e.t.c.
				
				if (sourceName.equals("Components")) {
				
					this.state = ProductsTab.ProductsTabState.COMPONENTS_PAGE;
				
				} else if (sourceName.equals("Peripherals")) {
				
					this.state = ProductsTab.ProductsTabState.PERIPHERALS_PAGE;
				
				} else if (sourceName.equals("CPUs") || sourceName.equals("Motherboards") || sourceName.equals("RAMs") || sourceName.equals("Graphics Cards") || sourceName.equals("Hard Drives")) {
				
					this.state = ProductsTab.ProductsTabState.COMPONENT_ITEMS_PAGE;
					filter = sourceName.substring(0, sourceName.length() - 1).replace(" ", "").toLowerCase();
				
				} else if (sourceName.equals("Monitors") || sourceName.equals("Keyboards") || sourceName.equals("Mice") || sourceName.equals("Printers")) {
				
					this.state = ProductsTab.ProductsTabState.PERIPHERAL_ITEMS_PAGE;
					
					if (!(sourceName.equals("Mice"))) {
					
						filter = sourceName.substring(0, sourceName.length() - 1).toLowerCase();
					
					} else filter = "mouse";
				
				} else {
				
					new ProductDetailsWindow((MainWindow) (this.getRootPane().getParent()), this.ordersCatalog, this.salesCatalog, "Product Details", this.productCatalog.get(sourceName), TabPage.DETAILS_WINDOW_WIDTH, TabPage.DETAILS_WINDOW_HEIGHT);
					return;
				
				}
				
				this.updateTab(filter);
			
			}
		
		}
	
	}

}