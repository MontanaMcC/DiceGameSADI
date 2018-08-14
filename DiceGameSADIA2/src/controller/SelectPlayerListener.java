package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.AppFrame;

public class SelectPlayerListener implements ActionListener {
	private AppFrame appFrame;
	private GameEngine gameEngine;

	public SelectPlayerListener(GameEngine gameEngine, AppFrame appFrame) {
		this.gameEngine = gameEngine;
		this.appFrame = appFrame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Player player = appFrame.getSelectedPlayer(gameEngine);
		//update status bar and dice panel of selected player
		appFrame.setStatus(gameEngine);
		appFrame.updatePanel(player, player.getRollResult());
	}
}
