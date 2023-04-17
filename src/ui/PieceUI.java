package ui;

import javax.swing.ImageIcon;

import models.Piece;

public class PieceUI {
	// Static variables
	private static final PieceUI pieceUI = new PieceUI(null);
	
	public static final PieceUI WHITE_Bishop = pieceUI.new WhiteBishopUI();
	public static final PieceUI WHITE_KING = pieceUI.new WhiteKingUI();
	public static final PieceUI WHITE_KNIGHT = pieceUI.new WhiteKnightUI();
	public static final PieceUI WHITE_PAWN = pieceUI.new WhitePawnUI();
	public static final PieceUI WHITE_QUEEN = pieceUI.new WhiteQueenUI();
	public static final PieceUI WHITE_ROOK = pieceUI.new WhiteRookUI();
	
	public static final PieceUI BLACK_Bishop = pieceUI.new BlackBishopUI();
	public static final PieceUI BLACK_KING = pieceUI.new BlackKingUI();
	public static final PieceUI BLACK_KNIGHT = pieceUI.new BlackKnightUI();
	public static final PieceUI BLACK_PAWN = pieceUI.new BlackPawnUI();
	public static final PieceUI BLACK_QUEEN = pieceUI.new BlackQueenUI();
	public static final PieceUI BLACK_ROOK = pieceUI.new BlackRookUI();
	
	// Instance variables
	private ImageIcon icon;
	
	// Constructors
	private PieceUI(ImageIcon icon) {
		this.icon = icon;
	}
	
	// Getters
	public ImageIcon getIcon() { return icon; }
	
	// Inner classes
	private class WhiteBishopUI extends PieceUI {
		private WhiteBishopUI() {
			super(new ImageIcon("whiteBishop.png"));
		}
	}
	
	private class WhiteKingUI extends PieceUI {
		private WhiteKingUI() {
			super(new ImageIcon("whiteKing.png"));
		}
	}
	
	private class WhiteKnightUI extends PieceUI {
		private WhiteKnightUI() {
			super(new ImageIcon("whiteKnight.png"));
		}
	}
	
	private class WhitePawnUI extends PieceUI {
		WhitePawnUI() {
			super(new ImageIcon("whitePawn.png"));
		}
	}
	
	private class WhiteQueenUI extends PieceUI {
		WhiteQueenUI() {
			super(new ImageIcon("whiteQueen.png"));
		}
	}
	
	private class WhiteRookUI extends PieceUI {
		WhiteRookUI() {
			super(new ImageIcon("whiteRook.png"));
		}
	}
	
	private class BlackBishopUI extends PieceUI {
		private BlackBishopUI() {
			super(new ImageIcon("blackBishop.png"));
		}
	}
	
	private class BlackKingUI extends PieceUI {
		private BlackKingUI() {
			super(new ImageIcon("blackKing.png"));
		}
	}
	
	private class BlackKnightUI extends PieceUI {
		private BlackKnightUI() {
			super(new ImageIcon("blackKnight.png"));
		}
	}
	
	private class BlackPawnUI extends PieceUI {
		BlackPawnUI() {
			super(new ImageIcon("blackPawn.png"));
		}
	}
	
	private class BlackQueenUI extends PieceUI {
		BlackQueenUI() {
			super(new ImageIcon("blackQueen.png"));
		}
	}
	
	private class BlackRookUI extends PieceUI {
		BlackRookUI() {
			super(new ImageIcon("blackRook.png"));
		}
	}
	
}
