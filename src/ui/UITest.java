package ui;

import java.awt.*;

import javax.swing.*;

public class UITest {

	public static void main(String[] args) {
		new MyFrame();
	}

}

class MyFrame extends JFrame {

	private static final long serialVersionUID = 5322341613804524181L;

	MyFrame() {

		MyPanel panel = new MyPanel();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setIconImage(new ImageIcon("whitePawn.png").getImage());
		setTitle("Atef's Chess");

		ImageIcon icon = new ImageIcon("whitePawn.png");

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
}

class MyPanel extends JPanel {

	private static final long serialVersionUID = 8974674497126370970L;
	
	ImageIcon icon = new ImageIcon("whitePawn.png");
	JButton[][] pieces = new JButton[8][8];
	int squareEdge = getWidth() / 8;

	MyPanel() {
		setLayout(null);
		setBorder(BorderFactory.createLineBorder(Color.black, 3));
		setPreferredSize(new Dimension(600, 600));
		setBackground(Color.yellow);

		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				JButton piece = new JButton();
				piece.setIcon(icon);
				piece.setPreferredSize(new Dimension(squareEdge, squareEdge));
				piece.setBackground((row + col) % 2 == 0 ? Color.white : new Color(145, 127, 179));
				piece.setBounds(col * squareEdge, row * squareEdge, squareEdge, squareEdge);
				pieces[row][col] = piece;
				add(piece);
				
			}
		}
	}
}
