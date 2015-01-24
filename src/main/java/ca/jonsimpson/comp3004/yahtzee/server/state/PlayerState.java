package ca.jonsimpson.comp3004.yahtzee.server.state;

import ca.jonsimpson.comp3004.yahtzee.DiceSet;
import ca.jonsimpson.comp3004.yahtzee.PointCategory;
import ca.jonsimpson.comp3004.yahtzee.net.NoMoreRollsException;

public abstract class PlayerState {

	protected PlayerStateContext context;

	public void scoreDice(PointCategory category) {}

	public void chooseDice(DiceSet clientDice) throws CheatingException {}

	public DiceSet rollDice() throws NoMoreRollsException, IllegalAccessException {
		throw new IllegalAccessException();
	}

	public PlayerStateContext getContext() {
		return context;
	}

	public void setContext(PlayerStateContext context) {
		this.context = context;
	}
	
	public DiceSet getDice() {
		return getContext().getPlayer().getDice();
	}
	
	public void setDice(DiceSet dice) {
		getContext().getPlayer().setDice(dice);
	}
	
	public String getPlayerId() {
		return getContext().getPlayer().getId();
	}
	
}
