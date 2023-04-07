package models;

import java.awt.Color;

public abstract class Piece {
	private Color color;
	private int row;
	private int column;
	private ChessBoard chessBoard;
	
	public Piece(Color color, int row, int column, ChessBoard chessBoard) {
		this.color = color;
		this.row = row;
		this.column = column;
		this.chessBoard = chessBoard;
	}
	
	public Color getColor() {
		return color;
	}
	
	public int getRow() {
		return row;
	}
	
	public int getColumn() {
		return column;
	}
	
	public ChessBoard getChessBoard() {
		return chessBoard;
	}
	
	public void setColor(Color color) {
		this.color = color;
	}
	
	public void setRow(int row) {
		this.row = row;
	}
	
	public void setColumn(int column) {
		this.column = column;
	}
	
	public void setChessBoard(ChessBoard chessBoard) {
		this.chessBoard = chessBoard;
	}
	
	public abstract void moveTo(int toRow, int toColumn);
	
	public abstract boolean isValidMove(Move move);
}
