package models;

/*
 * INSTANCE VARIABLES
 * |	DECLARE 2-dimensional array that will hold all the peices on the board, name it "pieces"
 * |	DECLARE a boolean that will indicate if the game is over or not, name it "gameOver"
 * |	DECLARE a variable that will hold the winning player of the game, name it "winningPlayer"
 * 
 * METHOD DECLARATION
 * |	DECLARE a method movePiece(Move move) that will take a Move parameter and move the piece by calling the Piece's moveTo()
 * |	DECLARE a method removePiece(Piece piece) that will take a Piece as a parameter and remove it from the 2-dimensional array
 * 
 * METHOD LOGIC
 * |	void movePiece(Move move)
 * |	| IF game is not over
 * |	| | CALL the piece's movePiece(Move move) method
 * */

public class ChessBoard {
	// Instance variables
	private Piece[][] pieces;
	private boolean gameOver;
	private Player winningPlayer;
	
	// Constructors
	public ChessBoard(Piece[][] pieces) {
		this.pieces = pieces;
	}
	
	// Getters
	public Piece[][] getPieces() { return pieces; }
	public boolean isGameOver() { return gameOver; }
	public Player getWinningPlayer() { return winningPlayer; }
	
	// Setters
	public void setPieces(Piece[][] pieces) { this.pieces = pieces; }
	public void setGameOver(boolean gameOver) { this.gameOver = gameOver; }
	public void setWinningPlayer(Player player) { this.winningPlayer = player; }
	
	// Methods
	public void movePiece(Move move) {
		if (gameOver) {
			System.out.println("Game is over, you can't move now.");
			return;
		}
		Piece piece = move.piece();
		piece.movePiece(move);
	}
}
