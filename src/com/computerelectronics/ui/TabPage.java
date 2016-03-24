package com.computerelectronics.ui;
import java.awt.Color;
import java.awt.Font;
import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Scanner;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.computerelectronics.catalogs.SalesCatalog;

// Skeleton class representing a tab.
public abstract class TabPage extends JPanel implements MouseListener {
	
	private static final long serialVersionUID = -6240456101554745274L;
	// Details window indicators.
	protected static final int DETAILS_WINDOW_WIDTH = 550;
	protected static final int DETAILS_WINDOW_HEIGHT = 450;
	
	// Parser for the command-line options menu.
	// (Required by OrdersTab and SalesTab classes.)
	protected Scanner commandLineParser = null;
	
	// Required by all subclasses, with direct access.
	protected SalesCatalog salesCatalog;
	protected JLabel title;
	protected JPanel contentPanel;
	protected FlowLayout contentPanelLayout;
	
	/** Constructor */
	protected TabPage(SalesCatalog salesCatalog, boolean enableCommandLineMenu) {
	
		super();
		this.setLayout(null);
		this.title = new JLabel();
		this.title.setFont(new Font("SansSerif", Font.BOLD, 22));
		this.contentPanel = new JPanel();
		this.contentPanelLayout = (FlowLayout) (this.contentPanel.getLayout());
		JScrollPane containerPanel = new JScrollPane(contentPanel);     // Add scrolling support to the content panel.
		containerPanel.setBounds(10, 90, 674, 210);
		containerPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, false));
		this.add(this.title);
		this.add(containerPanel);
		this.salesCatalog = salesCatalog;
		
		if (enableCommandLineMenu) this.commandLineParser = new Scanner(System.in);     // Add command-line support only if
																						// the subclass needs it.
	
	}
	
	// Helper method for updating the tab based on its state.
	// The "filter" parameter is used only when displaying available products, otherwise it is
	// ignored and it should be null.
	// This method is shared among all subclasses, with similar implementation in each subclass.
	public abstract void updateTab(String filter);
	
	// Helper method for displaying the command line menu.
	// The String value returned is used as a filter in the "updateTab()" methods.
	// (Only for OrdersTab and SalesTab classes.)
	protected final String showCommandLineMenu(boolean order) {
	
		if (this.commandLineParser != null) {
		
			String caption = (order) ? "order" : "sale";
			System.out.println();
			System.out.println();
			System.out.println("****************************");
			System.out.println("*                          *");
			System.out.println("* ~ Computer Electronics ~ *");
			System.out.println("*                          *");
			System.out.println("****************************");
			System.out.println();
			
			while (true) {
			
				System.out.println(String.format("1. Show only %ss of a specific product.", caption));
				System.out.println(String.format("2. Show only %ss of a specific product type.", caption));
				System.out.println(String.format("3. Show all %ss.", caption));
				System.out.println("4. Return to the main window.");
				System.out.println();
				System.out.println("Please select an option [1-4] :");
				int option = readOptions(4);
				
				switch (option) {
				
					case 1:
						{
						
							System.out.println();
							System.out.println("============================");
							System.out.println();
							System.out.println("Please type the name of the product :");
							System.out.println("(Or type \"back\" to go back ...)");
							System.out.println();
							System.out.print("-> ");
							String input = this.commandLineParser.nextLine();
							
							if (!(input.equals("back"))) {
							
								System.out.println();
								System.out.println(" < Look at the main window again... >");
								return "pn" + input;     // "pn" is used for filter identification by subclasses. (OrdersTab and SalesTab only)
														 // It shows that the filter represents a product name.
							
							}
							
							System.out.println();
							System.out.println("============================");
							System.out.println();
						
						} break;
					
					case 2:
						{
						
							System.out.println();
							System.out.println("============================");
							System.out.println();
							System.out.println("~~ Components ~~");
							System.out.println();
							System.out.println(String.format("1. List all CPU %ss", caption));
							System.out.println(String.format("2. List all Motherboard %ss", caption));
							System.out.println(String.format("3. List all RAM %ss", caption));
							System.out.println(String.format("4. List all Graphics Card %ss", caption));
							System.out.println(String.format("5. List all Hard Drive %ss", caption));
							System.out.println();
							System.out.println("----------------------------");
							System.out.println();
							System.out.println("~~ Peripherals ~~");
							System.out.println();
							System.out.println(String.format("6. List all Monitor %ss", caption));
							System.out.println(String.format("7. List all Keyboard %ss", caption));
							System.out.println(String.format("8. List all Mouse %ss", caption));
							System.out.println(String.format("9. List all Printer %ss", caption));
							System.out.println();
							System.out.println("----------------------------");
							System.out.println();
							System.out.println("10. Go back");
							System.out.println();
							System.out.println("Please select an option [1-9] :");
							option = readOptions(10);
							System.out.println();
							
							switch (option) {
							
								case 1:
									{
									
										System.out.println(" < Look at the main window again... >");
									
									} return "ptcpu";     // "pt" is used for filter identification by subclasses. (OrdersTab and SalesTab only)
														  // It shows that the filter represents a product type.
								
								case 2:
									{
									
										System.out.println(" < Look at the main window again... >");
									
									} return "ptmotherboard";
								
								case 3:
									{
									
										System.out.println(" < Look at the main window again... >");
									
									} return "ptram";
								
								case 4:
									{
									
										System.out.println(" < Look at the main window again... >");
									
									} return "ptgraphicscard";
								
								case 5:
									{
									
										System.out.println(" < Look at the main window again... >");
									
									} return "ptharddrive";
								
								case 6:
									{
									
										System.out.println(" < Look at the main window again... >");
									
									} return "ptmonitor";
								
								case 7:
									{
									
										System.out.println(" < Look at the main window again... >");
									
									} return "ptkeyboard";
								
								case 8:
									{
									
										System.out.println(" < Look at the main window again... >");
									
									} return "ptmouse";
								
								case 9:
									{
									
										System.out.println(" < Look at the main window again... >");
									
									} return "ptprinter";
								
								case 10:
									{
									
										System.out.println("============================");
										System.out.println();
									
									} break;
							
							}
						
						} break;
					
					case 3:
						{
						
							System.out.println();
							System.out.println(" < Look at the main window again... >");
						
						} return "all";
					
					case 4:
						{
						
							System.out.println();
							System.out.println(" < Look at the main window again... >");
						
						} return "cancel";
				
				}
			
			}
		
		}
		
		return null;     // In case the method is called, without the subclass having command-line support.
	
	}
	
	// Helper method to read available options from the console.
	protected final int readOptions(int optionNumber) {
	
		if (this.commandLineParser != null) {
		
			int option;
			
			while (true) {
			
				try {
				
					System.out.println();
					System.out.print("-> ");
					option = Integer.parseInt(this.commandLineParser.nextLine());
					
					for (int i = 0 ; i < optionNumber ; i++) {
					
						if (option == (i + 1)) return option;
					
					}
					
					System.out.println();
					System.out.printf("Invalid option selected. Please select an option between 1 and %d.", optionNumber);
					System.out.println();
				
				} catch (Exception exception) {
				
					System.out.println();
					System.out.println("Invalid input. Please try again ...");
				
				}
			
			}
		
		}
		
		return -1;     // In case the method is called, without the subclass having command-line support.
	
	}
	
	//Clean-Up method.
	@Override
	protected final void finalize() {
	
		try {
		
			if (this.commandLineParser != null) {
			
				this.commandLineParser.close();
				this.commandLineParser = null;
			
			}
			
			super.finalize();
		
		} catch (Throwable error) {     /* NO EXCEPTION SHOULD BE THROWN IN THIS METHOD, IN GENERAL. */     }
	
	}
	
	// These functions/mouse handlers are required only by the "MouseListener" interface, and serve
	// no purpose in this class and subclasses.
	@Override
	public final void mouseEntered(MouseEvent event) {}
	
	@Override
	public final void mouseExited(MouseEvent event) {}
	
	@Override
	public final void mousePressed(MouseEvent event) {}
	
	@Override
	public final void mouseReleased(MouseEvent event) {}

}