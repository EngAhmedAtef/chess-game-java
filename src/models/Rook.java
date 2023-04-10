package models;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/*
 * METHOD LOGIC
 * boolean isValidMove(Move move)
 * |	GET intermediate squares between the end position and start position
 * |	IF any of the intermediate squares are occupied
 * |	| RETURN false
 * |	IF the desired square is occupied by a same colored piece
 * |	| RETURN false
 * 
 * boolean getIntermediatePieces(Move move, int rowDifference, int columnDifference) 
 * |	CHECK if the Rook is moving horizontally or vertically
 * |	ITERATE over the chess board
 * |	|	GET all the pieces in the same row/column
 * |	|	IF there is at least 1 piece
 * |	|	|	RETURN true
 * */

public class Rook extends Piece {

	// Constructors
	public Rook(Color color, Position position, ChessBoard chessBoard) {
		super(color, position, chessBoard);
	}

	// Methods
	@Override
	public boolean isValidMove(Move move) {
		int rowDifference = move.endPosition().row() - move.startPosition().row();
		int columnDifference = move.endPosition().column() - move.startPosition().column();
		
		// Check if the intermediate squares are occupied
		boolean hasIntermediatePieces = hasIntermediatePieces(move, rowDifference, columnDifference);
		if (hasIntermediatePieces)
			return false;
		
		// Check if the desired square is occupied by a same colored piece
		Piece desiredSquare = getChessBoard().getPiece(move.endPosition());
		if (desiredSquare != null && desiredSquare.getColor() == getColor())
			return false;
				
		return true;
	}
	
	private boolean hasIntermediatePieces(Move move, int rowDifference, int columnDifference) {
		// Rook is moving horizontally
		if (rowDifference == 0 && columnDifference != 0) {
			Piece[] rowPieces = getChessBoard().getRowPieces(getPosition().row());
			
			// Check if the Rook is moving to the right or left and check intermediate squares accordingly
			for (int i = columnDifference > 0 ? getPosition().column() + 1 : getPosition().column() - 1; i < move.endPosition().column(); i++) {
				// If there's at least one piece then return true
				if (rowPieces[i] != null)
					return true;
			}
		}
		
		// Rook is moving vertically
		else if (rowDifference != 0 && columnDifference == 0) {
			List<Piece> columnPieces = getChessBoard().getColumnPieces(getPosition().column());
			
			// Check if the Rook is moving up or down and check intermediate squares accordingly
			for (int i = rowDifference > 0 ?  getPosition().column() + 1 : getPosition().column() - 1; i < move.endPosition().row(); i++) {
				// If there's at least one piece then return true
				if (columnPieces.get(i) != null)
					return true;
			}
		}
		
		return false;
	}

}
