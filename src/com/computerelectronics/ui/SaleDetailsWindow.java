package com.computerelectronics.ui;
import java.awt.event.ActionEvent;
import javax.swing.JButton;

import com.computerelectronics.actions.Sale;
import com.computerelectronics.io.utils.ImageReader;

// Class representing a dialog window for displaying a sale's details.
public final class SaleDetailsWindow extends ItemDetailsWindow {

	private static final long serialVersionUID = -896847671376392229L;

	/** Constructor */
	public SaleDetailsWindow(MainWindow parentWindow, String dialogTitle, Sale sale, int width, int height) {
	
		super(parentWindow, dialogTitle, "Sale", ImageReader.getImage(String.format("%s.png", sale.getClass().getName())), sale.toString(), width, height);
		JButton closeButton = new JButton("Close");
		closeButton.setBounds(this.getWidth() - 133, this.getHeight() - 83, 100, 30);
		closeButton.setActionCommand(closeButton.getText());
		closeButton.addActionListener(this);
		this.contentPanel.add(closeButton);
		this.setVisible(true);
	
	}
	
	// Mouse handler. (For "Close" button)
	@Override
	public final void actionPerformed(ActionEvent event) {
	
		this.setVisible(false);
		this.dispose();
	
	}

}