package models;

import java.awt.Color;


public abstract class Piece {
	// Instance variables
	private Color color;
	private Position position;
	private ChessBoard chessBoard;
	
	// Constructors
	public Piece(Color color, Position position, ChessBoard chessBoard) {
		this.color = color;
		this.position = position;
		this.chessBoard = chessBoard;
	}
	
	// Getters
	public Color getColor() { return color; }
	public Position getPosition() { return position; }
	public ChessBoard getChessBoard() { return chessBoard; }
	
	// Setters
	public void setColor(Color color) { this.color = color; }
	public void setPosition(Position position) { this.position = position; }
	public void setChessBoard(ChessBoard chessBoard) { this.chessBoard = chessBoard; }
	
	// Methods
	public abstract MoveStatus isValidMove(Move move);
	
	@Override
	public String toString() {
		return (color == Color.white ? "White " : "Black ") + this.getClass().getSimpleName() + " - " + getPosition();
	}
	
	
	// Inner class
	public class MoveStatus {
		private MoveState moveState;
		private String message;
		
		MoveStatus(MoveState moveState, String message) {
			this.moveState = moveState;
			this.message = message;
		}
		
		public MoveState getMoveState() { return moveState; }
		public String getMessage() { return message; }
		
		public void setMoveState(MoveState moveState) { this.moveState = moveState; }
		public void setMessage(String message) { this.message = message; }
	}
	
	public enum MoveState {
		SUCCESS, FAILURE
	}
}
