package models;

import java.awt.Color;

public class Pawn extends Piece {
	// Instance variables
	private boolean hasMoved;

	// Constructors
	public Pawn(Color color, Position position, ChessBoard chessBoard) {
		super(color, position, chessBoard);
	}

	// Getters
	public boolean hasMoved() { return hasMoved; }
	
	// Setters
	public void setHasMoved(boolean hasMoved) { this.hasMoved = hasMoved; }
	
	// Methods
	@Override
	public MoveStatus isValidMove(Move move) {
		int rowDifference = move.endPosition().row() - move.startPosition().row();
		int columnDifference = move.endPosition().column() - move.startPosition().column();
		
		// Check if the pawn is going out of the board
		if (!getChessBoard().isLegalMove(move))
			return new MoveStatus(MoveState.FAILURE, "New position is out of bounds of the board");
		
		// Check if the pawn is moving to the same position
		if (move.endPosition().equals(move.startPosition()))
			return new MoveStatus(MoveState.FAILURE, "Pawn is trying to move to its initial location");
		
		// Check if the pawn is moving laterally
		if (rowDifference == 0 && columnDifference != 0)
			return new MoveStatus(MoveState.FAILURE, "Pawn is trying to move laterally");
		
		// Check if the pawn is trying to move more than 2 squares
		if (Math.abs(rowDifference) > 2)
			return new MoveStatus(MoveState.FAILURE, "Pawn is trying to move more than 2 squares");
		
		// Check if the pawn moves 1 square and the square is occupied by a piece
		if (columnDifference == 0 && ((rowDifference == 1 && getColor() == Color.white) || (rowDifference == -1 && getColor() == Color.black)) && getChessBoard().getPiece(move.endPosition()) != null)
			return new MoveStatus(MoveState.FAILURE, "Moving 1 square but it is occupied");
		
		// Check if the pawn is moving forward 2 squares when it already has moved
		if (columnDifference == 0 && ((rowDifference == 2 && getColor() == Color.white) || (rowDifference == -2 && getColor() == Color.black)) && hasMoved)
			return new MoveStatus(MoveState.FAILURE, "Moving 2 squares when it already has moved");
		
		// Check if the pawn is moving forward 2 squares as its first move and
		// 1. The square right in front of it is occupied OR
		// 2. The desired square is occupied
		if (
				// The pawn is moving 2 squares as its first move
				columnDifference == 0 && !hasMoved && ( (rowDifference == 2 && getColor() == Color.white) || (rowDifference == -2 && getColor() == Color.black) ) && 
				// The desired square is occupied
				( (getChessBoard().getPiece(move.endPosition()) != null) || 
				// The square in front of the pawn is occupied
				( (getChessBoard().
						getPiece(getColor() == Color.white ? new Position(move.endPosition().row() - 1, move.endPosition().column()) : new Position(move.endPosition().row() + 1, move.endPosition().column())) != null)))
			)
				return new MoveStatus(MoveState.FAILURE, "Moving 2 squares as a first move but the desired square is occupied or the square in front of the pawn is occupied");
		
		// Check if the pawn is capturing diagonally
		if ( Math.abs(columnDifference) == 1 && ( (getColor() == Color.white && rowDifference == 1) || (getColor() == Color.black && rowDifference == -1) ) ) {
			boolean capture = getChessBoard().getPiece(move.endPosition()) != null && getChessBoard().getPiece(move.endPosition()).getColor() != getColor();
			if (capture)
				return new MoveStatus(MoveState.SUCCESS, null);
			else
				return new MoveStatus(MoveState.FAILURE, "Pawn is tyring to capture diagonally but there isn't a piece to capture or it might be of the same color");
		}
		
		// Else, the pawn is neither capturing nor blocked, thus he can move
		hasMoved = true;
		return new MoveStatus(MoveState.SUCCESS, null);
	}
}
