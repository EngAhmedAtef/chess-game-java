package tests;

import java.awt.Color;

import models.ChessBoard;
import models.Move;
import models.Piece;
import models.Piece.MoveState;
import models.Piece.MoveStatus;
import models.Position;
import models.Queen;

public class QueenTestDrive {

	public static void main(String[] args) {
		ChessBoard board = setupBoard();
		Piece queen = createQueen(Color.white, new Position(0, 0), board);
		
		Piece blackQueen = createQueen(Color.black, new Position(1, 1), board);
		
		Move move = new Move(queen, queen.getPosition(), new Position(1, 1), false);
		validateAndMove(move, board);
	}
	
	private static ChessBoard setupBoard() {
		Piece[][] pieces = new Piece[8][8];
		return new ChessBoard(pieces);
	}
	
	private static Piece createQueen(Color color, Position position, ChessBoard board) {
		Queen queen = new Queen(color, position, board);
		board.getPieces()[position.row()][position.column()] = queen;
		return queen;
	}
	
	private static void validateAndMove(Move move, ChessBoard board) {
		MoveStatus moveStatus = move.piece().isValidMove(move);
		if (moveStatus.getMoveState() == MoveState.SUCCESS)
			board.movePiece(move);
		else 
			System.out.println(moveStatus.getMessage());
			
	}
	
}
