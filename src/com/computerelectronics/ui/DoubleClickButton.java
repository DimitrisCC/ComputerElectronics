package com.computerelectronics.ui;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.SwingConstants;

// Class representing a graphic button for double-click purposes.
public final class DoubleClickButton extends JButton {

	private static final long serialVersionUID = 2595817138850678444L;
	
	private Color borderColor;
	
	/** Constructor */
	public DoubleClickButton(int width, int height, String text, int fontStyle, int fontSize, Color borderColor, MouseListener mouseListener) {
	
		super(text);
		this.setName(text);     // Required by mouse listeners.
		this.setPreferredSize(new Dimension(width, height));
		this.setFont(new Font("SansSerif", fontStyle, fontSize));
		this.setHorizontalAlignment(SwingConstants.CENTER);
		this.borderColor = borderColor;
		
		if (mouseListener != null) this.addMouseListener(mouseListener);
	
	}
	
	// Helper method for creating the button's border. (with anti-aliasing on)
	@Override
	protected final void paintComponent(Graphics componentGraphics) {
	
		Graphics2D paintGraphics = (Graphics2D) (componentGraphics);
		super.paintComponent(componentGraphics);
		paintGraphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		paintGraphics.setColor(this.borderColor);
		paintGraphics.setStroke(new BasicStroke(3, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
		paintGraphics.drawRoundRect(3, 3, ((int) (this.getPreferredSize().getWidth())) - 6, ((int) (this.getPreferredSize().getHeight())) - 6, 2, 2);
		paintGraphics.dispose();
	
	}

}