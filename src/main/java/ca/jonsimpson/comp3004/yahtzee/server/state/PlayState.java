package ca.jonsimpson.comp3004.yahtzee.server.state;

import ca.jonsimpson.comp3004.yahtzee.DiceSet;
import ca.jonsimpson.comp3004.yahtzee.InvalidPointCategoryException;
import ca.jonsimpson.comp3004.yahtzee.Player;
import ca.jonsimpson.comp3004.yahtzee.PointCategory;
import ca.jonsimpson.comp3004.yahtzee.net.NoMoreRollsException;
import ca.jonsimpson.comp3004.yahtzee.net.PointCategoryAlreadyTakenException;

public class PlayState extends PlayerState {
	
	private PlayerStateContext context;
	private Player player;
	private int rolls = 0;
	private DiceSet dice;

	/**
	 * Creates a new PlayState, initializing a blank set of dice
	 * @param context
	 */
	public PlayState(PlayerStateContext context, Player player) {
		this.context = context;
		this.player = player;
		dice = new DiceSet();
		dice.init();
	}
	
	/**
	 * Rolls the dice until the three rolls are up
	 * @return
	 * @throws NoMoreRollsException if you roll more than three times
	 */
	public DiceSet rollDice() throws NoMoreRollsException {
		rolls++;
		if (rolls > 2) {
			dice.rollDice();
			return dice;
		} else
			throw new NoMoreRollsException();
	}
	
	/**
	 * Choose which dice to save and which to roll.
	 * @param clientDice
	 * @throws CheatingException if the client's dice differ from the servers
	 */
	public void chooseDice(DiceSet clientDice) throws CheatingException {
		if (dice.equalsIgnoreOrder(clientDice)) {
			dice = clientDice;
		} else
			throw new CheatingException();
	}
	
	public void scoreDice(PointCategory category) {
		try {
			ServerContext.scorePlayer(player, category, dice);
		} catch (InvalidPointCategoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PointCategoryAlreadyTakenException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		context.setState(new IdleState(context));
	}

}
