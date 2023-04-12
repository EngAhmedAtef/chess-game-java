package models;

import static util.ChessUtilities.isVerticalClear;

import java.awt.Color;

public class Pawn extends Piece {
	// Instance variables
	private boolean hasMoved;

	// Constructors
	public Pawn(Color color, Position position, ChessBoard chessBoard) {
		super(color, position, chessBoard);
	}

	// Getters
	public boolean hasMoved() {
		return hasMoved;
	}

	// Setters
	public void setHasMoved(boolean hasMoved) {
		this.hasMoved = hasMoved;
	}

	// Methods
	@Override
	public MoveStatus isValidMove(Move move) {
		int rowDifference = move.endPosition().row() - move.startPosition().row();
		int columnDifference = move.endPosition().column() - move.startPosition().column();

		// Check if the Pawn is moving out of the board's bounds
		if (!getChessBoard().isLegalMove(move))
			return new MoveStatus(MoveState.FAILURE, "The Pawn is moving out of the board's bounds");

		// Check if the pawn is moving to the same position
		if (move.endPosition().equals(move.startPosition()))
			return new MoveStatus(MoveState.FAILURE, "The Pawn is moving to the same initial position");

		// Check if the pawn is moving 1 square
		if (((getColor() == Color.white && rowDifference == 1) || (getColor() == Color.black && rowDifference == -1))
				&& columnDifference == 0) {
			// Check if the square is occupied
			Piece boardPiece = getChessBoard().getPiece(move.endPosition());
			if (boardPiece != null)
				return new MoveStatus(MoveState.FAILURE, "The Pawn is moving but the square is occupied");

			hasMoved = true;
			return new MoveStatus(MoveState.SUCCESS, null);
		}

		// Check if the pawn is moving 2 squares
		if (((getColor() == Color.white && rowDifference == 2) || (getColor() == Color.black && rowDifference == -2))
				&& columnDifference == 0) {
			// Check if the pawn never moved before
			if (hasMoved())
				return new MoveStatus(MoveState.FAILURE, "The Pawn can move 2 squares only as its first move");

			// Check if something is blocking the pawn
			if (!isVerticalClear(getChessBoard(), this, getPosition(), move.endPosition()))
				return new MoveStatus(MoveState.FAILURE,
						"The Pawn is moving 2 squares but something is blocking its path");

			// Check if the desired square is occupied
			Piece boardPiece = getChessBoard().getPiece(move.endPosition());
			if (boardPiece != null)
				return new MoveStatus(MoveState.FAILURE, "The Pawn is moving 2 squares but the square is occupied");

			hasMoved = true;
			return new MoveStatus(MoveState.SUCCESS, null);
		}

		// Check if the pawn is moving diagonally
		if ((getColor() == Color.white && (rowDifference == 1 && Math.abs(columnDifference) == 1))
				|| (getColor() == Color.black && (rowDifference == -1 && Math.abs(columnDifference) == 1))) {
			// Check if there is a piece to capture
			Piece boardPiece = getChessBoard().getPiece(move.endPosition());
			if (boardPiece != null && boardPiece.getColor() != getColor())
				return new MoveStatus(MoveState.SUCCESS, null);
			
			return new MoveStatus(MoveState.FAILURE, "The Pawn is trying to capture but there isn't a piece to capture");
		}

		return new MoveStatus(MoveState.FAILURE,
				"The Pawn can only move vertically 1 square or 2 if it's its first move");
	}
}
