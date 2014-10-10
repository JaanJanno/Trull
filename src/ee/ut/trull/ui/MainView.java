package ee.ut.trull.ui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;
import ee.ut.trull.game.GameLoop;
import ee.ut.trull.game.GameLoop.GameState;
import ee.ut.trull.game.field.GameField;
import ee.ut.trull.game.field.GameField.FieldSlot;

/**
 * @author Jaan Janno
 */

/**
 * Klass, mis defineerib mänguvälja väljanägemise. Implementeerib ka
 * MouseListener klassi, et võimaldada vaate hiirega manipuleerimise.
 */

public class MainView extends JPanel implements MouseListener {

	private static final long serialVersionUID = 4056960530231784348L;
	
	private GameLoop loop; // Mänguloogikat kontrolliv objekt.
	
	int hight = 100; // Mängu akna suuruseid iseloomustavad arvud.
	int width = 100;

	public MainView() {
		super(true); // Enabled double buffering
		loop = new GameLoop(new GameField());
		loop.init(); // Alustab mängu kulgu kontrolliva klassi töö.
		addMouseListener(this); // Seob hiire sisendi.
	}

	@Override
	public void paintComponent(Graphics graphics) {
		
		/*
		 * Esmalt kogutakse info vaate jaoks eraldatud ekraaniruumi kohta.
		 */
		
		Graphics2D g = (Graphics2D) graphics;	
		hight = g.getClipBounds().height;
		width = g.getClipBounds().width;
		
		/*
		 * Valitakse sätted hilisemaks joonistamiseks.
		 */
		
		g.setFont(new Font("Arial", 0, 50));
		g.setStroke(new BasicStroke(5));
		g.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING, 
                RenderingHints.VALUE_ANTIALIAS_ON);

		drawField(loop.getField(), g);
		drawGrid(g);
		drawSplash(g);
	}
	
	/**
	 * Joonistab teate võitude või viikide kohta.
	 */
	
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
	
	/**
	 * Joonistab mänguvälja ruudustiku.
	 */
	
	private void drawGrid(Graphics2D g) {
		g.drawLine(0, hight / 3, width, hight / 3);
		g.drawLine(0, hight / 3 * 2, width, hight / 3 * 2);		
		g.drawLine(width / 3, 0, width / 3, hight);
		g.drawLine(width / 3 * 2, 0, width / 3 * 2, hight);
	}
	
	/**
	 * Joonistab mänguväljale ristid ja ringid.
	 * @param field Mänguväli
	 */

	private void drawField(GameField field, Graphics2D g) {
		for (int x = 0; x<3; x++){		
			for (int y = 0; y<3; y++){
				if(field.getSlot(x, y) == FieldSlot.CIRCLE)
					drawOval(x, y, width, hight, g);			
				else if(field.getSlot(x, y) == FieldSlot.CROSS)
					drawCross(x, y, width, hight, g);
			}
		}
	}
	
	/**
	 * Joonistab antud koordinaatidega ruudule ringi.
	 */

	private void drawOval(int x, int y, int w, int h, Graphics g){
		g.drawOval(w / 3 * x + 10, h / 3 * y + 10, w / 3 - 20, h / 3 - 20);
	}
	
	/**
	 * Joonistab antud koordinaatidega ruudule risti.
	 */
	
	private void drawCross(int x, int y, int w, int h, Graphics g){
		g.drawLine(w / 3 * x + 10, h / 3 * y + 10, w / 3 * (x + 1) - 20, h / 3 * (y + 1) - 20);
		g.drawLine(w / 3 * x + 10, h / 3 * (y + 1) - 20, w / 3 * (x + 1) - 20, h / 3 * y + 10);
	}
	
	/**
	 * Tagastab mänguloogikat kontrolliva objekti.
	 */

	public GameLoop getLoop() {
		return loop;
	}
	
	/**
	 * Kuulab sisendit hiirelt ning annab selle
	 * edasi mängu loogikat kontrollivale klassile.
	 */

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
