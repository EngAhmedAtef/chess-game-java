package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import models.ChessBoard;
import models.Piece;


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

		/**
		 * 
		 */
		private static final long serialVersionUID = 4011166194793979686L;
		private int squareEdge;

		private ChessBoardPanel() {
			setLayout(null);
			setBorder(BorderFactory.createLineBorder(Color.black, 3));
			setPreferredSize(new Dimension(600, 600));
			setSize(600, 600);
//			setBackground(Color.yellow);

			squareEdge = getWidth() / 8;

			drawBoard();

		}

		private void drawBoard() {
			removeAll();
			Piece[][] boardPieces = board.getPieces();
			for (int row = 0; row < boardPieces.length; row++) {
				for (int col = 0; col < boardPieces[row].length; col++) {
					JButton piece = new JButton();
					if (boardPieces[row][col] != null) {
						ImageIcon pieceIcon = boardPieces[row][col].getPieceUI().getIcon();
						piece.setIcon(pieceIcon);
					}
					piece.setBackground((row + col) % 2 == 0 ? Color.white : new Color(145, 127, 179));
					piece.setBounds(col * squareEdge, row * squareEdge, squareEdge, squareEdge);
					piece.setFocusable(false);
					add(piece);
				}
			}
		}
	}
}
