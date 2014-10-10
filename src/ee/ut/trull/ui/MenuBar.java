package ee.ut.trull.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

/**
 * @author Jaan Janno
 */

/**
 * Klass ülemise menüüriba loomiseks.
 */

public class MenuBar extends JMenuBar {

	private static final long serialVersionUID = -3531119364226212118L;

	public MenuBar(final MainWindow window) {
		
		/*
		 * Defineerib menüüriba valikud.
		 */
		
		JMenu fileMenu = new JMenu("Mäng");
		JMenu beginMenu = new JMenu("Alustaja");
		add(fileMenu);
		add(beginMenu);
		
		/*
		 * Seostab valikutega alamvalikud..
		 */

		JMenuItem newAction = new JMenuItem("Uus mäng");
		JMenuItem exitAction = new JMenuItem("Välju mängust");

		JRadioButtonMenuItem beginRadioAction1 = new JRadioButtonMenuItem(
				"Mängija", true);
		JRadioButtonMenuItem beginRadioAction2 = new JRadioButtonMenuItem(
				"Arvuti");
		ButtonGroup bg2 = new ButtonGroup();
		bg2.add(beginRadioAction1);
		bg2.add(beginRadioAction2);

		fileMenu.add(newAction);
		fileMenu.addSeparator();
		fileMenu.add(exitAction);

		beginMenu.add(beginRadioAction1);
		beginMenu.add(beginRadioAction2);
		
		/*
		 * Defineerib, et menüüribaga manipuleerides tuleb kasutajaliides ümber joonistada.
		 */
		
		MenuListener listener = new MenuListener() {
			
			@Override
			public void menuSelected(MenuEvent e) {
				window.repaint();
			}
			
			@Override
			public void menuDeselected(MenuEvent e) {
				window.repaint();
			}
			
			@Override
			public void menuCanceled(MenuEvent e) {
				window.repaint();
				
			}
		};
		
		fileMenu.addMenuListener(listener);
		beginMenu.addMenuListener(listener);
		
		/*
		 * Ühendab mängu alustaja valimisega seonduva
		 * loogika nuppudega.
		 */
		
		beginRadioAction1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				window.getView().getLoop().setAiBegins(false);
				window.getView().getLoop().init();			
				window.repaint();
			}
		});
		
		beginRadioAction2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				window.getView().getLoop().setAiBegins(true);
				window.getView().getLoop().init();		
				window.repaint();
			}
		});

		/*
		 * Väljumisnupule seotakse väljumise funktsioon.
		 */

		exitAction.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				window.dispose();
			}
		});
		
		/*
		 * Uue mängu nupp seotakse mängu käiku kontrolliva klassiga.
		 */
		
		newAction.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				window.getView().getLoop().init();
				window.repaint();
			}
		});
	}
}
