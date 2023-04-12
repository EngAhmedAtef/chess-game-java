package tests;

import static util.ChessUtilities.setupBoard;
import static util.ChessUtilities.validateAndMove;

import java.awt.Color;

import models.ChessBoard;
import models.Move;
import models.Pawn;
import models.Piece;
import models.Position;
import util.PieceFactory;

public class PawnTestDrive {

	public static void main(String[] args) {
		ChessBoard board = setupBoard();

		Piece pawn = PieceFactory.createPiece(Pawn.class, Color.white, new Position(0, 0), board);
		Piece bPawn = PieceFactory.createPiece(Pawn.class, Color.black, new Position(1, 1), board);
		
		Move move = new Move(pawn, pawn.getPosition(), new Position(1, 1), true);
		validateAndMove(move, board);
	}
}
