package models;

import java.awt.Color;

public class Bishop extends Piece {

	// Constructors
	public Bishop(Color color, Position position, ChessBoard chessBoard) {
		super(color, position, chessBoard);
	}

	// Methods
	@Override
	public boolean isValidMove(Move move) {
		int rowDifference = move.endPosition().row() - move.startPosition().row();
		int columnDifference = move.endPosition().column() - move.startPosition().column();
		
		// Check if the bishop is not moving diagonally
		if (Math.abs(rowDifference) != Math.abs(columnDifference))
			return false;
		
		// Check if the bishop is moving diagonally
		// but there is intermediate pieces
		boolean hasIntermediatePieces = hasIntermediatePieces(move, rowDifference, columnDifference);
		if (hasIntermediatePieces)
			return false;
		// The Bishop is moving diagonally without any intermediate pieces
		// But the desired square is occupied by a piece of the same color
		else if(getChessBoard().getPiece(move.endPosition()) != null && getChessBoard().getPiece(move.endPosition()).getColor() == getColor())
			return false;
		
		return true;
	}
	
	private boolean hasIntermediatePieces(Move move, int rowDifference, int columnDifference) {
		int xDirection = Integer.signum(rowDifference);
		int yDirection = Integer.signum(columnDifference);
		
		int currentRow = move.startPosition().row() + xDirection;
		int currentColumn = move.startPosition().column() + yDirection;
		
		while (currentRow != move.endPosition().row() && currentColumn != move.endPosition().column()) {
			
			if (getChessBoard().getPiece(new Position(currentRow, currentColumn)) != null)
				return false;
			
			currentRow += xDirection;
			currentColumn += yDirection;
		}
		
		return true;
	}

}
