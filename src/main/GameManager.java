package main;

import models.*;
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
		Piece blackRook1 = PieceFactory.createPiece(Rook.class, PieceColors.BLACK_PIECE, new Position(0, 0), board,
				PieceUI.BLACK_ROOK);
		Piece blackRook2 = PieceFactory.createPiece(Rook.class, PieceColors.BLACK_PIECE, new Position(0, 7), board,
				PieceUI.BLACK_ROOK);
		Piece blackKnight1 = PieceFactory.createPiece(Knight.class, PieceColors.BLACK_PIECE, new Position(0, 1), board,
				PieceUI.BLACK_KNIGHT);
		Piece blackKnight2 = PieceFactory.createPiece(Knight.class, PieceColors.BLACK_PIECE, new Position(0, 6), board,
				PieceUI.BLACK_KNIGHT);
		Piece blackBishop1 = PieceFactory.createPiece(Bishop.class, PieceColors.BLACK_PIECE, new Position(0, 2), board,
				PieceUI.BLACK_BISHOP);
		Piece blackBishop2 = PieceFactory.createPiece(Bishop.class, PieceColors.BLACK_PIECE, new Position(0, 5), board,
				PieceUI.BLACK_BISHOP);
		Piece blackQueen = PieceFactory.createPiece(Queen.class, PieceColors.BLACK_PIECE, new Position(0, 3), board,
				PieceUI.BLACK_QUEEN);
		Piece blackKing = PieceFactory.createPiece(King.class, PieceColors.BLACK_PIECE, new Position(0, 4), board,
				PieceUI.BLACK_KING);
		
		// Add the black pieces to the board
		pieces[blackRook1.getPosition().row()][blackRook1.getPosition().column()] = blackRook1;
		pieces[blackRook2.getPosition().row()][blackRook2.getPosition().column()] = blackRook2;
		pieces[blackKnight1.getPosition().row()][blackKnight1.getPosition().column()] = blackKnight1;
		pieces[blackKnight2.getPosition().row()][blackKnight2.getPosition().column()] = blackKnight2;
		pieces[blackBishop1.getPosition().row()][blackBishop1.getPosition().column()] = blackBishop1;
		pieces[blackBishop2.getPosition().row()][blackBishop2.getPosition().column()] = blackBishop2;
		pieces[blackQueen.getPosition().row()][blackQueen.getPosition().column()] = blackQueen;
		pieces[blackKing.getPosition().row()][blackKing.getPosition().column()] = blackKing;
		
		// Create the black pawns
		for (int i = 0; i < 8; i++) {
			Piece pawn = PieceFactory.createPiece(Pawn.class, PieceColors.BLACK_PIECE, new Position(1, i), board, PieceUI.BLACK_PAWN);
			pieces[pawn.getPosition().row()][pawn.getPosition().column()] = pawn;
		}

		// Create the white pieces
		Piece whiteRook1 = PieceFactory.createPiece(Rook.class, PieceColors.WHITE_PIECE, new Position(7, 0), board,
				PieceUI.WHITE_ROOK);
		Piece whiteRook2 = PieceFactory.createPiece(Rook.class, PieceColors.WHITE_PIECE, new Position(7, 7), board,
				PieceUI.WHITE_ROOK);
		Piece whiteKnight1 = PieceFactory.createPiece(Knight.class, PieceColors.WHITE_PIECE, new Position(7, 1), board,
				PieceUI.WHITE_KNIGHT);
		Piece whiteKnight2 = PieceFactory.createPiece(Knight.class, PieceColors.WHITE_PIECE, new Position(7, 6), board,
				PieceUI.WHITE_KNIGHT);
		Piece whiteBishop1 = PieceFactory.createPiece(Bishop.class, PieceColors.WHITE_PIECE, new Position(7, 2), board,
				PieceUI.WHITE_BISHOP);
		Piece whiteBishop2 = PieceFactory.createPiece(Bishop.class, PieceColors.WHITE_PIECE, new Position(7, 5), board,
				PieceUI.WHITE_BISHOP);
		Piece whiteQueen = PieceFactory.createPiece(Queen.class, PieceColors.WHITE_PIECE, new Position(7, 3), board,
				PieceUI.WHITE_QUEEN);
		Piece whiteKing = PieceFactory.createPiece(King.class, PieceColors.WHITE_PIECE, new Position(7, 4), board,
				PieceUI.WHITE_KING);
		
		// Add the white pieces to the board
		pieces[whiteRook1.getPosition().row()][whiteRook1.getPosition().column()] = whiteRook1;
		pieces[whiteRook2.getPosition().row()][whiteRook2.getPosition().column()] = whiteRook2;
		pieces[whiteKnight1.getPosition().row()][whiteKnight1.getPosition().column()] = whiteKnight1;
		pieces[whiteKnight2.getPosition().row()][whiteKnight2.getPosition().column()] = whiteKnight2;
		pieces[whiteBishop1.getPosition().row()][whiteBishop1.getPosition().column()] = whiteBishop1;
		pieces[whiteBishop2.getPosition().row()][whiteBishop2.getPosition().column()] = whiteBishop2;
		pieces[whiteQueen.getPosition().row()][whiteQueen.getPosition().column()] = whiteQueen;
		pieces[whiteKing.getPosition().row()][whiteKing.getPosition().column()] = whiteKing;
		
		// Create the white pawns
		for (int i = 0; i < 8; i++) {
			Piece pawn = PieceFactory.createPiece(Pawn.class, PieceColors.WHITE_PIECE, new Position(6, i), board, PieceUI.WHITE_PAWN);
			pieces[pawn.getPosition().row()][pawn.getPosition().column()] = pawn;
		}

		// Update the possible moves for all the pieces
		for (Piece[] row : pieces) {
			for (Piece piece : row) 
				if (piece != null)
					piece.updatePossibleMoves();
		}
		
		// Return the a ChessBoard passing in the pieces array
		return board;
	}

	
	public void makeMove(Move move) {
		// For now just make the move regardless of the players
		
		// Ask the piece if it's a valid move
		MoveStatus moveStatus = move.piece().isValidMove(move);
		if (moveStatus.getMoveState() == MoveState.SUCCESS) {
			board.movePiece(move);
			frame.drawBoard();
		} else 
			System.out.println(moveStatus.getMessage());
	}
}
