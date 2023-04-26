package models;

import java.awt.Color;

public class BoardSquareColors {

	// Static variables
	private static final BoardSquareColors boardColors = new BoardSquareColors(null, null);
	public static final BoardSquareColors WHITE_SQUARE = boardColors.new WhiteSquare();
	public static final BoardSquareColors VIOLET_SQUARE = boardColors.new VioletSquare();
	public static final BoardSquareColors ORANGE_SQUARE = boardColors.new OrangeSquare();
	
	// Instance variables
	private String colorName;
	private Color color;
	
	// Constructors
	private BoardSquareColors(String colorName, Color color) {
		this.colorName = colorName;
		this.color = color;
	}
	
	// Getters
	public String getColorName() { return colorName; }
	public Color getColor() { return color; }
	
	// Setters
	public void setColorName(String colorName) { this.colorName = colorName; }
	public void setColor(Color color) { this.color = color; }
	
	// Inner classes
	private class WhiteSquare extends BoardSquareColors {
		private WhiteSquare() {
			super("White", Color.white);
		}
	}
	
	private class VioletSquare extends BoardSquareColors {
		private VioletSquare() {
			super("Violet", new Color(0x917FB3));
		}
	}
	
	private class OrangeSquare extends BoardSquareColors {
		private OrangeSquare() {
			super("Orange", new Color(0xFA8E21));
		}
	}
}
