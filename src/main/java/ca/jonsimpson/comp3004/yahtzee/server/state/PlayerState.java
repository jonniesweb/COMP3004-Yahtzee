package ca.jonsimpson.comp3004.yahtzee.server.state;

import ca.jonsimpson.comp3004.yahtzee.DiceSet;
import ca.jonsimpson.comp3004.yahtzee.InvalidPointCategoryException;
import ca.jonsimpson.comp3004.yahtzee.PointCategory;
import ca.jonsimpson.comp3004.yahtzee.net.NoMoreRollsException;
import ca.jonsimpson.comp3004.yahtzee.net.PointCategoryAlreadyTakenException;

public abstract class PlayerState {

	private PlayerContext context;

	public void chooseCategory(PointCategory category) throws PointCategoryAlreadyTakenException, InvalidPointCategoryException {}

	public void moveDice(DiceSet clientDice) throws CheatingException {}

	public DiceSet rollDice() throws NoMoreRollsException, IllegalAccessException {
		throw new IllegalAccessException();
	}

	public PlayerContext getContext() {
		return context;
	}

	public void setContext(PlayerContext context) {
		this.context = context;
	}
	
	public DiceSet getDice() {
		return getContext().getPlayer().getDice();
	}
	
	public void setDice(DiceSet dice) {
		getContext().getPlayer().setDice(dice);
	}
	
	public int getPlayerId() {
		return getContext().getPlayer().getId();
	}
	
}
