package models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ChessBoard implements Serializable {

	private static final long serialVersionUID = 2906380867476785569L;
	// Instance variables
	private Piece[][] pieces;

	// Constructors
	public ChessBoard(Piece[][] pieces) {
		this.pieces = pieces;
	}

	// Getters
	public Piece[][] getPieces() {
		return pieces;
	}

	// Setters
	public void setPieces(Piece[][] pieces) {
		this.pieces = pieces;
	}

	// Methods
	public void movePiece(Move move) {
		Piece piece = move.piece();
		Position startPosition = move.startPosition();
		Position endPosition = move.endPosition();

		if (move.isCapturing()) {
			Piece capturedPiece = pieces[endPosition.row()][endPosition.column()];
			System.out.println("Piece captured: " + capturedPiece);
		}

		piece.setPosition(move.endPosition());
		pieces[endPosition.row()][endPosition.column()] = piece;
		pieces[startPosition.row()][startPosition.column()] = null;

		if (piece.getClass() == Pawn.class)
			((Pawn) piece).setHasMoved(true);
		
		updatePiecesPossibleMoves();
		System.out.println(piece.getColor().getColorName() + piece.getClass().getSimpleName()
				+ " moved to " + endPosition);
	}
	
	public void updatePiecesPossibleMoves() {
		for (Piece[] row : pieces) {
			for (Piece piece : row) 
				if (piece != null)
					piece.updatePossibleMoves();
		}
	}

	public Piece getPiece(Position position) {
		return pieces[position.row()][position.column()];
	}

	public void addPiece(Piece piece, Position position) {
		pieces[position.row()][position.column()] = piece;
	}

	public void removePiece(Piece piece) {
		Position position = piece.getPosition();
		pieces[position.row()][position.column()] = null;
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
		return (move.endPosition().row() >= 0 && move.endPosition().row() < 8)
				&& (move.endPosition().column() >= 0 && move.endPosition().column() < 8);
	}

}
