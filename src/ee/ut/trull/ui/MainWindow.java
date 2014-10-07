package ee.ut.trull.ui;

import java.awt.Dimension;
import javax.swing.JFrame;

public class MainWindow extends JFrame {

	private static final long serialVersionUID = 5321579545089254563L;

	public MainWindow(String title) {
		super(title);
		initWindow();
	}

	public void initWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(150, 150, 300, 300);
		setMinimumSize(new Dimension(300, 300));
		setJMenuBar(new MenuBar(this));
		add(new MainView());
		setVisible(true);
	}

	public static void main(String[] args) {
		new MainWindow("Trull");
	}
}
