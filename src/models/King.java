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
	public boolean isChecked() { return isChecked; }
	
	// Setters
	public void setIsChecked(boolean isChecked) { this.isChecked = isChecked; }
	
	// Methods
	@Override
	public boolean isValidMove(Move move) {
		int rowDifference = move.endPosition().row() - move.startPosition().row();
		int columnDifference = move.endPosition().column() - move.startPosition().column();
		
		// Check if the King moves more than 1 square
		if (Math.abs(rowDifference) != 1 && Math.abs(columnDifference) != 1)
			return false;
		
		// Check if the King moves 1 square and the desired square is occupied by a piece of the same color
		if (Math.abs(rowDifference) == 1 && Math.abs(columnDifference) == 1 && getChessBoard().getPiece(move.endPosition()) != null && getChessBoard().getPiece(move.endPosition()).getColor() == getColor())
			return false;

		// Check if the King moves 1 square but it will result in him being checked
		if (Math.abs(rowDifference) == 1 && Math.abs(columnDifference) == 1 && isResultingInCheck(move))
			return false;
		
		// Else, the King is not blocked, not moving more than 1 square, the movement won't expose him to a check. So he can move
		return true;
	}
	
	private boolean isResultingInCheck(Move move) {
		List<Piece> diffColoredPieces = new ArrayList<>();
		
		for (Piece[] row : getChessBoard().getPieces()) {
			for (Piece piece : row) {
				if (piece.getColor() != getColor())
					diffColoredPieces.add(piece);
			}
		}

		boolean checkPiece = diffColoredPieces.stream()
				.anyMatch(piece -> piece.isValidMove(new Move(piece, piece.getPosition(), getPosition(), true)));
		
		if (checkPiece)
			return true;
		
		return false;
	}
	
	

}
