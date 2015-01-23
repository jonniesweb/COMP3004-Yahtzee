package ca.jonsimpson.comp3004.yahtzee.server.state;

import java.util.ArrayList;
import java.util.List;

import ca.jonsimpson.comp3004.yahtzee.DiceSet;
import ca.jonsimpson.comp3004.yahtzee.DiceSetScorer;
import ca.jonsimpson.comp3004.yahtzee.InvalidPointCategoryException;
import ca.jonsimpson.comp3004.yahtzee.Player;
import ca.jonsimpson.comp3004.yahtzee.PointCategory;
import ca.jonsimpson.comp3004.yahtzee.ScoreCard;
import ca.jonsimpson.comp3004.yahtzee.ScoreCardEntry;
import ca.jonsimpson.comp3004.yahtzee.net.PointCategoryAlreadyTakenException;

public class ServerContext {
	
	private static ScoreCard scoreCard;
	private static List<Player> players = new ArrayList<Player>();

	/**
	 * 
	 * @param player
	 * @param category
	 * @param dice
	 * @throws InvalidPointCategoryException
	 * @throws PointCategoryAlreadyTakenException
	 */
	public static void scorePlayer(Player player, PointCategory category, DiceSet dice) throws InvalidPointCategoryException, PointCategoryAlreadyTakenException {
		int pointsFor = DiceSetScorer.getPointsFor(category, dice);
		
		ScoreCardEntry scoreCardEntry = new ScoreCardEntry(pointsFor, player);
		
		scoreCard.addScoreCardEntry(category, scoreCardEntry);
	}
	
	public static void startGame() {
		scoreCard = new ScoreCard();
	}
	
	public static void stopGame() {
		// tell everyone the game has stopped and send out the final scorecard
		// switch the games state to waiting for first player
	}
}
