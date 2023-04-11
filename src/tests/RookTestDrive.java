package tests;

import java.awt.Color;

import models.ChessBoard;
import models.Move;
import models.Piece;
import models.Piece.MoveState;
import models.Piece.MoveStatus;
import models.Position;
import models.Rook;

public class RookTestDrive {

	public static void main(String[] args) {
		ChessBoard board = setupBoard();
		Piece whiteRook = createRook(Color.white, new Position(0,0), board);
		Piece blackRook = createRook(Color.black, new Position(7, 7), board);
		
		Move move = new Move(whiteRook, whiteRook.getPosition(), new Position(7, 0), false);
		validateAndMove(move, board);
		
		move = new Move(whiteRook, whiteRook.getPosition(), new Position(6, 1), true);
		validateAndMove(move, board);
	}
	
	private static ChessBoard setupBoard() {
		Piece[][] pieces = new Piece[8][8];
		return new ChessBoard(pieces);
	}
	
	private static Piece createRook(Color color, Position position, ChessBoard board) {
		Rook rook = new Rook(color, position, board);
		board.getPieces()[position.row()][position.column()] = rook;
		return rook;
	}
	
	private static void validateAndMove(Move move, ChessBoard board) {
		MoveStatus moveStatus = move.piece().isValidMove(move);
		if (moveStatus.getMoveState() == MoveState.SUCCESS)
			board.movePiece(move);
		else 
			System.out.println(moveStatus.getMessage());
			
	}
	
}
