package tests;

import static util.ChessUtilities.setupBoard;
import static util.ChessUtilities.validateAndMove;

import java.awt.Color;

import models.ChessBoard;
import models.King;
import models.Move;
import models.Pawn;
import models.Piece;
import models.Position;
import util.PieceFactory;

public class KingTestDrive {

	public static void main(String[] args) {
		ChessBoard board = setupBoard();

		Piece wKing = PieceFactory.createPiece(King.class, Color.white, new Position(0, 0), board);
		Piece bPawn = PieceFactory.createPiece(Pawn.class, Color.black, new Position(1, 2), board);

		Move move = new Move(wKing, wKing.getPosition(), new Position(0, 1), false);
		validateAndMove(move, board);
	}
}
