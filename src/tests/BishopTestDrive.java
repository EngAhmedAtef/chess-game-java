package tests;

import static util.ChessUtilities.setupBoard;
import static util.ChessUtilities.validateAndMove;

import java.awt.Color;

import models.Bishop;
import models.ChessBoard;
import models.Move;
import models.Piece;
import models.Position;
import util.PieceFactory;;

public class BishopTestDrive {

	public static void main(String[] args) {
		ChessBoard board = setupBoard();
		Piece whiteBishop = PieceFactory.createPiece(Bishop.class, Color.white, new Position(0, 0), board);
		Piece blackBishop = PieceFactory.createPiece(Bishop.class, Color.black, new Position(7, 7), board);

		Move move = new Move(whiteBishop, whiteBishop.getPosition(), new Position(3, 3), false);
		validateAndMove(move, board);
		move = new Move(whiteBishop, whiteBishop.getPosition(), new Position(9, 3), false);
		validateAndMove(move, board);
	}
}
