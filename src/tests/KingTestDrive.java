package tests;

import java.awt.Color;

import models.ChessBoard;
import models.King;
import models.Move;
import models.Position;

public class KingTestDrive {

	public static void main(String[] args) {
		ChessBoard chessBoard = new ChessBoard();
		King king = new King(Color.white, 0, 0, chessBoard);
		Move move = new Move(king, new Position(king.getRow(), king.getColumn()), new Position(-1, -1), false);
		System.out.println("Expected: False - Value: " + king.isValidMove(move));
		king.moveTo(0, 1);
		king.moveTo(0, 0);
		king.moveTo(1, 1);
		king.moveTo(2, 1);
		king.moveTo(4, 2);
		king.moveTo(-4, -2);
	}
	
}
