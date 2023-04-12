package util;

import java.awt.Color;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import models.ChessBoard;
import models.Piece;
import models.Position;

public class PieceFactory {

	public static Piece createPiece(Class<? extends Piece> clazz, Color color, Position position, ChessBoard board) {
		try {
			Constructor<? extends Piece> constructor = clazz.getConstructor(Color.class, Position.class,
					ChessBoard.class);
			Piece piece = constructor.newInstance(color, position, board);
			board.addPiece(piece, position);
			return piece;
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
		return null;
	}

}
