package view;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import model.GameEngineImpl;
import model.SimplePlayer;
import model.interfaces.DicePair;
import model.interfaces.GameEngine;
import model.interfaces.GameEngineCallback;
import model.interfaces.Player;

@SuppressWarnings("serial")
public class AppFrame extends JFrame {

	private GameEngine gameEngine = new GameEngineImpl();
	private GameEngineCallback geci = new GameEngineCallbackImpl();
	private GameEngineCallback gecGUI = new GameEngineCallbackGUI(this);
	private PullDownMenu menu = new PullDownMenu(this.gameEngine, this);
	private ToolBar toolBar = new ToolBar(this.gameEngine, this);
	private DicePanel dicePanel = new DicePanel(this.gameEngine, this);
	private StatusBar statusBar = new StatusBar(this.gameEngine, this);
	private DialogBox dialogBox = new DialogBox();

	public AppFrame() {
		super("Dice Game");
		// setLocationByPlatform(true);
		setBounds(10, 10, 1000, 800);
		setLayout(new BorderLayout());
		setJMenuBar(menu);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void populate() {
		add(toolBar, BorderLayout.NORTH);
		add(dicePanel, BorderLayout.CENTER);
		add(statusBar, BorderLayout.SOUTH);
		setJMenuBar(menu);
	}

	public void loadPlayer() {
		Player player = new SimplePlayer("1", "John", 1000);
		this.gameEngine.addPlayer(player);
		updateComboBox(this.gameEngine);
	}

	public void addCallBacks() {
		gameEngine.addGameEngineCallback(this.geci);
		gameEngine.addGameEngineCallback(this.gecGUI);
	}
	
	public Player createPlayer(GameEngine gameEngine) {
		Player player = dialogBox.getPlayer(gameEngine);
		return player;
	}
	
	public Player getSelectedPlayer(GameEngine gameEngine) {
		Player player = toolBar.getPlayer(gameEngine);
		return player;
	}

	public void placeBet(GameEngine gameEngine, Player player) {
		dialogBox.placeBet(gameEngine, player);
	}
	
	public void updateComboBox(GameEngine gameEngine) {
		toolBar.updatePlayers(gameEngine);
	}

	public void setStatus(GameEngine gameEngine) {
		Player player = getSelectedPlayer(gameEngine);
		statusBar.setStatus(player);
	}
	
	public void updatePanel(Player player, DicePair dicePair) {

		dicePanel.updatePanel(player, dicePair);
	}

	public void updateHouse(DicePair dicePair, AppFrame frame, GameEngine gameEngine) {
		dicePanel.updateHouse(dicePair);
	}
	
	public void updateResult() {
		dicePanel.updateResult();

	}
	
	public void resetHouse() {
		dicePanel.resetHouse();
	}

}
