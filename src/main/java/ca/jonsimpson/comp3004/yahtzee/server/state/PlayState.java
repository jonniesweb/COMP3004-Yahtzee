package ca.jonsimpson.comp3004.yahtzee.server.state;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import ca.jonsimpson.comp3004.yahtzee.DiceSet;
import ca.jonsimpson.comp3004.yahtzee.InvalidPointCategoryException;
import ca.jonsimpson.comp3004.yahtzee.Player;
import ca.jonsimpson.comp3004.yahtzee.PointCategory;
import ca.jonsimpson.comp3004.yahtzee.net.NoMoreRollsException;
import ca.jonsimpson.comp3004.yahtzee.net.PointCategoryAlreadyTakenException;

public class PlayState extends PlayerState {
	
	private static final Log log = LogFactory.getLog(PlayState.class);
	
	/**
	 * Creates a new PlayState, initializing a blank set of dice
	 * @param context
	 */
	public PlayState(PlayerStateContext context) {
		this.context = context;
		setDice(new DiceSet());
	}
	
	/**
	 * Rolls the dice, returning the result
	 * @return
	 * @throws NoMoreRollsException if you roll more than three times
	 */
	@Override
	public DiceSet rollDice() throws NoMoreRollsException {
			getDice().rollDice();
			log.info("Player id [" + getPlayerId() + "] rolled " + getDice());
			return getDice();
			
	}
	
	/**
	 * Choose which dice to save and which to roll.
	 * @param clientDice
	 * @throws CheatingException if the client's dice differ from the servers
	 */
	@Override
	public void chooseDice(DiceSet clientDice) throws CheatingException {
		if (getDice().equalsIgnoreOrder(clientDice)) {
			setDice(clientDice);
		} else
			throw new CheatingException();
	}
	
	@Override
	public void scoreDice(PointCategory category) {
		try {
			ServerContext.scorePlayer(context.getPlayer(), category, getDice());
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
