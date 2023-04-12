package models;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

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
	
	public void addPiece(Piece piece, Position position) {
		pieces[position.row()][position.column()] = piece;
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
	
	public boolean isLegalMove(Move move) {
		return move.endPosition().row() < 8 && move.endPosition().column() < 8;
	}
	
}
