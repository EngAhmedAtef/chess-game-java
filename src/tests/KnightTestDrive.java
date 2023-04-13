package tests;

import static util.ChessUtilities.setupBoard;
import static util.ChessUtilities.validateAndMove;

import java.awt.Color;

import models.ChessBoard;
import models.Knight;
import models.Move;
import models.Pawn;
import models.Piece;
import models.Position;
import util.PieceFactory;

public class KnightTestDrive {

	public static void main(String[] args) {
		ChessBoard board = setupBoard();
		Piece wKnight = PieceFactory.createPiece(Knight.class, Color.white, new Position(0, 0), board);
		Piece bPawn = PieceFactory.createPiece(Pawn.class, Color.black, new Position(1, 2), board);
		Piece bPawn2 = PieceFactory.createPiece(Pawn.class, Color.black, new Position(2, 1), board);
		Piece wPawn = PieceFactory.createPiece(Pawn.class, Color.white, new Position(1, 2), board);

		// Capturing a piece
//		Move move = new Move(wKnight, wKnight.getPosition(), new Position(1, 2), true);
//		validateAndMove(move, board);
		
		// Capturing a piece
//		Move move = new Move(wKnight, wKnight.getPosition(), new Position(2, 1), true);
//		validateAndMove(move, board);
		
		// Illegal move
//		Move move = new Move(wKnight, wKnight.getPosition(), new Position (1, 0), false);
//		validateAndMove(move, board);
		
		// Square is occupied
//		Move move = new Move(wKnight, wKnight.getPosition(), new Position(1, 2), false);
//		validateAndMove(move, board);
		
		// Out of the board
		Move move = new Move(wKnight, wKnight.getPosition(), new Position(-1, 2), false);
		validateAndMove(move, board);
	}

}
