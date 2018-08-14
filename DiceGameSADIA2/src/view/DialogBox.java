package view;

import java.util.Collection;
import javax.swing.JOptionPane;

import model.SimplePlayer;
import model.interfaces.GameEngine;
import model.interfaces.Player;

@SuppressWarnings("serial")
public class DialogBox extends JOptionPane {
	public Player getPlayer(GameEngine gameEngine) {
		Collection<Player> player = gameEngine.getAllPlayers();
		
		// adds a player with ID 1 if no players in the collection
		if (player.isEmpty()) {
			String id = "1";
			String playerName = JOptionPane.showInputDialog(null, "Please enter your name: ");
			String playerPoints = JOptionPane.showInputDialog(null, "Please enter points available: ");
			int points = Integer.parseInt(playerPoints);
			Player newPlayer = new SimplePlayer(id, playerName, points);
			return newPlayer;
		} 
		else {
			//adds player with id next after latest player id
			int id;
			id = player.size() + 1;
			String playerName = JOptionPane.showInputDialog(null, "Please enter your name: ");
			String playerPoints = JOptionPane.showInputDialog(null, "Please enter points available: ");
			int points = Integer.parseInt(playerPoints);
			Player newPlayer = new SimplePlayer(Integer.toString(id), playerName, points);
			return newPlayer;
		}

	}

	public void placeBet(GameEngine gameEngine, Player player) {
		boolean retry = true;
		
		while (retry == true) {
			String bet = JOptionPane.showInputDialog(null, "Please insert your bet: ");
			int bets = 0;
			if (bet != null) {
				try {
					bets = Integer.parseInt(bet);
					if (gameEngine.placeBet(player, bets) == true) {
						retry = false;
					} 
					else {
						JOptionPane.showMessageDialog(null, "Not enough points to bet");
					}
				} catch (NumberFormatException ie) {
					JOptionPane.showMessageDialog(null, "Bet must be a number");
					retry = true;
				}
			}
			retry = false;
		}

	}

}
