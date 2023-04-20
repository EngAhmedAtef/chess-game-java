package ui;

import java.io.Serializable;

import javax.swing.ImageIcon;

public class PieceUI implements Serializable {
	private static final long serialVersionUID = -2921084014363819673L;

	// Static variables
	private static final PieceUI pieceUI = new PieceUI(null);
	
	public static final PieceUI WHITE_BISHOP = pieceUI.new WhiteBishopUI();
	public static final PieceUI WHITE_KING = pieceUI.new WhiteKingUI();
	public static final PieceUI WHITE_KNIGHT = pieceUI.new WhiteKnightUI();
	public static final PieceUI WHITE_PAWN = pieceUI.new WhitePawnUI();
	public static final PieceUI WHITE_QUEEN = pieceUI.new WhiteQueenUI();
	public static final PieceUI WHITE_ROOK = pieceUI.new WhiteRookUI();
	
	public static final PieceUI BLACK_BISHOP = pieceUI.new BlackBishopUI();
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
		/**
		 * 
		 */
		private static final long serialVersionUID = -3013970133521623685L;

		private WhiteBishopUI() {
			super(new ImageIcon("whiteBishop.png"));
		}
	}
	
	private class WhiteKingUI extends PieceUI {
		/**
		 * 
		 */
		private static final long serialVersionUID = 4224540715617977391L;

		private WhiteKingUI() {
			super(new ImageIcon("whiteKing.png"));
		}
	}
	
	private class WhiteKnightUI extends PieceUI {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1993725667159101188L;

		private WhiteKnightUI() {
			super(new ImageIcon("whiteKnight.png"));
		}
	}
	
	private class WhitePawnUI extends PieceUI {
		/**
		 * 
		 */
		private static final long serialVersionUID = 8028208055347171873L;

		WhitePawnUI() {
			super(new ImageIcon("whitePawn.png"));
		}
	}
	
	private class WhiteQueenUI extends PieceUI {
		/**
		 * 
		 */
		private static final long serialVersionUID = -7062918712726415890L;

		WhiteQueenUI() {
			super(new ImageIcon("whiteQueen.png"));
		}
	}
	
	private class WhiteRookUI extends PieceUI {
		/**
		 * 
		 */
		private static final long serialVersionUID = -7287707522929206726L;

		WhiteRookUI() {
			super(new ImageIcon("whiteRook.png"));
		}
	}
	
	private class BlackBishopUI extends PieceUI {
		/**
		 * 
		 */
		private static final long serialVersionUID = 7417617709091470967L;

		private BlackBishopUI() {
			super(new ImageIcon("blackBishop.png"));
		}
	}
	
	private class BlackKingUI extends PieceUI {
		/**
		 * 
		 */
		private static final long serialVersionUID = -8684068056387733488L;

		private BlackKingUI() {
			super(new ImageIcon("blackKing.png"));
		}
	}
	
	private class BlackKnightUI extends PieceUI {
		/**
		 * 
		 */
		private static final long serialVersionUID = -1269498324947959853L;

		private BlackKnightUI() {
			super(new ImageIcon("blackKnight.png"));
		}
	}
	
	private class BlackPawnUI extends PieceUI {
		/**
		 * 
		 */
		private static final long serialVersionUID = -8255430445807302144L;

		BlackPawnUI() {
			super(new ImageIcon("blackPawn.png"));
		}
	}
	
	private class BlackQueenUI extends PieceUI {
		/**
		 * 
		 */
		private static final long serialVersionUID = -2905911261934665486L;

		BlackQueenUI() {
			super(new ImageIcon("blackQueen.png"));
		}
	}
	
	private class BlackRookUI extends PieceUI {
		/**
		 * 
		 */
		private static final long serialVersionUID = 4472494038180193680L;

		BlackRookUI() {
			super(new ImageIcon("blackRook.png"));
		}
	}
	
}
