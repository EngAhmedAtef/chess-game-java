package models;

import java.awt.Color;
import static util.ChessUtilities.isDiagonalClear;

public class Bishop extends Piece {

	// Constructors
	public Bishop(Color color, Position position, ChessBoard chessBoard) {
		super(color, position, chessBoard);
	}

	// Methods
	@Override
	public MoveStatus isValidMove(Move move) {
		int rowDifference = move.endPosition().row() - move.startPosition().row();
		int columnDifference = move.endPosition().column() - move.startPosition().column();

		// Check if the Bishop is going out of the board's bounds
		if (!getChessBoard().isLegalMove(move))
			return new MoveStatus(MoveState.FAILURE, "The Bishop is moving out of the board's bounds");

		// Check if the Bishop is moving to the same initial position
		if (move.endPosition().equals(move.startPosition()))
			return new MoveStatus(MoveState.FAILURE, "The Bishop is moving to the same initial position");

		// Check if the Bishop is moving diagonally
		if (Math.abs(rowDifference) == Math.abs(columnDifference)) {
			// Check if the diagonal path is blocked
			if (!isDiagonalClear(getChessBoard(), move.startPosition(), move.endPosition()))
				return new MoveStatus(MoveState.FAILURE, "The Bishop tried to move but there is an intermediate peice");

			// Check if the desired location is occupied by a piece of the same color
			Piece boardPiece = getChessBoard().getPiece(move.endPosition());
			if (boardPiece != null && boardPiece.getColor() == getColor())
				return new MoveStatus(MoveState.FAILURE,
						"The Bishop tried to move but the square is occupied by a piece of the same color");

			return new MoveStatus(MoveState.SUCCESS, null);
		}

		return new MoveStatus(MoveState.FAILURE, "The Bishop can only move diagonally");
	}

}
