package tests;

import java.awt.Color;

import models.Bishop;
import models.ChessBoard;
import models.Move;
import models.Piece;
import models.Piece.MoveState;
import models.Piece.MoveStatus;
import models.Position;

public class BishopTestDrive {

	public static void main(String[] args) {
		ChessBoard board = setupBoard();
		Piece whiteBishop = createBishop(Color.white, new Position(0,0), board);
		Piece blackBishop = createBishop(Color.black, new Position(7, 7), board);
		
		Move move = new Move(whiteBishop, whiteBishop.getPosition(), new Position(3, 3), false);
		validateAndMove(move, board);
		move = new Move(whiteBishop, whiteBishop.getPosition(), new Position(9, 3), false);
		validateAndMove(move, board);
	}
	
	private static ChessBoard setupBoard() {
		Piece[][] pieces = new Piece[8][8];
		return new ChessBoard(pieces);
	}
	
	private static Piece createBishop(Color color, Position position, ChessBoard board) {
		Bishop bishop = new Bishop(color, position, board);
		board.getPieces()[position.row()][position.column()] = bishop;
		return bishop;
	}
	
	private static void validateAndMove(Move move, ChessBoard board) {
		MoveStatus moveStatus = move.piece().isValidMove(move);
		if (moveStatus.getMoveState() == MoveState.SUCCESS)
			board.movePiece(move);
		else 
			System.out.println(moveStatus.getMessage());
			
	}
	
}
