package ca.jonsimpson.comp3004.yahtzee.server.state;

import java.rmi.RemoteException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import ca.jonsimpson.comp3004.yahtzee.Player;
import ca.jonsimpson.comp3004.yahtzee.PointCategory;
import ca.jonsimpson.comp3004.yahtzee.ScoreCard;
import ca.jonsimpson.comp3004.yahtzee.ScoreCardEntry;



public class GameRunningState extends ServerState {
	
	private static final Log log = LogFactory.getLog(GameRunningState.class);
	
	private int playersLeft = 0;
	private int round = 0;

	public GameRunningState(ServerContext context) {
		super(context);
		
		/*
		 * Set the players state to PlayState and notify the clients
		 */
		log.info("Notifying all players that the game has started");
		for (PlayerContext playerContext : getContext().getAllPlayerContexts()) {
			try {
				playerContext.setState(new PlayState(playerContext));
				playerContext.getService().gameStarted();
				playersLeft++;
			} catch (RemoteException e) {
				logPlayerDisconnected(e, playerContext);
			}
		}
		
		// notify the tracer the game has started
		getContext().getTracer().traceNewGame(playersLeft);
		
	}

	private void logPlayerDisconnected(RemoteException e, PlayerContext playerContext) {
		log.error("Player disconnected", e);
		playersLeft--;
		
		// notify the tracer
		getContext().getTracer().tracePlayerDropped(playerContext.getPlayer().getId());
	}
	
	@Override
	public void startRound() {
		log.info("Round " + getRound() + " starting");
		round++;
		
		/**
		 * Set each players state to PlayState and notify the clients
		 */
		for (PlayerContext playerContext : getContext().getAllPlayerContexts()) {
			try {
				playerContext.setState(new PlayState(playerContext));
				playerContext.getService().roundStarted();
				playersLeft++;
			} catch (RemoteException e) {
				logPlayerDisconnected(e, playerContext);
			}
		}
	}

	@Override
	public void endGame() {
		log.info("Game ending");
		
		// trace player total
		tracePlayerTotals();
		
		// trace end game
		getContext().getTracer().traceEndGame();
		
		/**
		 * Notify the clients that the game is over and destroy all
		 * PlayerContexts
		 */
		for (PlayerContext playerContext : getContext().getAllPlayerContexts()) {
			try {
				playerContext.setState(new IdleState(playerContext));
				playerContext.getService().gameEnded();
			} catch (RemoteException e) {
				logPlayerDisconnected(e, playerContext);
			}
		}
		getContext().clearPlayerContexts();
		getContext().setState(new WaitForFirstPlayerState(getContext()));
	}
	
	private void tracePlayerTotals() {
		
		ScoreCard scoreCard = getContext().getScoreCard();
		
		for (PlayerContext player : getContext().getPlayers().values()) {
			// get totals for the player
			int topSubtotal = 0;
			
			topSubtotal += getPointsFromPointCategory(player, PointCategory.ONES);
			topSubtotal += getPointsFromPointCategory(player, PointCategory.TWOS);
			topSubtotal += getPointsFromPointCategory(player, PointCategory.THREES);
			topSubtotal += getPointsFromPointCategory(player, PointCategory.FOURS);
			topSubtotal += getPointsFromPointCategory(player, PointCategory.FIVES);
			topSubtotal += getPointsFromPointCategory(player, PointCategory.SIXES);
			
			int topBonus = 0;
			if (topSubtotal >= 63)
				topBonus = 35;
			
			int totalTop = topSubtotal + topBonus;
			
			int bottomSubtotal = 0;
			
			bottomSubtotal += getPointsFromPointCategory(player, PointCategory.THREE_KIND);
			bottomSubtotal += getPointsFromPointCategory(player, PointCategory.FOUR_KIND);
			bottomSubtotal += getPointsFromPointCategory(player, PointCategory.FULL_HOUSE);
			bottomSubtotal += getPointsFromPointCategory(player, PointCategory.SMALL_STRAIGHT);
			bottomSubtotal += getPointsFromPointCategory(player, PointCategory.LARGE_STRAIGHT);
			bottomSubtotal += getPointsFromPointCategory(player, PointCategory.YAHTZEE);
			bottomSubtotal += getPointsFromPointCategory(player, PointCategory.CHANCE);
			
			int bonusBottom = 0;
			
			bonusBottom += getPointsFromPointCategory(player, PointCategory.BONUS_1);
			bonusBottom += getPointsFromPointCategory(player, PointCategory.BONUS_2);
			bonusBottom += getPointsFromPointCategory(player, PointCategory.BONUS_3);
			
			int totalBottom = bottomSubtotal + bonusBottom;
			
			int totalScore = totalTop + totalBottom;
			
			getContext().getTracer().tracePlayerTotal(player.getPlayer().getId(), topSubtotal, topBonus, totalTop, bonusBottom, totalBottom, totalScore);
		}
		
	}

	/**
	 * Only get points if the player matches
	 * @param player
	 * @param topSubtotal
	 * @param ones
	 */
	private int getPointsFromPointCategory(PlayerContext player, PointCategory ones) {
		ScoreCardEntry entry = getContext().getScoreCard().getScoreCardEntry(ones);
		if (entry != null && entry.getPlayer().equals(player)) {
			return entry.getPoints();
		} else
			return 0;
	}

	@Override
	public String toString() {
		return "GameRunningState";
	}
	
	@Override
	public void finishTurn(Player player) {
		playersLeft--;
		if (playersLeft <= 0) {
			if (getContext().getScoreCard().isSpotsLeft()) {
				startRound();
			} else {
				endGame();
			}
			
		}
	}

	public int getRound() {
		return round;
	}

}
