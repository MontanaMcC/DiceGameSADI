package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import controller.NewPlayerListener;
import model.interfaces.GameEngine;

@SuppressWarnings("serial")
public class PullDownMenu extends JMenuBar {
	private JMenu optionsMenu = new JMenu("Options");
	private JMenuItem addPlayer = new JMenuItem("Add Player");
	private JMenuItem exitItem = new JMenuItem("Exit");

	public PullDownMenu(GameEngine gameEngine, AppFrame appFrame) {
		NewPlayerListener newPlayerListener = new NewPlayerListener(gameEngine, appFrame);
		
		add(optionsMenu);
		optionsMenu.add(addPlayer);
		optionsMenu.add(exitItem);
		addPlayer.addActionListener(newPlayerListener);
		exitItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
	}
}
