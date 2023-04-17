package util;

import java.awt.Color;

public class PieceColors {
	// Static variables
	private final static PieceColors pieceColors = new PieceColors(null, null);
	public final static PieceColors WHITE_PIECE = pieceColors.new WhitePiece();
	public final static PieceColors BLACK_PIECE = pieceColors.new BlackPiece();
	
	// Instance variables
	private Color color;
	private String colorName;
	
	// Constructors
	private PieceColors(Color color, String colorName) {
		this.color = color;
		this.colorName = colorName;
	}
	
	// Getters
	public Color getColor() { return color; }
	public String getColorName() { return colorName; }
	
	// Inner classes
	private class WhitePiece extends PieceColors {
		private WhitePiece() {
			super(Color.white, "White");
		}
	}
	
	private class BlackPiece extends PieceColors {
		private BlackPiece() {
			super(Color.black, "Black");
		}
	}
}
