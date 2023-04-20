package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import models.ChessBoard;
import models.GameManager;
import models.Move;
import models.Piece;
import models.Position;
import util.BoardSquareColors;

public class ChessGameFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 800783242387129706L;
	private ChessBoard board;
	private ChessBoardPanel panel;

	public ChessGameFrame(ChessBoard board) {
		this.board = board;
		panel = new ChessBoardPanel();

		ImageIcon icon = new ImageIcon("whitePawn.png");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setIconImage(icon.getImage());
		setTitle("Atef's Chess");
		setResizable(false);

		JLabel label1 = new JLabel("Label1");
		label1.setVerticalAlignment(JLabel.CENTER);
		label1.setHorizontalAlignment(JLabel.CENTER);
		label1.setIcon(icon);
		label1.setOpaque(true);
		label1.setBackground(Color.red);

		JLabel label2 = new JLabel("Label2");
		label2.setVerticalAlignment(JLabel.CENTER);
		label2.setHorizontalAlignment(JLabel.CENTER);
		label2.setIcon(icon);
		label2.setOpaque(true);
		label2.setBackground(Color.green);

		JLabel label3 = new JLabel("Label3");
		label3.setVerticalAlignment(JLabel.CENTER);
		label3.setHorizontalAlignment(JLabel.CENTER);
		label3.setIcon(icon);
		label3.setOpaque(true);
		label3.setBackground(Color.blue);

		JLabel label4 = new JLabel("Label4");
		label4.setVerticalAlignment(JLabel.CENTER);
		label4.setHorizontalAlignment(JLabel.CENTER);
		label4.setIcon(icon);
		label4.setOpaque(true);
		label4.setBackground(Color.magenta);

		add(BorderLayout.WEST, label1);
		add(BorderLayout.EAST, label2);
		add(BorderLayout.NORTH, label3);
		add(BorderLayout.SOUTH, label4);
		add(BorderLayout.CENTER, panel);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public void drawBoard() {
		panel.drawBoard();
		repaint();
	}

	private class ChessBoardPanel extends JPanel {

		private static final long serialVersionUID = 4011166194793979686L;
		private int squareEdge;
		private List<PieceButton> possibleSquares = new ArrayList<>();
		private PieceButton[][] pieceButtons = new PieceButton[8][8];
		private Piece selectedPiece;

		private ChessBoardPanel() {
			setLayout(null);
			setBorder(BorderFactory.createLineBorder(Color.black, 3));
			setPreferredSize(new Dimension(600, 600));
			setSize(600, 600);

			squareEdge = getWidth() / 8;

			drawBoard();

		}

		private void drawBoard() {
			removeAll();
			Piece[][] boardPieces = board.getPieces();
			for (int row = 0; row < boardPieces.length; row++) {
				for (int col = 0; col < boardPieces[row].length; col++) {
					BoardSquareColors squareColor = (row + col) % 2 == 0 ? BoardSquareColors.WHITE_SQUARE
							: BoardSquareColors.VIOLET_SQUARE;
					PieceButton piece = new PieceButton(squareColor, row, col);
					pieceButtons[row][col] = piece;
					if (boardPieces[row][col] != null) {
						ImageIcon pieceIcon = boardPieces[row][col].getPieceUI().getIcon();
						piece.setIcon(pieceIcon);
					}
					Position position = new Position(row, col);
					piece.addActionListener(e -> squareClickListener(piece, position));
					add(piece);
				}
			}
		}

		private void squareClickListener(PieceButton button, Position position) {
			// CHECK if a button is already selected
			// IF YES:
				// CHECK if the button is one of the highlighted squares
				// IF YES: Move the piece
			// IF NO: 
				// IF THE BUTTON HAS A PIECE: SELECT THE BUTTON AND HIGHLIGH POSSIBLE SQUARES
			Piece piece = board.getPiece(position);
			
			if (!possibleSquares.isEmpty()) {
				if (button.isSelected())
					clearSelection(button);
				else if (possibleSquares.contains(button)) {
					Move move = new Move(selectedPiece, selectedPiece.getPosition(), position, piece != null);
					GameManager.gameManager.makeMove(move);
					clearSelection(button);
				}
			} else {
				if (piece != null) {
					selectedPiece = piece;
					button.setSelected(true);
					List<Position> possibleMoves = piece.getPossibleMoves();
					for (Position possibleMove : possibleMoves) {
						PieceButton pButton = pieceButtons[possibleMove.row()][possibleMove.column()];
						pButton.setHighlighted(true);
						possibleSquares.add(pButton);
					}
				}
			}
		}
		
		private void clearSelection(PieceButton button) {
			button.setSelected(false);
			selectedPiece = null;
			for (PieceButton possibleSqaure : possibleSquares)
				possibleSqaure.setHighlighted(false);
			possibleSquares.clear();
		}

		private class PieceButton extends JButton {

			private static final long serialVersionUID = 5153824029378847656L;
			// Instance variables
			private final BoardSquareColors squareColor;
			private boolean selected;

			// Constructor
			private PieceButton(BoardSquareColors squareColor, int row, int col) {
				this.squareColor = squareColor;
				setColor(squareColor.getColor());
				setBounds(col * squareEdge, row * squareEdge, squareEdge, squareEdge);
				setFocusable(false);
			}

			// Getters
			public boolean isSelected() { return selected; }
			
			// Setters
			public void setSelected(boolean selected) { this.selected = selected; }
			public void setHighlighted(boolean highlighted) {
				if (highlighted)
					setColor(BoardSquareColors.ORANGE_SQUARE.getColor());
				else
					setColor(squareColor.getColor());
			}
			
			// Methods
			private void setColor(Color color) {
				setBackground(color);
			}

		}
	}
}
