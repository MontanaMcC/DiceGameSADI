package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.AppFrame;

public class RollListener implements ActionListener {

	private AppFrame appFrame;
	private GameEngine gameEngine;
	private Player player;

	public RollListener(GameEngine gameEngine, AppFrame appFrame) {
		this.gameEngine = gameEngine;
		this.appFrame = appFrame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		player = appFrame.getSelectedPlayer(gameEngine);
		//new thread to roll player after player has placed bet
		if (player.getBet() == 0) {
			JOptionPane.showMessageDialog(appFrame, "Please place bet before rolling.");
		} else {
			new Thread() {
				@Override
				public void run() {
					gameEngine.rollPlayer(player, 1, 1000, 200);
					appFrame.resetHouse();
				}
			}.start();
		}

	}

}
