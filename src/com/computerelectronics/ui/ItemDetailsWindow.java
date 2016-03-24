package com.computerelectronics.ui;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

import com.computerelectronics.io.utils.ImageReader;

// Skeleton class representing a dialog window for displaying an item's details.
public abstract class ItemDetailsWindow extends JDialog implements ActionListener {

	private static final long serialVersionUID = -8429465250869084752L;
	
	public static final int CLIENT_NAME_MAX = 30;
	public static final int CLIENT_PHONE_MAX = 10;
	
	// Also required by subclasses, with direct access.
	protected Container contentPanel;
	
	private JTextArea details;
	
	/** Constructor */
	public ItemDetailsWindow(MainWindow parentWindow, String dialogTitle, String itemTitle, Image itemImage, String itemDetails, int width, int height) {
	
		super((Frame) (parentWindow), dialogTitle, true);
		this.setSize(width, height);
		this.setLocationRelativeTo((Component) (parentWindow));
		this.setResizable(false);
		this.contentPanel = this.getContentPane();
		this.contentPanel.setLayout(null);
		JLabel previewImage = new JLabel();
		previewImage.setBounds(10, 10, 128, 128);
		previewImage.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, false));
		
		if (itemImage != null) {
		
			previewImage.setIcon(new ImageIcon(itemImage));
		
		} else {     // In case of an error, display an error message instead of the image.
		
			previewImage.setText(ImageReader.IMAGE_ERROR);
		
		}
		
		previewImage.setHorizontalAlignment(SwingConstants.CENTER);
		JLabel title = new JLabel(itemTitle);
		title.setBounds(143, 10, this.getWidth() - 160, 128);
		title.setFont(new Font("SansSerif", Font.BOLD, 24));
		title.setHorizontalAlignment(SwingConstants.CENTER);
		details = new JTextArea(itemDetails);
		details.setDragEnabled(false);
		details.setEditable(false);
		details.setMargin(new Insets(15, 15, 15, 15));
		details.setLineWrap(true);
		details.setFont(new Font("SansSerif", Font.PLAIN, 14));
		JScrollPane detailsPanel = new JScrollPane(details);     // Add scrolling support to the item details panel.
		detailsPanel.setBounds(10, 148, this.getWidth() - 37, this.getHeight() - 244);
		detailsPanel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		this.contentPanel.add(previewImage);
		this.contentPanel.add(title);
		this.contentPanel.add(detailsPanel);
	
	}
	
	// Helper method to update the current item's details.
	// Used only by subclasses.
	protected final void updateDetails(String details) {
	
		this.details.setText(details);
		this.repaint();
	
	}

}