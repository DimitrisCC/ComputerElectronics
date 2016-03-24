package com.computerelectronics.ui;
import java.awt.Container;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;

import com.computerelectronics.actions.Order;
import com.computerelectronics.actions.Sale;
import com.computerelectronics.catalogs.OrdersCatalog;
import com.computerelectronics.catalogs.ProductCatalog;
import com.computerelectronics.catalogs.SalesCatalog;
import com.computerelectronics.io.OrdersReader;
import com.computerelectronics.io.OrdersWriter;
import com.computerelectronics.io.ProductReader;
import com.computerelectronics.io.ProductWriter;
import com.computerelectronics.io.SalesReader;
import com.computerelectronics.io.SalesWriter;
import com.computerelectronics.io.utils.ImageReader;
import com.computerelectronics.products.Product;

// Application's main window class.
public final class MainWindow extends JFrame implements ActionListener {

	private static final long serialVersionUID = -4138931006073404299L;

	public static final String APPLICATION_TITLE = "Computer Electronics";
	
	public static final int WINDOW_WIDTH = 700;
	public static final int WINDOW_HEIGHT = 550;
	
	// Catalogs.
	private static ProductCatalog products;
	private static OrdersCatalog orders;
	private static SalesCatalog sales;
	
	// Tab panel & tabs.
	private JTabbedPane tabPanel;
	private ProductsTab productsTab;
	private OrdersTab ordersTab;
	private SalesTab salesTab;
	
	/** Constructor */
	public MainWindow(String title, List<? extends Image> icon, int width, int height) {
	
		super(title);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(width, height);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		
		if (icon != null) this.setIconImages(icon);
		
		MainWindow.products = new ProductCatalog();
		MainWindow.orders = new OrdersCatalog();
		MainWindow.sales = new SalesCatalog();
		
		// Create the main menu.
		JMenuBar mainMenuBar = new JMenuBar();
		JMenu mainMenu = new JMenu("Menu");
		
		JMenuItem loadProductsCatalog = new JMenuItem("Load Products Catalog");
		loadProductsCatalog.setActionCommand(loadProductsCatalog.getText());
		loadProductsCatalog.addActionListener(this);
		
		JMenuItem loadOrdersCatalog = new JMenuItem("Load Orders Catalog");
		loadOrdersCatalog.setActionCommand(loadOrdersCatalog.getText());
		loadOrdersCatalog.addActionListener(this);
		
		JMenuItem loadSalesCatalog = new JMenuItem("Load Sales Catalog");
		loadSalesCatalog.setActionCommand(loadSalesCatalog.getText());
		loadSalesCatalog.addActionListener(this);
		
		JMenuItem saveProductsCatalog = new JMenuItem("Save Products Catalog");
		saveProductsCatalog.setActionCommand(saveProductsCatalog.getText());
		saveProductsCatalog.addActionListener(this);
		
		JMenuItem saveOrdersCatalog = new JMenuItem("Save Orders Catalog");
		saveOrdersCatalog.setActionCommand(saveOrdersCatalog.getText());
		saveOrdersCatalog.addActionListener(this);
		
		JMenuItem saveSalesCatalog = new JMenuItem("Save Sales Catalog");
		saveSalesCatalog.setActionCommand(saveSalesCatalog.getText());
		saveSalesCatalog.addActionListener(this);
		
		JMenuItem exit = new JMenuItem("Exit");
		exit.setActionCommand(exit.getText());
		exit.addActionListener(this);
		
		mainMenu.add(loadProductsCatalog);
		mainMenu.add(saveProductsCatalog);
		mainMenu.addSeparator();
		mainMenu.add(loadOrdersCatalog);
		mainMenu.add(saveOrdersCatalog);
		mainMenu.addSeparator();
		mainMenu.add(loadSalesCatalog);
		mainMenu.add(saveSalesCatalog);
		mainMenu.addSeparator();
		mainMenu.add(exit);
		mainMenuBar.add(mainMenu);
		this.setJMenuBar(mainMenuBar);
		
		this.setLayout(null);
		JLabel logo = new JLabel();
		logo.setBounds(0, 0, 700, 100);
		Image logoImage = ImageReader.getImage("Logo.jpg");
		
		if (logoImage != null) {
		
			logo.setIcon(new ImageIcon(logoImage));
		
		} else {
		
			logo.setText(ImageReader.IMAGE_ERROR);
			logo.setHorizontalAlignment(SwingConstants.CENTER);
		
		}
		
		tabPanel = new JTabbedPane();
		tabPanel.setBounds(0, 100, MainWindow.WINDOW_WIDTH - 6, MainWindow.WINDOW_HEIGHT - 151);
		productsTab = new ProductsTab(MainWindow.products, MainWindow.orders, MainWindow.sales);
		ordersTab = new OrdersTab(MainWindow.orders, MainWindow.sales);
		salesTab = new SalesTab(MainWindow.sales);
		
		// Spaces are used, in order for the tabs to have the same size. (width)
		tabPanel.addTab("Products  ", productsTab);
		tabPanel.addTab(" Orders   ", ordersTab);
		tabPanel.addTab("  Sales   ", salesTab);
		
		Image productsTabIcon = ImageReader.getImage("ProductsTab.png");
		Image ordersTabIcon = ImageReader.getImage("OrdersTab.png");
		Image salesTabIcon = ImageReader.getImage("SalesTab.png");
		
		if (productsTabIcon != null) tabPanel.setIconAt(tabPanel.indexOfTab("Products  "), new ImageIcon(productsTabIcon));
		
		if (ordersTabIcon != null) tabPanel.setIconAt(tabPanel.indexOfTab(" Orders   "), new ImageIcon(ordersTabIcon));
		
		if (salesTabIcon != null) tabPanel.setIconAt(tabPanel.indexOfTab("  Sales   "), new ImageIcon(salesTabIcon));
		
		Container mainPanel = this.getContentPane();
		mainPanel.add(logo);
		mainPanel.add(tabPanel);
		this.setVisible(true);
	
	}
	
