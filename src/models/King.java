package models;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class King extends Piece {

	// Instance variables
	private boolean isChecked;

	// Constructors
	public King(Color color, Position position, ChessBoard chessBoard) {
		super(color, position, chessBoard);
	}

	// Getters
	public boolean isChecked() {
		return isChecked;
	}

	// Setters
	public void setIsChecked(boolean isChecked) {
		this.isChecked = isChecked;
	}

	// Methods
	@Override
	public MoveStatus isValidMove(Move move) {
		int rowDifference = move.endPosition().row() - move.startPosition().row();
		int columnDifference = move.endPosition().column() - move.startPosition().column();

		// Check if the King is moving out of the board's bounds
		if (!getChessBoard().isLegalMove(move))
			return new MoveStatus(MoveState.FAILURE, "The King is moving out of the board's bounds");

		// Check if the King is moving to the same initial position
		if (move.endPosition().equals(move.startPosition()))
			return new MoveStatus(MoveState.FAILURE, "The King is moving to the same initial position");

		// Check if the King is moving horizontally, vertically or diagonally 1 square
		if ((rowDifference == 0 && Math.abs(columnDifference) == 1)
				|| (Math.abs(rowDifference) == 1 && columnDifference == 0)
				|| (Math.abs(rowDifference) == 1 && Math.abs(columnDifference) == 1)) {
			
			// Check if the desired square is occupied by a peace of the same color
			Piece boardPiece = getChessBoard().getPiece(move.endPosition());
			if (boardPiece != null && boardPiece.getColor() == getColor())
				return new MoveStatus(MoveState.FAILURE, "The King is trying to move but the square is occupied by a piece of the same color");
			
			// Check if the move is resulting in the king being in check
			if (isResultingInCheck(move))
				return new MoveStatus(MoveState.FAILURE, "The King is trying to move but the move will result in a check");
			
			return new MoveStatus(MoveState.SUCCESS, null);
		}
		
		return new MoveStatus(MoveState.FAILURE, "The king can only move 1 square in any direction");
	}

	private boolean isResultingInCheck(Move move) {
		List<Piece> diffColoredPieces = new ArrayList<>();

		for (Piece[] row : getChessBoard().getPieces()) {
			for (Piece piece : row) {
				if (piece != null && piece.getColor() != getColor())
					diffColoredPieces.add(piece);
			}
		}
		
		System.out.println(diffColoredPieces.size());

		boolean checkPiece = diffColoredPieces.stream()
				.anyMatch(piece -> piece.isValidMove(new Move(piece, piece.getPosition(), move.endPosition(), true))
						.getMoveState() == MoveState.SUCCESS);

		System.out.println(checkPiece);
		
		if (checkPiece)
			return true;

		return false;
	}

}
