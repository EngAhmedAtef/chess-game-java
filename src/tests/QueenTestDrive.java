package tests;

import static util.ChessUtilities.setupBoard;
import static util.ChessUtilities.validateAndMove;

import java.awt.Color;

import models.ChessBoard;
import models.Move;
import models.Piece;
import models.Position;
import models.Queen;
import util.PieceFactory;

public class QueenTestDrive {

	public static void main(String[] args) {
		ChessBoard board = setupBoard();

		Piece queen = PieceFactory.createPiece(Queen.class, Color.white, new Position(0, 0), board);

		Move move = new Move(queen, queen.getPosition(), new Position(0, 0), false);
		validateAndMove(move, board);
	}
}
