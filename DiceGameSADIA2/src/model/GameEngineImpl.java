package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import model.interfaces.DicePair;
import model.interfaces.GameEngine;
import model.interfaces.GameEngineCallback;
import model.interfaces.Player;

public class GameEngineImpl implements GameEngine {

	Map<String,Player> players =new HashMap<String,Player>();
	Collection<GameEngineCallback> callBacks = new ArrayList<GameEngineCallback>();
	GameEngine gameEngine;
	DicePair dicePair = null;

	private DicePair rollDice() {
		int dice1 = 0;
		int dice2 = 0;

		dice1 = (int)(NUM_FACES * Math.random()) + 1;
		dice2 = (int)(NUM_FACES * Math.random()) + 1;
		dicePair = new DicePairImpl(dice1, dice2, NUM_FACES);
		return dicePair;
	}

	private int getDiceTotal(DicePair dice) {
		int total = dice.getDice1() + dice.getDice2();
		return total;
	}

	private void calculateResult() {
		Iterator iterator = this.players.entrySet().iterator();
		while(iterator.hasNext()){
			Map.Entry pair = (Map.Entry)iterator.next();
			Player player = (Player)pair.getValue();
			// if the player wins then add bet amount to points
			if(player.getBet()!=0) {
				if (getDiceTotal(player.getRollResult()) > getDiceTotal(dicePair)) {
					player.setPoints(player.getPoints() + player.getBet());
				}
				// if player the player loses then deduct bet amount from points
				else if (getDiceTotal(player.getRollResult()) < getDiceTotal(dicePair)) {
					player.setPoints(player.getPoints() - player.getBet());
				}
				// if player ties with the house then leave points as is
				else if (getDiceTotal(player.getRollResult()) == getDiceTotal(dicePair)) {
					player.setPoints(player.getPoints());
				}
			}
		}
	}

	@Override
	public boolean placeBet(Player player, int bet) {
		if (player.placeBet(bet) == true) {
			player.placeBet(bet);
			return true;
		}
		return false;
	}

	@Override
	public void rollPlayer(Player player, int initialDelay, int finalDelay, int delayIncrement) {
		// Show die face until the initial delay set is greater than the final delay set, increment by increment amount set
		while (initialDelay < finalDelay) {
			DicePair dicePair = new DicePairImpl(rollDice().getDice1(),rollDice().getDice2(),rollDice().getNumFaces());
			// loop to invoke intermediate callback for each callback engine
			for (GameEngineCallback callBack : callBacks) {
				if (callBack != null) {
					callBack.intermediateResult(player, dicePair, this);
				}
			}
			// Stop program from proceeding to next set of lines until initialDelay variables time has passed 
			try {
				Thread.sleep(initialDelay);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
			initialDelay += delayIncrement;
		}
		player.setRollResult(rollDice());
		for (GameEngineCallback callBack : callBacks) {
			if (callBack != null) {
				callBack.result(player, player.getRollResult(), this);
			}
		}
	}

	@Override
	public void rollHouse(int initialDelay, int finalDelay, int delayIncrement) {
		DicePair dicePair;
		while (initialDelay < finalDelay) {
			dicePair = new DicePairImpl(rollDice().getDice1(),rollDice().getDice2(),rollDice().getNumFaces());
			for (GameEngineCallback callBack : callBacks) {
				if (callBack != null) {
					// loop to invoke intermediate house result callback for each callback engine
					callBack.intermediateHouseResult(dicePair, this);
				}
			}
			// Stop program from proceeding to next set of lines until initialDelay variables time has passed 
			try {
				Thread.sleep(delayIncrement);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
			initialDelay = initialDelay + delayIncrement;
		}
		dicePair = new DicePairImpl(rollDice().getDice1(),rollDice().getDice2(),rollDice().getNumFaces());
		calculateResult();
		for (GameEngineCallback callBack : callBacks) {
			if (callBack != null) {
				callBack.houseResult(dicePair, this);
			}
		}
	}

	@Override
	public void addPlayer(Player player) {
		players.put(player.getPlayerId(), player);
	}

	@Override
	public Player getPlayer(String id) {
		Player player = players.get(id);
		return player;
	}

	@Override
	public boolean removePlayer(Player player) {
		if (players.remove(player.getPlayerId()) != null) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public void addGameEngineCallback(GameEngineCallback gameEngineCallback) {
		// add gameEngine call backs to GameEngineCallback collection
		this.callBacks.add(gameEngineCallback);
	}

	@Override
	public boolean removeGameEngineCallback(GameEngineCallback gameEngineCallback) {
		// remove specified GameEngineCallback from GameEngineCallback collection
		for (GameEngineCallback callBacks : callBacks) {
			if (callBacks == gameEngineCallback) {
				if (this.callBacks.remove(gameEngineCallback)) {
					return true;
				}
				else {
					return false;
				}
			}
		}
		// if specified GameEngineCallback could not be found then return false
		return false;
	}

	@Override
	public Collection<Player> getAllPlayers() {
		Collection<Player> player = players.values();
		return player;
	}

}
