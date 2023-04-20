package models;

import ui.ChessGameFrame;
import ui.PieceUI;
import util.PieceColors;
import util.PieceFactory;

public class GameManager {
	// Static variable
	public static final GameManager gameManager = new GameManager();

	// Instance Variables
	private ChessBoard board;
	private ChessGameFrame frame;

	// Constructors
	private GameManager() {
		board = setupBoard();
		frame = new ChessGameFrame(board);
	}

	// Getters
	public ChessBoard getBoard() { return board; }

	// Methods
	private ChessBoard setupBoard() {
		// Create the pieces array
		Piece[][] pieces = new Piece[8][8];
		ChessBoard board = new ChessBoard(pieces);

		// Create the black pieces
		PieceFactory.createPiece(Rook.class, PieceColors.BLACK_PIECE, new Position(0, 0), board, PieceUI.BLACK_ROOK);
		PieceFactory.createPiece(Rook.class, PieceColors.BLACK_PIECE, new Position(0, 7), board, PieceUI.BLACK_ROOK);
		PieceFactory.createPiece(Knight.class, PieceColors.BLACK_PIECE, new Position(0, 1), board, PieceUI.BLACK_KNIGHT);
		PieceFactory.createPiece(Knight.class, PieceColors.BLACK_PIECE, new Position(0, 6), board, PieceUI.BLACK_KNIGHT);
		PieceFactory.createPiece(Bishop.class, PieceColors.BLACK_PIECE, new Position(0, 2), board, PieceUI.BLACK_BISHOP);
		PieceFactory.createPiece(Bishop.class, PieceColors.BLACK_PIECE, new Position(0, 5), board, PieceUI.BLACK_BISHOP);
		PieceFactory.createPiece(Queen.class, PieceColors.BLACK_PIECE, new Position(0, 3), board, PieceUI.BLACK_QUEEN);
		PieceFactory.createPiece(King.class, PieceColors.BLACK_PIECE, new Position(0, 4), board, PieceUI.BLACK_KING);

		// Create the black pawns
		for (int i = 0; i < 8; i++) {
			Piece pawn = PieceFactory.createPiece(Pawn.class, PieceColors.BLACK_PIECE, new Position(1, i), board,
					PieceUI.BLACK_PAWN);
			pieces[pawn.getPosition().row()][pawn.getPosition().column()] = pawn;
		}

		// Create the white pieces
		PieceFactory.createPiece(Rook.class, PieceColors.WHITE_PIECE, new Position(7, 0), board, PieceUI.WHITE_ROOK);
		PieceFactory.createPiece(Rook.class, PieceColors.WHITE_PIECE, new Position(7, 7), board, PieceUI.WHITE_ROOK);
		PieceFactory.createPiece(Knight.class, PieceColors.WHITE_PIECE, new Position(7, 1), board, PieceUI.WHITE_KNIGHT);
		PieceFactory.createPiece(Knight.class, PieceColors.WHITE_PIECE, new Position(7, 6), board, PieceUI.WHITE_KNIGHT);
		PieceFactory.createPiece(Bishop.class, PieceColors.WHITE_PIECE, new Position(7, 2), board, PieceUI.WHITE_BISHOP);
		PieceFactory.createPiece(Bishop.class, PieceColors.WHITE_PIECE, new Position(7, 5), board, PieceUI.WHITE_BISHOP);
		PieceFactory.createPiece(Queen.class, PieceColors.WHITE_PIECE, new Position(7, 3), board, PieceUI.WHITE_QUEEN);
		PieceFactory.createPiece(King.class, PieceColors.WHITE_PIECE, new Position(7, 4), board, PieceUI.WHITE_KING);

		// Create the white pawns
		for (int i = 0; i < 8; i++) {
			Piece pawn = PieceFactory.createPiece(Pawn.class, PieceColors.WHITE_PIECE, new Position(6, i), board,
					PieceUI.WHITE_PAWN);
			pieces[pawn.getPosition().row()][pawn.getPosition().column()] = pawn;
		}

		// Update the possible moves for all the pieces
		board.updatePiecesPossibleMoves();

		// Return the a ChessBoard passing in the pieces array
		return board;
	}

	public void makeMove(Move move) {
		// For now just make the move regardless of the players
		// TODO: Make moves based on players
		// Ask the piece if it's a valid move
		MoveStatus moveStatus = move.piece().isValidMove(move);
		if (moveStatus.getMoveState() == MoveState.SUCCESS) {
			board.movePiece(move);
			frame.drawBoard();
		} else
			System.out.println(moveStatus.getMessage());
	}
}
