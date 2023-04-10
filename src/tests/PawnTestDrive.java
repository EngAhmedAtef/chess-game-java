package tests;

import java.awt.Color;

import models.ChessBoard;
import models.Move;
import models.Pawn;
import models.Piece;
import models.Piece.MoveState;
import models.Piece.MoveStatus;
import models.Position;

public class PawnTestDrive {

	public static void main(String[] args) {
		Piece[][] pieces = new Piece[8][8];
		ChessBoard board = new ChessBoard(pieces);
		Piece pawn = new Pawn(Color.white, new Position(0, 0), board);
		pieces[0][0] = pawn;
		
		Piece blackPawn = new Pawn(Color.black, new Position(1, 0), board);
		pieces[1][0] = blackPawn;
		
		Move move = new Move(pawn, pawn.getPosition(), new Position(1, 0), true);
		validateAndMove(move, board);
	}
	
	private static void validateAndMove(Move move, ChessBoard board) {
		MoveStatus moveStatus = move.piece().isValidMove(move);
		if (moveStatus.getMoveState() == MoveState.SUCCESS)
			board.movePiece(move);
		else
			System.out.println(moveStatus.getMessage());
	}
	
}
