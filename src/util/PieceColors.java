package util;

import java.io.Serializable;

public class PieceColors implements Serializable {

	private static final long serialVersionUID = -2842281218861897261L;
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

		private static final long serialVersionUID = -3473351743942469319L;

		private WhitePiece() {
			super("White");
		}
	}
	
	private class BlackPiece extends PieceColors {

		private static final long serialVersionUID = -8263260107549338764L;

		private BlackPiece() {
			super("Black");
		}
	}
}
