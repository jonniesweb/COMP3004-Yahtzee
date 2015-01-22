package ca.jonsimpson.comp3004.yahtzee.server.state;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import ca.jonsimpson.comp3004.yahtzee.DiceSet;
import ca.jonsimpson.comp3004.yahtzee.Player;
import ca.jonsimpson.comp3004.yahtzee.ScoreCard;

public abstract class ServerStatelike {
	
	protected static final Log log = LogFactory.getLog("ServerState");
	
	public void connect(Player player) {
	}
	
	public void rollDice(Player player, DiceSet dice) {
	}
	
	public void chooseCategory(Player player, DiceSet dice) {
	}
	
	public ScoreCard getScoreCard() {
		return null;
	}
	
	public ScoreCard getScoreCard(Player player) {
		return null;
	}
	
	public void startGame() {
	}
	
	public void endGame() {
	}
	
}
