package tests;

import static util.ChessUtilities.setupBoard;
import static util.ChessUtilities.validateAndMove;

import java.awt.Color;

import models.ChessBoard;
import models.Move;
import models.Piece;
import models.Position;
import models.Rook;
import util.PieceFactory;;

public class RookTestDrive {

	public static void main(String[] args) {
		ChessBoard board = setupBoard();

		Piece whiteRook = PieceFactory.createPiece(Rook.class, Color.white, new Position(0, 0), board);
		Piece blackRook = PieceFactory.createPiece(Rook.class, Color.black, new Position(7, 7), board);

		Move move = new Move(whiteRook, whiteRook.getPosition(), new Position(7, 0), false);
		validateAndMove(move, board);

		move = new Move(whiteRook, whiteRook.getPosition(), new Position(6, 1), true);
		validateAndMove(move, board);
	}
}
