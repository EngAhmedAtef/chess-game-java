package models;

import ui.PieceUI;
import util.PieceColors;


public abstract class Piece {
	// Instance variables
	private PieceColors color;
	private Position position;
	private ChessBoard chessBoard;
	private PieceUI pieceUI;
	
	// Constructors
	public Piece(PieceColors color, Position position, ChessBoard chessBoard, PieceUI pieceUI) {
		this.color = color;
		this.position = position;
		this.chessBoard = chessBoard;
		this.pieceUI = pieceUI;
	}
	
	// Getters
	public PieceColors getColor() { return color; }
	public Position getPosition() { return position; }
	public ChessBoard getChessBoard() { return chessBoard; }
	public PieceUI getPieceUI() { return pieceUI; }
	
	// Setters
	public void setColor(PieceColors color) { this.color = color; }
	public void setPosition(Position position) { this.position = position; }
	public void setChessBoard(ChessBoard chessBoard) { this.chessBoard = chessBoard; }
	public void setPieceUI(PieceUI pieceUI) { this.pieceUI = pieceUI; }
	
	// Methods
	public abstract MoveStatus isValidMove(Move move);
	
	@Override
	public String toString() {
		return (color.getColorName() + this.getClass().getSimpleName() + " - " + getPosition());
	}
}
