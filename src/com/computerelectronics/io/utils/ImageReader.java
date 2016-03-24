package com.computerelectronics.io.utils;
import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;

// Class representing an image reader for the application.
public final class ImageReader {

	// Standard image error message.
	public static final String IMAGE_ERROR = "Image not available.";
	
	public static final Image getImage(String fileName) {
	
		// Read the image file from the "Images" folder.
		File imageFile = new File(String.format("Images\\%s", fileName));
		
		if ((imageFile.exists() && (imageFile.isFile()) && (!(imageFile.isHidden())))) {
		
			try {
			
				Image image = (Image) (ImageIO.read(imageFile));
				return image;
			
			} catch (Exception error) {
			
				return null;
			
			}
		
		}
		
		return null;
	
	}

}