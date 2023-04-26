package models;

import java.util.ArrayList;
import java.util.List;

import ui.PieceUI;
import util.PieceColors;
import util.PieceFactory;

public class King extends Piece {

	private static final long serialVersionUID = 630533465927876025L;
	// Instance variables
	private boolean checked;
	private List<Piece> checkingPieces = new ArrayList<>();
	
	// Static variables
	private static Piece[][] copiedPieces;
	private static ChessBoard fakeBoard;
	private static Piece tempQueen = new Queen(null, null, null, null);
	private static boolean invalidCopiedPieces = true;

	// Constructors
	public King(PieceColors color, Position position, ChessBoard chessBoard, PieceUI pieceUI) {
		super(color, position, chessBoard, pieceUI);
	}

	// Getters
	public boolean isChecked() { return checked; }
	public List<Piece> getCheckingPieces() { return checkingPieces; }

	// Setters
	public void setChecked(boolean checked) { 
		this.checked = checked;
		if (!checked)
			checkingPieces.clear();
	}
	public void setCheckingPieces(List<Piece> checkingPieces) { this.checkingPieces = checkingPieces; }
	
	// Methods
	@Override
	public MoveStatus isValidMove(Move move) {
		int rowDifference = move.endPosition().row() - move.startPosition().row();
		int columnDifference = move.endPosition().column() - move.startPosition().column();

		// Check if the King is moving out of the board's bounds
		if (!move.piece().getChessBoard().isLegalMove(move))
			return new MoveStatus(MoveState.FAILURE, "The King is moving out of the board's bounds");

		// Check if the King is moving to the same initial position
		if (move.endPosition().equals(move.startPosition()))
			return new MoveStatus(MoveState.FAILURE, "The King is moving to the same initial position");

		// Check if the King is moving horizontally, vertically or diagonally 1 square
		if ((rowDifference == 0 && Math.abs(columnDifference) == 1)
				|| (Math.abs(rowDifference) == 1 && columnDifference == 0)
				|| (Math.abs(rowDifference) == 1 && Math.abs(columnDifference) == 1)) {
			
			// Check if the desired square is occupied by a peace of the same color
			Piece boardPiece = move.piece().getChessBoard().getPiece(move.endPosition());
			if (boardPiece != null && boardPiece.getColor() == getColor())
				return new MoveStatus(MoveState.FAILURE, "The King is trying to move but the square is occupied by a piece of the same color");
			
			// Check if the move is resulting in the king being in check
			if (isResultingInCheck(move)) {
				return new MoveStatus(MoveState.FAILURE, "The King is trying to move but the move will result in a check");
			}
			
			return new MoveStatus(MoveState.SUCCESS, null);
		}
		
		return new MoveStatus(MoveState.FAILURE, "The king can only move 1 square in any direction");
	}
	
	private boolean isResultingInCheck(Move move) {
		// Create a list that will hold the different colored pieces
		List<Piece> diffColoredPieces = new ArrayList<>();
		
		Piece[][] copyBoard = getBoardCopy(diffColoredPieces);
		
		// Get the copy of the King
		Piece copyKing = copyBoard[getPosition().row()][getPosition().column()];
		
		// Remove it from the copied board
		fakeBoard.removePiece(copyKing);
		
		// Remove the piece at the end position of the move
		Piece endPositionPiece = fakeBoard.getPiece(move.endPosition());
		if (endPositionPiece != null)
			fakeBoard.removePiece(endPositionPiece);
		
		// Create a Queen at the end position of the move
		tempQueen.setColor(copyKing.getColor());
		tempQueen.setPosition(move.endPosition());
		tempQueen.setChessBoard(fakeBoard);
		fakeBoard.addPiece(tempQueen, tempQueen.getPosition());
		
		// Check the different colored pieces to see if they can move to the end position
		for (Piece diffColoredPiece : diffColoredPieces) {
			Move fakeMove = new Move(diffColoredPiece, diffColoredPiece.getPosition(), move.endPosition(), false);
			if (diffColoredPiece.isValidMove(fakeMove).getMoveState() == MoveState.SUCCESS) {
				invalidCopiedPieces = true;
				return true;
			}
		}
		
		invalidCopiedPieces = true;
		return false;
	}
	
	private Piece[][] getBoardCopy(List<Piece> diffColoredPieces) {
		if (invalidCopiedPieces) {
			// Get the array of the board
			Piece[][] pieces = getChessBoard().getPieces();
			
			// Create a copy of the board
			// Check if copiedPieces is null
			// IF YES: Create a new array
			if (copiedPieces == null)
				copiedPieces = new Piece[8][8];
			
			// Check if fakeBoard is null
			// IF YES: make a new board
			if (fakeBoard == null)
				fakeBoard = new ChessBoard(copiedPieces);
			
			// Copy the pieces on the board
			// And add the different colored pieces to the list
			for (int row = 0; row < 8; row++) {
				for (int col = 0; col < 8; col++) {
					// Get the original piece
					Piece originalPiece = pieces[row][col];
					// Nullify the copied board
					copiedPieces[row][col] = null;
					// If there is an original piece then make a copy and add it to the copied array
					if (originalPiece != null) {
						Piece copiedPiece = PieceFactory.createPiece(originalPiece.getClass(), originalPiece.getColor(), originalPiece.getPosition(), fakeBoard, originalPiece.getPieceUI());
						copiedPieces[row][col] = copiedPiece;
						// If the color of the piece is different than the King
						// Add it to the copied array
						if (originalPiece.getColor() != getColor()) {
							diffColoredPieces.add(copiedPiece);
						}
					}
				}
			}
			
			invalidCopiedPieces = false;
		}
		
		
		return copiedPieces;
	}

}
