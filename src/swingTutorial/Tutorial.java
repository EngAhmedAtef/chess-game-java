package swingTutorial;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class Tutorial {

	public static void main(String[] args) {
		new MyFrame();
	}

}

class MyFrame extends JFrame {
	private static final long serialVersionUID = -6064751546988489561L;
	JLabel label;
	DragPanel dragPanel = new DragPanel();
	
	MyFrame() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600, 600);
		setTitle("Drag & drop");
		

		add(dragPanel);
		setVisible(true);
	}
}

class DragPanel extends JPanel {
	
	ImageIcon icon = new ImageIcon("whiteQueen.png");
	final int WIDTH = icon.getIconWidth();
	final int HEIGHT = icon.getIconHeight();
	Point imageCorner;
	Point prevPt;
	
	DragPanel() {
		imageCorner = new Point(0, 0);
		ClickListener clickListener = new ClickListener();
		DragListener dragListener = new DragListener();
		addMouseListener(clickListener);
		addMouseMotionListener(dragListener);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		icon.paintIcon(this, g, (int) imageCorner.getX(), (int) imageCorner.getY());
	}
	
	private class ClickListener extends MouseAdapter {
		public void mousePressed(MouseEvent e) {
			prevPt = e.getPoint();
			System.out.println(prevPt);
		}
	}
	
	private class DragListener extends MouseMotionAdapter {
		
		public void mouseDragged(MouseEvent e) {
			Point currentPt = e.getPoint();
			imageCorner.translate((int) (currentPt.getX() - prevPt.getX()),
					(int) (currentPt.getY() - prevPt.getY()));
			prevPt = currentPt;
			repaint();
		}
		
	}
	
}