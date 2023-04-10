package models;

import java.awt.Color;

/*
 * boolean isValidMove(Move move)
 * |	CHECK if the Queen is moving horizontally or vertically
 * |	|	IF there is any intermediate pieces or the desired square is occupied by a piece of the same color
 * |	|	|	RETURN false
 * |	CHECK if the Queen is moving diagonally
 * |	|	IF there isn't any intermediate pieces and the desired square is not occupied by a piece of the same color
 * |	|	|	RETURN true
 * |	| RETURN false
 * */

public class Queen extends Piece {

	// Constructors
	public Queen(Color color, Position position, ChessBoard chessBoard) {
		super(color, position, chessBoard);
	}

	// Methods
	@Override
	public boolean isValidMove(Move move) {
		int rowDifference = move.endPosition().row() - move.startPosition().row();
		int columnDifference = move.endPosition().column() - move.startPosition().column();

		// Check if the Queen is moving horizontally or vertically
		if ((rowDifference == 0 && columnDifference != 0) || (rowDifference != 0 && columnDifference == 0)) {
			int min = Math.min(move.startPosition().row(), move.endPosition().row());
			int max = Math.max(move.startPosition().row(), move.endPosition().row());
			// Check if there's any intermediate pieces vertically
			for (int i = min + 1; i < max; i++)
				if (getChessBoard().getPiece(new Position(getPosition().row(), i)) != null)
					return false;
			
			min = Math.min(move.startPosition().column(), move.endPosition().column());
			max = Math.max(move.startPosition().column(), move.endPosition().column());
			// Check if there's any intermediate pieces horizontally
			for (int i = min + 1; i < max; i++)
				if (getChessBoard().getPiece(new Position(i, getPosition().column())) != null)
					return false;
			// Check if the desired square is occupied by a piece of the same color
			if (getChessBoard().getPiece(move.endPosition()) != null && getChessBoard().getPiece(move.endPosition()).getColor() == getColor())
				return false;
		}

		return true;
	}

}
