/*
 *	TEAM DETAILS:
 *	====================================================
 *
 *	-> NAME:				GEORGILAKIS ANTONIOS
 *	   SEMESTER:			2
 *	   REGISTRATION NUMBER:	3130037
 *
 *	-> NAME:				PAPATHEODOROU DIMITRIOS
 *	   SEMESTER:			2
 *	   REGISTRATION NUMBER:	3130162
 *
 *	-> NAME:				PETROCHEILOS ALKIVIADIS
 *	   SEMESTER:			2
 *	   REGISTRATION NUMBER:	3130171
 *
 *	====================================================
 *
 */

package com.computerelectronics;

import java.awt.Image;
import java.util.ArrayList;
import javax.swing.UIManager;

import com.computerelectronics.io.utils.ImageReader;
import com.computerelectronics.ui.MainWindow;

public final class mainApp {

	/** Application Main Entry Point */
	public static final void main(String[] arguments) {
	
		// Set a "Nimbus" UI look and feel, only if it has been installed.
		try {
		
			for (UIManager.LookAndFeelInfo interfaceStyle : UIManager.getInstalledLookAndFeels()) {
			
				if (interfaceStyle.getName().equalsIgnoreCase("Nimbus")) {
				
					UIManager.setLookAndFeel(interfaceStyle.getClassName());
					break;
				
				}
			
			}
		
		} catch (Exception error) {}
		
		// Prepare the application's icon set.
		ArrayList<Image> mainWindowIcon = new ArrayList<Image>();
		mainWindowIcon.add(ImageReader.getImage("MainWindowIconLarge.png"));
		mainWindowIcon.add(ImageReader.getImage("MainWindowIconMedium.png"));
		mainWindowIcon.add(ImageReader.getImage("MainWindowIconSmall.png"));
		
		// Show the main window.
		new MainWindow(MainWindow.APPLICATION_TITLE, mainWindowIcon, MainWindow.WINDOW_WIDTH, MainWindow.WINDOW_HEIGHT);
	
	}

}