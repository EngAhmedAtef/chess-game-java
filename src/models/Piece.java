package models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import ui.PieceUI;
import util.PieceColors;


public abstract class Piece implements Serializable {

	private static final long serialVersionUID = -2031046168990546537L;
	// Instance variables
	private PieceColors color;
	private Position position;
	private ChessBoard chessBoard;
	private PieceUI pieceUI;
	private List<Position> possibleMoves = new ArrayList<>();
	
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
	public List<Position> getPossibleMoves() { return possibleMoves; }
	
	// Setters
	public void setColor(PieceColors color) { this.color = color; }
	public void setPosition(Position position) { this.position = position; }
	public void setChessBoard(ChessBoard chessBoard) { this.chessBoard = chessBoard; }
	public void setPieceUI(PieceUI pieceUI) { this.pieceUI = pieceUI; }
	public void setPossibleMoves(List<Position> possibleMoves) { this.possibleMoves = possibleMoves; }
	
	// Methods
	public abstract MoveStatus isValidMove(Move move);
	
	// Called by the ChessBoard's updatePiecesPossibleMoves()
	// Also called when the GameManager calls the setupBoard() method through the ChessBoard.
	public List<Position> updatePossibleMoves() {
		Piece[][] pieces = chessBoard.getPieces();
		possibleMoves.clear();
		
		for (int row = 0; row < pieces.length; row++) {
			for (int col = 0; col < pieces[row].length; col++) {
				Piece boardPiece = pieces[row][col];
				if (boardPiece != null && boardPiece.getColor() == getColor())
					continue;
				
				Position currentSquare = new Position(row, col);
				Move move = new Move(this, getPosition(), currentSquare, (boardPiece != null && boardPiece.getColor() != getColor()));
				MoveStatus moveStatus = isValidMove(move);
				if (moveStatus.getMoveState() == MoveState.SUCCESS)
					possibleMoves.add(currentSquare);
			}
		}
		
		return possibleMoves;
	}
	
	@Override
	public String toString() {
		return (color.getColorName() + this.getClass().getSimpleName() + " - " + getPosition());
	}
}
