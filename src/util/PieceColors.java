package util;

public class PieceColors {
	// Static variables
	private final static PieceColors pieceColors = new PieceColors(null);
	public final static PieceColors WHITE_PIECE = pieceColors.new WhitePiece();
	public final static PieceColors BLACK_PIECE = pieceColors.new BlackPiece();
	
	// Instance variables
	private String colorName;
	
	// Constructors
	private PieceColors(String colorName) {
		this.colorName = colorName;
	}
	
	// Getters
	public String getColorName() { return colorName; }
	
	// Inner classes
	private class WhitePiece extends PieceColors {
		private WhitePiece() {
			super("White");
		}
	}
	
	private class BlackPiece extends PieceColors {
		private BlackPiece() {
			super("Black");
		}
	}
}
