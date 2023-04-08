package models;

import java.awt.Color;

public abstract class Piece {
	private Color color;
	private Position position;
	private ChessBoard chessBoard;
	
	public Piece(Color color, Position position, ChessBoard chessBoard) {
		this.color = color;
		this.position = position;
		this.chessBoard = chessBoard;
	}
	
	public Color getColor() { return color; }
	public Position getPosition() { return position ; }
	public ChessBoard getChessBoard() { return chessBoard; }
	
	public void setColor(Color color) { this.color = color; }
	public void setPosition(Position position) { this.position = position; }
	public void setChessBoard(ChessBoard chessBoard) { this.chessBoard = chessBoard; }
	
	public abstract void movePiece(Move move);
	
	public abstract boolean isValidMove(Move move);
}
