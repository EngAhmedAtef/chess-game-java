package models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import ui.PieceUI;
import util.PieceColors;
import util.PieceFactory;


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
	// Also called when the GameManager calls the setupBoard() method.
	// We have to check whether or not the King of the same color is checked or not
	// If he's checked then we have to check if the move is going to result in the King 
	// Not being in a check anymore
	// If YES we add that move to the possibleMoves
	// If the King is not in check then we normally add all the possible moves
	public List<Position> updatePossibleMoves() {
		Piece[][] pieces = chessBoard.getPieces();
		possibleMoves.clear();
		
		Piece sameColoredKing = getColor() == PieceColors.WHITE_PIECE ? chessBoard.getWhiteKing() : chessBoard.getBlackKing();

		// TODO: Improve performance
		Piece[][] copiedPieces = new Piece[8][8];
		ChessBoard fakeBoard = new ChessBoard(copiedPieces);

		for (int row = 0; row < 8; row++)
			for (int col = 0; col < 8; col++) {
				Piece originalPiece = pieces[row][col];
				if (originalPiece != null) {
					Piece copyPiece = PieceFactory.createPiece(originalPiece.getClass(), originalPiece.getColor(),
							originalPiece.getPosition(), fakeBoard, originalPiece.getPieceUI());
					if (copyPiece.getClass() == King.class) {
						if (copyPiece.getColor() == PieceColors.WHITE_PIECE)
							fakeBoard.setWhiteKing(copyPiece);
						else
							fakeBoard.setBlackKing(copyPiece);
					}
				}
			}
		
		if (((King) sameColoredKing).isChecked()) {
			/*
			 * Simulate every move for the piece
			 * If a move results in the king not being in check anymore
			 * add that move to the possible moves
			 * */
			
			for (int row = 0; row < 8; row++) 
				for (int col = 0; col < 8; col++) {
					Piece copiedPiece = copiedPieces[row][col];
					if (copiedPiece != null && copiedPiece.getColor() == getColor())
						continue;
					
					Position currentSquare = new Position(row, col);
					Piece thisCopy = copiedPieces[getPosition().row()][getPosition().column()];
					Move move = new Move(thisCopy, thisCopy.getPosition(), currentSquare, false);
					if (isValidMove(move).getMoveState() == MoveState.SUCCESS) {
						// Simulate the move
						Position oldPosition = thisCopy.getPosition();
						Piece currentSquarePiece = copiedPieces[currentSquare.row()][currentSquare.column()];

						copiedPieces[thisCopy.getPosition().row()][thisCopy.getPosition().column()] = null;
						copiedPieces[currentSquare.row()][currentSquare.column()] = thisCopy;
						thisCopy.setPosition(currentSquare);
						
						// Check if the king is still in check
						boolean isSafe = thisCopy.getColor() == PieceColors.WHITE_PIECE ? fakeBoard.isWhiteKingSafe() : fakeBoard.isBlackKingSafe();
						
						if (isSafe)
							possibleMoves.add(currentSquare);
						
						copiedPieces[thisCopy.getPosition().row()][thisCopy.getPosition().column()] = null;
						copiedPieces[oldPosition.row()][oldPosition.column()] = thisCopy;
						thisCopy.setPosition(oldPosition);
						copiedPieces[currentSquare.row()][currentSquare.column()] = currentSquarePiece;
					}
				}
			
		} else {
			/*
			 * Loop over all the squares that are not filled with a same colored piece and check if it's a valid move for the piece
			 *	IF yes
			 *	|	Add the piece to the possilbeMoves
			 *	|	CHECK if we can move from the possible square to attack the king of the other color
			 *	|	| IF YES
			 *	|	| |	SET the king's checked to True
			 *	|	| |	Add the attacking piece to the King's checkingPieces list
			 * */
			for (int row = 0; row < pieces.length; row++) {
				for (int col = 0; col < pieces[row].length; col++) {
					Piece boardPiece = copiedPieces[row][col];
					if (boardPiece != null && boardPiece.getColor() == getColor())
						continue;
					
					Position currentSquare = new Position(row, col);
					Piece thisCopy = copiedPieces[getPosition().row()][getPosition().column()];
					Move move = new Move(thisCopy, thisCopy.getPosition(), currentSquare, false);
					if (isValidMove(move).getMoveState() == MoveState.SUCCESS) {
						// Simulate the move
						Position oldPosition = thisCopy.getPosition();
						Piece currentSquarePiece = copiedPieces[currentSquare.row()][currentSquare.column()];
						
						copiedPieces[thisCopy.getPosition().row()][thisCopy.getPosition().column()] = null;
						copiedPieces[currentSquare.row()][currentSquare.column()] = thisCopy;
						thisCopy.setPosition(currentSquare);
						
						// Check if the king is still in check
						boolean isSafe = thisCopy.getColor() == PieceColors.WHITE_PIECE ? fakeBoard.isWhiteKingSafe() : fakeBoard.isBlackKingSafe();
						
						if (isSafe)
							possibleMoves.add(currentSquare);
						
						copiedPieces[thisCopy.getPosition().row()][thisCopy.getPosition().column()] = null;
						copiedPieces[oldPosition.row()][oldPosition.column()] = thisCopy;
						thisCopy.setPosition(oldPosition);
						copiedPieces[currentSquare.row()][currentSquare.column()] = currentSquarePiece;
					}
				}
			}
		}
		
		return possibleMoves;
	}
	
	@Override
	public String toString() {
		return (color.getColorName() + this.getClass().getSimpleName() + " - " + getPosition());
	}
}
