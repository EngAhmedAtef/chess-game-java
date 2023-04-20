package tests;

import models.ChessBoard;
import models.Knight;
import models.Piece;
import models.Position;
import ui.PieceUI;
import util.PieceColors;
import util.PieceFactory;

public class KnightTestDrive {

	public static void main(String[] args) {
		Piece[][] pieces = new Piece[8][8];
		ChessBoard board = new ChessBoard(pieces);
		Piece wKnight = PieceFactory.createPiece(Knight.class, PieceColors.WHITE_PIECE, new Position(0, 0), board, PieceUI.WHITE_KNIGHT);
		
	}

}
