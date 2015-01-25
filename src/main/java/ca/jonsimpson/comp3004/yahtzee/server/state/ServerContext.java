package ca.jonsimpson.comp3004.yahtzee.server.state;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import ca.jonsimpson.comp3004.yahtzee.DiceSet;
import ca.jonsimpson.comp3004.yahtzee.DiceSetScorer;
import ca.jonsimpson.comp3004.yahtzee.InvalidPointCategoryException;
import ca.jonsimpson.comp3004.yahtzee.Player;
import ca.jonsimpson.comp3004.yahtzee.PointCategory;
import ca.jonsimpson.comp3004.yahtzee.ScoreCard;
import ca.jonsimpson.comp3004.yahtzee.ScoreCardEntry;
import ca.jonsimpson.comp3004.yahtzee.net.ClientCommandService;
import ca.jonsimpson.comp3004.yahtzee.net.PointCategoryAlreadyTakenException;

public class ServerContext {
	
	private static final Log log = LogFactory.getLog(ServerContext.class);

	/**
	 * Players, mapped by their sessionID
	 */
	private Map<String, PlayerContext> players = new HashMap<>();
	private static ScoreCard scoreCard;
	
	private ServerState state;

	public ServerContext() {
		state = new WaitForFirstPlayerState(this);
	}
	
	/**
	 * 
	 * @param player
	 * @param category
	 * @param dice
	 * @throws InvalidPointCategoryException
	 * @throws PointCategoryAlreadyTakenException
	 */
	static void scorePlayer(Player player, PointCategory category, DiceSet dice) throws InvalidPointCategoryException, PointCategoryAlreadyTakenException {
		int pointsFor = DiceSetScorer.getPointsFor(category, dice);
		
		ScoreCardEntry scoreCardEntry = new ScoreCardEntry(pointsFor, player);
		
		scoreCard.addScoreCardEntry(category, scoreCardEntry);
	}

	public Map<String, PlayerContext> getPlayers() {
		return players;
	}
	
	/**
	 * Should only be called by subclasses of class {@link ServerState}
	 * @param sessionID
	 * @param player
	 * @param client
	 */
	void connect(String sessionID, Player player, ClientCommandService client) {
		
		log.info("Player id [" + player.getId() + "] connected");
		
		player.setSecret(sessionID);
		
		// create a new state context for the user. This could be extended to
		// provide spectators to join
		PlayerContext playerStateContext = new PlayerContext(player, client);
		
		getPlayers().put(sessionID, playerStateContext);
		
		// XXX testing
//		playerStateContext.setState(new PlayState(playerStateContext));
	}
	
	List<Player> getPlayerList() {
		Map<String, PlayerContext> players = getPlayers();
		
		ArrayList<Player> list = new ArrayList<Player>(players.size());
		for (PlayerContext context : players.values()) {
			list.add(context.getPlayer());
		}
		return list;
	}

	public ServerState getState() {
		return state;
	}

	public void setState(ServerState state) {
		this.state = state;
	}

	public void clearPlayerContexts() {
		players = new HashMap<String, PlayerContext>();
	}
	
	

}
