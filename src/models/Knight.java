package models;

import ui.PieceUI;
import util.PieceColors;

public class Knight extends Piece {

	// Constructors
	public Knight(PieceColors color, Position position, ChessBoard chessBoard, PieceUI pieceUI) {
		super(color, position, chessBoard, pieceUI);
	}

	// Methods
	@Override
	public MoveStatus isValidMove(Move move) {
		int deltaRow = Math.abs(move.endPosition().row() - move.startPosition().row());
		int deltaColumn = Math.abs(move.endPosition().column() - move.startPosition().column());

		// Check if the Knight moves out of the board's bounds
		if (!getChessBoard().isLegalMove(move))
			return new MoveStatus(MoveState.FAILURE, "The Knight is moving out of the board's bounds");

		// Check if the Knight is moving to the same initial Position
		if (move.endPosition().equals(move.startPosition()))
			return new MoveStatus(MoveState.FAILURE, "The Knight is moving to the same initial position");

		// Check if the Knight is moving in a L shape
		if ((deltaRow == 1 && deltaColumn == 2) || (deltaRow == 2 && deltaColumn == 1)) {
			// Check if the square is occupied by a piece of the same color
			Piece boardPiece = getChessBoard().getPiece(move.endPosition());
			if (boardPiece != null && boardPiece.getColor() == getColor())
				return new MoveStatus(MoveState.FAILURE, "The Knight is trying to move but the square is occupied");
			
			return new MoveStatus(MoveState.SUCCESS, null);
		}

		return new MoveStatus(MoveState.FAILURE, "The Knight can only move in an L shape; 1 square horinzontally and 2 vertical or vice versa");
	}

}
