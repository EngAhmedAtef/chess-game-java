package models;

import javax.swing.ImageIcon;

import util.PieceColors;


public abstract class Piece {
	// Instance variables
	private PieceColors color;
	private Position position;
	private ChessBoard chessBoard;
	private ImageIcon icon;
	
	// Constructors
	public Piece(PieceColors color, Position position, ChessBoard chessBoard, ImageIcon icon) {
		this.color = color;
		this.position = position;
		this.chessBoard = chessBoard;
		this.icon = icon;
	}
	
	// Getters
	public PieceColors getColor() { return color; }
	public Position getPosition() { return position; }
	public ChessBoard getChessBoard() { return chessBoard; }
	public ImageIcon getIcon() { return icon; }
	
	// Setters
	public void setColor(PieceColors color) { this.color = color; }
	public void setPosition(Position position) { this.position = position; }
	public void setChessBoard(ChessBoard chessBoard) { this.chessBoard = chessBoard; }
	public void setIcon(ImageIcon icon) { this.icon = icon; }
	
	// Methods
	public abstract MoveStatus isValidMove(Move move);
	
	@Override
	public String toString() {
		return (color.getColorName() + this.getClass().getSimpleName() + " - " + getPosition());
	}
}
