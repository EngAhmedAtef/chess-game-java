package util;

import models.ChessBoard;
import models.Move;
import models.MoveState;
import models.MoveStatus;
import models.Piece;
import models.Position;

public abstract class ChessUtilities {

	public static boolean isDiagonalClear(ChessBoard board, Position startPosition, Position endPosition) {
		int rowDifference = endPosition.row() - startPosition.row();
		int columnDifference = endPosition.column() - startPosition.column();

		int xDirection = Integer.signum(rowDifference);
		int yDirection = Integer.signum(columnDifference);

		int currentRow = startPosition.row() + xDirection;
		int currentColumn = startPosition.column() + yDirection;

		while (currentRow != endPosition.row() && currentColumn != endPosition.column()) {

			if (board.getPiece(new Position(currentRow, currentColumn)) != null)
				return false;

			currentRow += xDirection;
			currentColumn += yDirection;
		}
		return true;
	}

	public static boolean isHorizontalClear(ChessBoard board, Piece piece, Position startPosition,
			Position endPosition) {
		int min = Math.min(startPosition.column(), endPosition.column());
		int max = Math.max(startPosition.column(), endPosition.column());
		// Check if there's any intermediate pieces horizontally
		for (int i = min + 1; i < max; i++)
			if (board.getPiece(new Position(piece.getPosition().row(), i)) != null)
				return false;
		return true;
	}

	public static boolean isVerticalClear(ChessBoard board, Piece piece, Position startPosition, Position endPosition) {
		int min = Math.min(startPosition.row(), endPosition.row());
		int max = Math.max(startPosition.row(), endPosition.row());
		// Check if there's any intermediate pieces vertically
		for (int i = min + 1; i < max; i++)
			if (board.getPiece(new Position(i, piece.getPosition().column())) != null)
				return false;
		return true;
	}
	
	public static void validateAndMove(Move move, ChessBoard board) {
		MoveStatus moveStatus = move.piece().isValidMove(move);
		if (moveStatus.getMoveState() == MoveState.SUCCESS)
			board.movePiece(move);
		else 
			System.out.println(moveStatus.getMessage());
			
	}
	
	public static ChessBoard setupBoard() {
		Piece[][] pieces = new Piece[8][8];
		return new ChessBoard(pieces);
	}

}
