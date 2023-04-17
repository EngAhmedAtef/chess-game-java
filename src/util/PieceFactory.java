package util;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import javax.swing.ImageIcon;

import models.ChessBoard;
import models.Piece;
import models.Position;
import ui.PieceUI;

public class PieceFactory {

	public static Piece createPiece(Class<? extends Piece> clazz, PieceColors color, Position position, ChessBoard board, PieceUI icon) {
		try {
			Constructor<? extends Piece> constructor = clazz.getConstructor(PieceColors.class, Position.class,
					ChessBoard.class, PieceUI.class);
			Piece piece = constructor.newInstance(color, position, board, icon);
			board.addPiece(piece, position);
			return piece;
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
		return null;
	}

}
