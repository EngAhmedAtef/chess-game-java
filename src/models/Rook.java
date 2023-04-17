package models;

import static util.ChessUtilities.isHorizontalClear;
import static util.ChessUtilities.isVerticalClear;

import ui.PieceUI;
import util.PieceColors;

public class Rook extends Piece {

	// Constructors
	public Rook(PieceColors color, Position position, ChessBoard chessBoard, PieceUI pieceUI) {
		super(color, position, chessBoard, pieceUI);
	}

	// Methods
	@Override
	public MoveStatus isValidMove(Move move) {
		int rowDifference = move.endPosition().row() - move.startPosition().row();
		int columnDifference = move.endPosition().column() - move.startPosition().column();

		// Check if the Rook is moving out of the board's bounds
		if (!getChessBoard().isLegalMove(move))
			return new MoveStatus(MoveState.FAILURE, "The Rook is moving out of the board's bounds");
		
		// Check if the Rook is moving to the same initial position
		if (move.endPosition().equals(move.startPosition()))
			return new MoveStatus(MoveState.FAILURE, "The Rook is moving to the same initial position");
		
		// Check if the Rook is moving horizontally
		if (rowDifference == 0 && columnDifference != 0) {
			// Check if there is intermediate pieces
			if (!isHorizontalClear(getChessBoard(), this, move.startPosition(), move.endPosition()))
				return new MoveStatus(MoveState.FAILURE, "The Rook is moving horizontally but the path is blocked");

			// Check if the desired square is occupied by a piece of the same color
			Piece boardPiece = getChessBoard().getPiece(move.endPosition());
			if (boardPiece != null && boardPiece.getColor() == getColor())
				return new MoveStatus(MoveState.FAILURE,
						"The Rook is trying to move but the square is occupied by a piece of the same color");
			
			return new MoveStatus(MoveState.SUCCESS, null);
		}
		// Check if the Rook is moving vertically
		if (rowDifference != 0 && columnDifference == 0) {
			// Check if there is intermediate pieces
			if (!isVerticalClear(getChessBoard(), this, move.startPosition(), move.endPosition()))
				return new MoveStatus(MoveState.FAILURE, "The Rook is moving vertically but the path is blocked");
			
			// Check if the desired square is occupied by a piece of the same color
			Piece boardPiece = getChessBoard().getPiece(move.endPosition());
			if (boardPiece != null && boardPiece.getColor() == getColor())
				return new MoveStatus(MoveState.FAILURE,
						"The Rook is trying to move but the square is occupied by a piece of the same color");
			return new MoveStatus(MoveState.SUCCESS, null);
		}

		return new MoveStatus(MoveState.FAILURE, "The Rook only moves horizontally or vertically");
	}
}
