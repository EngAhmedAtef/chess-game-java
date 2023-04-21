package models;

import static util.ChessUtilities.isDiagonalClear;
import static util.ChessUtilities.isHorizontalClear;
import static util.ChessUtilities.isVerticalClear;

import ui.PieceUI;
import util.PieceColors;

public class Queen extends Piece {

	private static final long serialVersionUID = -3966723971788954675L;

	// Constructors
	public Queen(PieceColors color, Position position, ChessBoard chessBoard, PieceUI pieceUI) {
		super(color, position, chessBoard, pieceUI);
	}

	// Methods
	@Override
	public MoveStatus isValidMove(Move move) {
		int rowDifference = move.endPosition().row() - move.startPosition().row();
		int columnDifference = move.endPosition().column() - move.startPosition().column();

		// Check if the Queen is moving out the bounds of the board
		if (!move.piece().getChessBoard().isLegalMove(move))
			return new MoveStatus(MoveState.FAILURE, "The Queen is moving out the bounds of the board");

		// Check if the Queen is moving to the same location
		if (move.endPosition().equals(move.startPosition()))
			return new MoveStatus(MoveState.FAILURE, "The Queen is moving to the same initial location");

		Piece boardPiece = move.piece().getChessBoard().getPiece(move.endPosition());
		// Check if the Queen is moving diagonally
		if (Math.abs(rowDifference) == Math.abs(columnDifference)) {
			// If the Queen is moving Diagonally and it is blocked
			if (!isDiagonalClear(move.piece().getChessBoard(), move.startPosition(), move.endPosition()))
				return new MoveStatus(MoveState.FAILURE, "The Queen is moving diagonally but it is blocked.");

			// If the Queen is moving diagonally with no intermediate pieces but the desired
			// square is occupied by a piece of the same color
			if (boardPiece != null && boardPiece.getColor() == getColor())
				return new MoveStatus(MoveState.FAILURE,
						"The Queen tried to move but square is occupied by a piece of the same color");

			return new MoveStatus(MoveState.SUCCESS, null);
		}

		// Check if the Queen is moving horizontally
		if (rowDifference == 0 && columnDifference != 0) {
			// If the Queen is moving horizontally and it is blocked
			if (!isHorizontalClear(move.piece().getChessBoard(), move.piece(), move.startPosition(), move.endPosition()))
				return new MoveStatus(MoveState.FAILURE, "The Queen is moving horizontally but it is blocked.");

			// If the Queen is moving horizontally with no intermediate pieces but the
			// desired
			// square is occupied by a piece of the same color
			if (boardPiece != null && boardPiece.getColor() == getColor())
				return new MoveStatus(MoveState.FAILURE,
						"The Queen tried to move but square is occupied by a piece of the same color");

			return new MoveStatus(MoveState.SUCCESS, null);
		}

		// Check if the Queen is moving vertically
		if (rowDifference != 0 && columnDifference == 0) {
			// If the Queen is moving vertically and it is blocked
			if (!isVerticalClear(move.piece().getChessBoard(), move.piece(), move.endPosition(), move.startPosition()))
				return new MoveStatus(MoveState.FAILURE, "The Queen is moving vertically but it is blocked.");

			// If the Queen is moving vertically with no intermediate pieces but the desired
			// square is occupied by a piece of the same color
			if (boardPiece != null && boardPiece.getColor() == getColor())
				return new MoveStatus(MoveState.FAILURE,
						"The Queen tried to move but square is occupied by a piece of the same color");

			return new MoveStatus(MoveState.SUCCESS, null);
		}

		return new MoveStatus(MoveState.FAILURE, "The Queen is not moving diagonally, horizotnally or vertically");
	}

}
