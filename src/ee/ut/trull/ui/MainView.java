package ee.ut.trull.ui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import ee.ut.trull.game.GameLoop;
import ee.ut.trull.game.GameLoop.GameState;
import ee.ut.trull.game.field.GameField;
import ee.ut.trull.game.field.GameField.FieldSlot;

public class MainView extends JPanel implements MouseListener {

	private static final long serialVersionUID = 4056960530231784348L;
	
	private GameLoop loop;
	
	int hight = 100;
	int width = 100;

	public MainView() {
		super(true); // Enabled double buffering
		loop = new GameLoop(new GameField());
		addMouseListener(this);
	}

	@Override
	public void paintComponent(Graphics graphics) {		
		Graphics2D g = (Graphics2D) graphics;	
		hight = g.getClipBounds().height;
		width = g.getClipBounds().width;
		
		g.setFont(new Font("Arial", 0, 50));
		g.setStroke(new BasicStroke(5));
		g.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING, 
                RenderingHints.VALUE_ANTIALIAS_ON);

		drawField(loop.getField(), g);		
		drawGrid(g);
		drawSplash(g);
	}
	
	private void drawSplash(Graphics2D g){
		if (loop.getState() != GameState.CIRCLE_MOVE && loop.getState() != GameState.CROSS_MOVE)
			g.fillRect(10, 10, 300, 50);
		g.setColor(new Color(255,0,0));
		switch (loop.getState()) {
		case DRAW:
			g.drawChars("Viik!".toCharArray(), 0, 5, 10, 50);
			break;
		case CROSS_WIN:
			g.drawChars("Rist võitis!".toCharArray(), 0, 12, 10, 50);
			break;
		case CIRCLE_WIN:
			g.drawChars("Ring võitis!".toCharArray(), 0, 12, 10, 50);
			break;
		default:
			break;
		}
		g.setColor(new Color(0,0,0));
	}
	
	private void drawGrid(Graphics2D g) {
		g.drawLine(0, hight / 3, width, hight / 3);
		g.drawLine(0, hight / 3 * 2, width, hight / 3 * 2);		
		g.drawLine(width / 3, 0, width / 3, hight);
		g.drawLine(width / 3 * 2, 0, width / 3 * 2, hight);
	}

	private void drawField(GameField field, Graphics2D g) {
		for (int y = 0; y<3; y++){		
			for (int x = 0; x<3; x++){
				if(field.getSlot(x, y) == FieldSlot.CIRCLE)
					drawOval(x, y, width, hight, g);			
				else if(field.getSlot(x, y) == FieldSlot.CROSS)
					drawCross(x, y, width, hight, g);
			}
		}
	}

	private void drawOval(int x, int y, int w, int h, Graphics g){
		g.drawOval(w / 3 * x + 10, h / 3 * y + 10, w / 3 - 20, h / 3 - 20);
	}
	
	private void drawCross(int x, int y, int w, int h, Graphics g){
		g.drawLine(w / 3 * x + 10, h / 3 * y + 10, w / 3 * (x + 1) - 20, h / 3 * (y + 1) - 20);
		g.drawLine(w / 3 * x + 10, h / 3 * (y + 1) - 20, w / 3 * (x + 1) - 20, h / 3 * y + 10);
	}

	public GameLoop getLoop() {
		return loop;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		int x = (e.getX() / (width / 3));
		int y = (e.getY() / (hight / 3));
		loop.handlePress(x, y);
		repaint();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		return;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		return;
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		return;
	}

	@Override
	public void mouseExited(MouseEvent e) {
		return;
	}
}
