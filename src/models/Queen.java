package models;

import java.awt.Color;

import static util.ChessUtilities.isDiagonalClear;
import static util.ChessUtilities.isHorizontalClear;
import static util.ChessUtilities.isVerticalClear;

public class Queen extends Piece {

	// Constructors
	public Queen(Color color, Position position, ChessBoard chessBoard) {
		super(color, position, chessBoard);
	}

	// Methods
	@Override
	public MoveStatus isValidMove(Move move) {
		int rowDifference = move.endPosition().row() - move.startPosition().row();
		int columnDifference = move.endPosition().column() - move.startPosition().column();

		// Check if the Queen is moving out the bounds of the board
		if (!getChessBoard().isLegalMove(move))
			return new MoveStatus(MoveState.FAILURE, "The Queen is moving out the bounds of the board");

		Piece boardPiece = getChessBoard().getPiece(move.endPosition());

		// Check if the Queen is moving to the same location
		if (move.endPosition().equals(move.startPosition()))
			return new MoveStatus(MoveState.FAILURE, "The Queen is moving to the same initial location");

		// Check if the Queen is moving diagonally
		if (Math.abs(rowDifference) == Math.abs(columnDifference)) {
			// If the Queen is moving Diagonally and it is blocked
			if (!isDiagonalClear(getChessBoard(), move.startPosition(), move.endPosition()))
				return new MoveStatus(MoveState.FAILURE, "The Queen is moving diagonally but it is blocked.");

			// If the Queen is moving diagonally with no intermediate pieces but the desired
			// square is occupied by a piece of the same color
			if (boardPiece != null && boardPiece.getColor() == getColor())
				return new MoveStatus(MoveState.FAILURE,
						"The Queen tried to move but square is occupied by a piece of the same color");

			return new MoveStatus(MoveState.SUCCESS, null);
		}

		// Check if the Queen is moving horizontally
		if (rowDifference == 0 && columnDifference != 0) {
			// If the Queen is moving horizontally and it is blocked
			if (!isHorizontalClear(getChessBoard(), move.piece(), move.startPosition(), move.endPosition()))
				return new MoveStatus(MoveState.FAILURE, "The Queen is moving horizontally but it is blocked.");

			// If the Queen is moving horizontally with no intermediate pieces but the
			// desired
			// square is occupied by a piece of the same color
			if (boardPiece != null && boardPiece.getColor() == getColor())
				return new MoveStatus(MoveState.FAILURE,
						"The Queen tried to move but square is occupied by a piece of the same color");

			return new MoveStatus(MoveState.SUCCESS, null);
		}

		// Check if the Queen is moving vertically
		if (rowDifference != 0 && columnDifference == 0) {
			// If the Queen is moving vertically and it is blocked
			if (!isVerticalClear(getChessBoard(), move.piece(), move.endPosition(), move.startPosition()))
				return new MoveStatus(MoveState.FAILURE, "The Queen is moving vertically but it is blocked.");

			// If the Queen is moving vertically with no intermediate pieces but the desired
			// square is occupied by a piece of the same color
			if (boardPiece != null && boardPiece.getColor() == getColor())
				return new MoveStatus(MoveState.FAILURE,
						"The Queen tried to move but square is occupied by a piece of the same color");

			return new MoveStatus(MoveState.SUCCESS, null);
		}

		return new MoveStatus(MoveState.FAILURE, "The Queen is not moving diagonally, horizotnally or vertically");
	}

}