	// Helper method to refresh the main window's tab panel.
	public final void refreshTabs() {
	
		this.productsTab.resetTab();
		this.ordersTab.resetTab();
		this.salesTab.resetTab();
	
	}
	
	// Main menu and catalog-open/save handler.
	@Override
	public void actionPerformed(ActionEvent event) {
	
		JFileChooser fileSelector = new JFileChooser(".");
		fileSelector.setAcceptAllFileFilterUsed(true);
		fileSelector.setDialogType(JFileChooser.OPEN_DIALOG);
		fileSelector.setDragEnabled(false);
		fileSelector.setFileSelectionMode(JFileChooser.FILES_ONLY);
		fileSelector.setMultiSelectionEnabled(false);
		String command = event.getActionCommand();
		fileSelector.setDialogTitle(command);
		int result;
		
		if (command.startsWith("Load")) {
		
			result = fileSelector.showDialog(this, "Load");
			
			if (result == JFileChooser.APPROVE_OPTION) {
			
				try {
				
					if (command.equals("Load Products Catalog")) {
					
						ProductReader productReader = new ProductReader();
						productReader.initialize(fileSelector.getSelectedFile().getAbsolutePath());
						Object[] productReaderOutput = productReader.read(this);
						productReader.close();
						MainWindow.products.emptyCatalog();
						
						for (Object currentProduct : productReaderOutput) {
						
							MainWindow.products.add((Product) (currentProduct));
						
						}
						
						JOptionPane.showMessageDialog(this, "Product Catalog loaded successfully.", MainWindow.APPLICATION_TITLE, JOptionPane.INFORMATION_MESSAGE);
					
					} else if (command.equals("Load Orders Catalog")) {
					
						OrdersReader ordersReader = new OrdersReader();
						ordersReader.initialize(fileSelector.getSelectedFile().getAbsolutePath());
						Object[] ordersReaderOutput = ordersReader.read(this, MainWindow.products.getItems());
						ordersReader.close();
						MainWindow.orders.emptyCatalog();
						
						for (Object currentOrder : ordersReaderOutput) {
						
							MainWindow.orders.add((Order) (currentOrder));
						
						}
						
						JOptionPane.showMessageDialog(this, "Orders Catalog loaded successfully.", MainWindow.APPLICATION_TITLE, JOptionPane.INFORMATION_MESSAGE);
					
					} else if (command.equals("Load Sales Catalog")) {
					
						SalesReader salesReader = new SalesReader();
						salesReader.initialize(fileSelector.getSelectedFile().getAbsolutePath());
						Object[] salesReaderOutput = salesReader.read(this, MainWindow.products.getItems());
						salesReader.close();
						MainWindow.sales.emptyCatalog();
						
						for (Object currentSale : salesReaderOutput) {
						
							MainWindow.sales.add((Sale) (currentSale));
						
						}
						
						JOptionPane.showMessageDialog(this, "Sales Catalog loaded successfully.", MainWindow.APPLICATION_TITLE, JOptionPane.INFORMATION_MESSAGE);
					
					}
					
					this.refreshTabs();
					this.tabPanel.setSelectedComponent(this.productsTab);
				
				} catch (Exception loadError) {
				
					JOptionPane.showMessageDialog(this, loadError.getMessage(), MainWindow.APPLICATION_TITLE, JOptionPane.ERROR_MESSAGE);
				
				}
			
			}
		
		} else if (command.startsWith("Save")) {
		
			result = fileSelector.showDialog(this, "Save");
			
			if (result == JFileChooser.APPROVE_OPTION) {
			
				try {
				
					if (command.equals("Save Products Catalog")) {
					
						ProductWriter productWriter = new ProductWriter();
						productWriter.initialize(fileSelector.getSelectedFile().getAbsolutePath());
						productWriter.write(MainWindow.products);
						productWriter.close();
						JOptionPane.showMessageDialog(this, "Product Catalog saved successfully.", MainWindow.APPLICATION_TITLE, JOptionPane.INFORMATION_MESSAGE);
					
					} else if (command.equals("Save Orders Catalog")) {
					
						OrdersWriter ordersWriter = new OrdersWriter();
						ordersWriter.initialize(fileSelector.getSelectedFile().getAbsolutePath());
						ordersWriter.write(MainWindow.orders);
						ordersWriter.close();
						JOptionPane.showMessageDialog(this, "Orders Catalog saved successfully.", MainWindow.APPLICATION_TITLE, JOptionPane.INFORMATION_MESSAGE);
					
					} else if (command.equals("Save Sales Catalog")) {
					
						SalesWriter salesWriter = new SalesWriter();
						salesWriter.initialize(fileSelector.getSelectedFile().getAbsolutePath());
						salesWriter.write(MainWindow.sales);
						salesWriter.close();
						JOptionPane.showMessageDialog(this, "Sales Catalog saved successfully.", MainWindow.APPLICATION_TITLE, JOptionPane.INFORMATION_MESSAGE);
					
					}
					
					this.refreshTabs();
					this.tabPanel.setSelectedComponent(this.productsTab);
				
				} catch (Exception loadError) {
				
					JOptionPane.showMessageDialog(this, loadError.getMessage(), MainWindow.APPLICATION_TITLE, JOptionPane.ERROR_MESSAGE);
				
				}
			
			}
		
		} else if (command.equals("Exit")) {
		
			this.setVisible(false);
			this.dispose();
		
		}
	
	}

}