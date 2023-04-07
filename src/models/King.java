package models;

import java.awt.Color;

/*
 * METHOD LOGIC
 * void moveTo(int toRow, int toColumn)
 * |	CALL isValidMove(Move move) to check if the piece can move to the desired position
 * |	IF the move is valid
 * |	|	SET the row and column of the piece to the new position
 * |	|	IF the move is capturing, remove the captured piece from the ChessBoard
 * 
 * boolean isValidMove(Move move)
 * |	CHECK if the row and column are within the bounds of the ChessBoard
 * |	IF the desired position is occupied by a piece of the same color
 * |	|	RETURN false 
 * |	CALCULATE the difference in the rows and columns
 * |	IF the difference of each is <= 1
 * |	|	RETURN true
 * |	ELSE
 * |	|	RETURN false	
 * */


public class King extends Piece {

	public King(Color color, int row, int column, ChessBoard chessBoard) {
		super(color, row, column, chessBoard);
	}

	@Override
	public void moveTo(int toRow, int toColumn) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isValidMove(Move move) {
		// TODO Auto-generated method stub
		return false;
	}

}
