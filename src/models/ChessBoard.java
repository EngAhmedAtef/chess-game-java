package models;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/*
 * INSTANCE VARIABLES
 * |	DECLARE 2-dimensional array that will hold all the peices on the board, name it "pieces"
 * 
 * METHOD DECLARATION
 * |	DECLARE a method movePiece(Move move) that will take a Move parameter and move the piece by calling the Piece's moveTo()
 * 
 * METHOD LOGIC
 * void movePiece(Move move)
 * |	IF game is not over
 * |	|	SET the piece's position to the new position
 * |	|	IF the move is capturing
 * |	|	|	REMOVE the captured piece from the board
 * 
 * Piece getPiece(Position position)
 * |	RETURN the piece at the specified position
 * 
 * Piece[] getRowPieces(int row)
 * |	RETURN the pieces at the specified row
 
 * List<Piece> getColumnPieces(int column)
 * |	RETURN the pieces at the specified column
 * */

public class ChessBoard {
	// Instance variables
	private Piece[][] pieces;
	
	// Constructors
	public ChessBoard(Piece[][] pieces) {
		this.pieces = pieces;
	}
	
	// Getters
	public Piece[][] getPieces() { return pieces; }
	
	// Setters
	public void setPieces(Piece[][] pieces) { this.pieces = pieces; }
	
	// Methods
	public void movePiece(Move move) {
		Piece piece = move.piece();
		Position startPosition = move.startPosition();
		Position endPosition = move.endPosition();

		if (move.isCapturing()) {
			Piece capturedPiece = pieces[endPosition.row()][endPosition.column()];
			System.out.println("Piece captured: " + capturedPiece);
		}
		
		pieces[endPosition.row()][endPosition.column()] = piece;
		piece.setPosition(move.endPosition());
		pieces[startPosition.row()][startPosition.column()] = null;
		
		System.out.println((piece.getColor() == Color.white ? "White " : "Black ") + piece.getClass().getSimpleName() + " moved to " + endPosition);
	}
	
	public Piece getPiece(Position position) {
		return pieces[position.row()][position.column()];
	}
	
	public Piece[] getRowPieces(int row) {
		return pieces[row];
	}
	
	public List<Piece> getColumnPieces(int column) {
		List<Piece> columnPieces = new ArrayList<>();
		
		for (Piece[] row : pieces) {
			columnPieces.add(row[column]);
		}
		
		return columnPieces;
	}
	
	public boolean inBoundMove(Move move) {
		return move.endPosition().row() < 8 && move.endPosition().column() < 8;
	}
	
}
