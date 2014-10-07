package ee.ut.trull.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;

public class MenuBar extends JMenuBar {

	private static final long serialVersionUID = -3531119364226212118L;

	public MenuBar(final MainWindow window) {
		JMenu fileMenu = new JMenu("Mäng");
		JMenu editMenu = new JMenu("Raskusaste");
		JMenu beginMenu = new JMenu("Alustaja");
		add(fileMenu);
		add(editMenu);
		add(beginMenu);

		JMenuItem newAction = new JMenuItem("Uus mäng");
		JMenuItem infoAction = new JMenuItem("Info");
		JMenuItem exitAction = new JMenuItem("Välju mängust");

		JRadioButtonMenuItem radioAction1 = new JRadioButtonMenuItem("Lihtne");
		JRadioButtonMenuItem radioAction2 = new JRadioButtonMenuItem(
				"Tavaline", true);
		JRadioButtonMenuItem radioAction3 = new JRadioButtonMenuItem("Võimatu");
		ButtonGroup bg = new ButtonGroup();
		bg.add(radioAction1);
		bg.add(radioAction2);
		bg.add(radioAction3);

		JRadioButtonMenuItem beginRadioAction1 = new JRadioButtonMenuItem(
				"Mängija");
		JRadioButtonMenuItem beginRadioAction2 = new JRadioButtonMenuItem(
				"Arvuti", true);
		ButtonGroup bg2 = new ButtonGroup();
		bg2.add(beginRadioAction1);
		bg2.add(beginRadioAction2);

		fileMenu.add(newAction);
		fileMenu.add(infoAction);
		fileMenu.addSeparator();
		fileMenu.add(exitAction);

		editMenu.add(radioAction1);
		editMenu.add(radioAction2);
		editMenu.add(radioAction3);

		beginMenu.add(beginRadioAction1);
		beginMenu.add(beginRadioAction2);

		exitAction.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				window.dispose();
			}
		});
	}
}
