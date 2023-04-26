package models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import util.PieceColors;

public class ChessBoard implements Serializable {

	private static final long serialVersionUID = 2906380867476785569L;
	// Instance variables
	private Piece[][] pieces;
	private Piece whiteKing;
	private Piece blackKing;

	// Constructors
	public ChessBoard(Piece[][] pieces) {
		this.pieces = pieces;
	}

	// Getters
	public Piece[][] getPieces() { return pieces; }
	public Piece getWhiteKing() { return whiteKing; }
	public Piece getBlackKing() { return blackKing; }

	// Setters
	public void setPieces(Piece[][] pieces) { this.pieces = pieces; }
	public void setWhiteKing(Piece whiteKing) { this.whiteKing = whiteKing; }
	public void setBlackKing(Piece blackKing) { this.blackKing = blackKing; }
	
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

		if (piece.getClass() == Pawn.class && !((Pawn) piece).hasMoved())
			((Pawn) piece).setHasMoved(true);
		
		((King) blackKing).setChecked(false);
		((King) whiteKing).setChecked(false);
		
		checkForChecks(); 
		
		updatePiecesPossibleMoves();
		System.out.println(piece.getColor().getColorName() + " " + piece.getClass().getSimpleName()
				+ " moved to " + endPosition);
	}
	
	public void checkForChecks() {
		List<Piece> whiteColorPieces = new ArrayList<>();
		List<Piece> blackColorPieces = new ArrayList<>();
		
		for (Piece[] row : pieces) 
			for (Piece rowPiece : row) {
				if (rowPiece != null && rowPiece.getColor() == PieceColors.WHITE_PIECE)
					whiteColorPieces.add(rowPiece);
				else if (rowPiece != null && rowPiece.getColor() == PieceColors.BLACK_PIECE)
					blackColorPieces.add(rowPiece);
			}
		
		for (Piece whitePiece : whiteColorPieces) {
			Move attackMove = new Move(whitePiece, whitePiece.getPosition(), blackKing.getPosition(), true);
			if (whitePiece.isValidMove(attackMove).getMoveState() == MoveState.SUCCESS) {
				((King) blackKing).setChecked(true);
				((King) blackKing).getCheckingPieces().add(whitePiece);
				System.out.println("White " + whitePiece.getClass().getSimpleName() + " is checking the Black King");
			}
		}
		

		for (Piece blackPiece : blackColorPieces) {
			Move attackMove = new Move(blackPiece, blackPiece.getPosition(), whiteKing.getPosition(), true);
			if (blackPiece.isValidMove(attackMove).getMoveState() == MoveState.SUCCESS) {
				((King) whiteKing).setChecked(true);
				((King) whiteKing).getCheckingPieces().add(blackPiece);
				System.out.println("Black " + blackPiece.getClass().getSimpleName() + " is checking the White King");
			}
		}
	}
	
	public boolean isBlackKingSafe() {
		List<Piece> whitePieces = new ArrayList<>();
		
		for (Piece[] row : pieces) 
			for (Piece piece : row)
				if (piece != null && piece.getColor() == PieceColors.WHITE_PIECE)
					whitePieces.add(piece);
		
		for (Piece piece : whitePieces) {
			Move move = new Move(piece, piece.getPosition(), blackKing.getPosition(), true);
			if (piece.isValidMove(move).getMoveState() == MoveState.SUCCESS)
				return false;
		}
		
		return true;
	}
	
	public boolean isWhiteKingSafe() {
		List<Piece> blackPieces = new ArrayList<>();
		
		for (Piece[] row : pieces) 
			for (Piece piece : row)
				if (piece != null && piece.getColor() == PieceColors.BLACK_PIECE)
					blackPieces.add(piece);
		
		for (Piece piece : blackPieces) {
			Move move = new Move(piece, piece.getPosition(), whiteKing.getPosition(), true);
			if (piece.isValidMove(move).getMoveState() == MoveState.SUCCESS)
				return false;
		}
		
		return true;
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
	
	public List<Position> getPath(Position fromPosition, Position toPosition) {
		// Check if both squares are on the same row
		// Check if both squares are on the same column
		// Check if both squares are on the same diagonal
		// Otherwise it's a Knight move
		
		int rowDiff = toPosition.row() - fromPosition.row();
		int colDiff = toPosition.column() - fromPosition.column();
		List<Position> path = new ArrayList<>();
		
		// Both squares are on the same row
		if (rowDiff == 0 && colDiff != 0) {
			int min = Math.min(fromPosition.column(), toPosition.column());
			int max = Math.max(fromPosition.column(), toPosition.column());
			
			for (int i = min; i < max; i++)
				path.add(pieces[fromPosition.row()][i].getPosition());
		} // Both squares are on the same column
		else if (colDiff == 0 && rowDiff != 0) {
			int min = Math.min(fromPosition.row(), toPosition.row());
			int max = Math.max(fromPosition.row(), toPosition.row());
			
			for (int i = min; i < max; i++)
				path.add(pieces[fromPosition.column()][i].getPosition());
		} // Both squares are on the same diagonal
		else if (Math.abs(colDiff) == Math.abs(rowDiff)) {
			int xDirection = Integer.signum(rowDiff);
			int yDirection = Integer.signum(colDiff);

			int currentRow = fromPosition.row() + xDirection;
			int currentColumn = fromPosition.column() + yDirection;

			while (currentRow != toPosition.row() && currentColumn != toPosition.column()) {
				path.add(new Position(currentRow, currentColumn));
				currentRow += xDirection;
				currentColumn += yDirection;
			}
		} 
		// Else: It's a Knight so we can simply return the empty path
		
		return path;
	}

}
