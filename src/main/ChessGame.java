package main;

import java.util.List;

import models.ChessBoard;
import models.Move;
import models.Piece;
import models.Position;

public class ChessGame {

	public static void main(String[] args) {
		GameManager gm = GameManager.gameManager;
		ChessBoard board = gm.getBoard();
		Piece pawn = board.getPiece(new Position(1, 0));
		List<Position> pawnMoves = pawn.getPossibleMoves();
		System.out.println(pawnMoves);
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Move move = new Move(pawn, pawn.getPosition(), new Position(2, 0), false);
		gm.makeMove(move);
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(pawnMoves);
		move = new Move(pawn, pawn.getPosition(), new Position(3, 0), false);
		gm.makeMove(move);
	}
	
}
