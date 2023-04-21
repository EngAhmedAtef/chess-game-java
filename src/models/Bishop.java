package models;

import static util.ChessUtilities.isDiagonalClear;

import ui.PieceUI;
import util.PieceColors;

public class Bishop extends Piece {

	private static final long serialVersionUID = -2876109646936742626L;

	// Constructors
	public Bishop(PieceColors color, Position position, ChessBoard chessBoard, PieceUI pieceUI) {
		super(color, position, chessBoard, pieceUI);
	}

	// Methods
	@Override
	public MoveStatus isValidMove(Move move) {
		int rowDifference = move.endPosition().row() - move.startPosition().row();
		int columnDifference = move.endPosition().column() - move.startPosition().column();

		// Check if the Bishop is going out of the board's bounds
		if (!move.piece().getChessBoard().isLegalMove(move))
			return new MoveStatus(MoveState.FAILURE, "The Bishop is moving out of the board's bounds");

		// Check if the Bishop is moving to the same initial position
		if (move.endPosition().equals(move.startPosition()))
			return new MoveStatus(MoveState.FAILURE, "The Bishop is moving to the same initial position");

		// Check if the Bishop is moving diagonally
		if (Math.abs(rowDifference) == Math.abs(columnDifference)) {
			// Check if the diagonal path is blocked
			if (!isDiagonalClear(move.piece().getChessBoard(), move.startPosition(), move.endPosition()))
				return new MoveStatus(MoveState.FAILURE, "The Bishop tried to move but there is an intermediate peice");

			// Check if the desired location is occupied by a piece of the same color
			Piece boardPiece = move.piece().getChessBoard().getPiece(move.endPosition());
			if (boardPiece != null && boardPiece.getColor() == getColor())
				return new MoveStatus(MoveState.FAILURE,
						"The Bishop tried to move but the square is occupied by a piece of the same color");

			return new MoveStatus(MoveState.SUCCESS, null);
		}

		return new MoveStatus(MoveState.FAILURE, "The Bishop can only move diagonally");
	}

}
