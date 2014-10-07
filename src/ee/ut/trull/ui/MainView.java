package ee.ut.trull.ui;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;

public class MainView extends JPanel implements MouseListener {

	private static final long serialVersionUID = 4056960530231784348L;

	public MainView() {
		super(true); // Enabled double buffering
		addMouseListener(this);
	}

	@Override
	public void paintComponent(Graphics g) {
		g.drawChars("Terere".toCharArray(), 0, 5, 10, 10);
		g.drawOval(10, 10, 90, 90);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		System.out.println("tere");
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
