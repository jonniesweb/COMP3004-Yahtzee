package ca.jonsimpson.comp3004.yahtzee.server.state;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import yahtzeeTrace.ScoreType;
import yahtzeeTrace.YahtzeeTrace;
import yahtzeeTrace.YahtzeeTracer;
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
	private Map<String, PlayerContext> players = new HashMap<String, PlayerContext>();
	private ScoreCard scoreCard = new ScoreCard();
	private YahtzeeTrace tracer = new YahtzeeTracer();
	
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
	public void scorePlayer(Player player, PointCategory category, DiceSet dice) throws InvalidPointCategoryException, PointCategoryAlreadyTakenException {
		int pointsFor = DiceSetScorer.getPointsFor(category, dice);
		
		ScoreCardEntry scoreCardEntry = new ScoreCardEntry(pointsFor, player);
		
		scoreCard.addScoreCardEntry(category, scoreCardEntry);
		
		traceScore(player, dice, pointsFor, category);
	}

	private void traceScore(Player player, DiceSet dice, int pointsFor, PointCategory category) {
		// trace scoring points
		if (getState() instanceof GameRunningState) {
			GameRunningState state = (GameRunningState) getState();
			
			ScoreType scoreType = null;
			switch (category) {
			case ONES:
				scoreType = ScoreType.ACES;
				break;
			case TWOS:
				scoreType = ScoreType.TWOS;
				break;
			case THREES:
				scoreType = ScoreType.THREES;
				break;
			case FOURS:
				scoreType = ScoreType.FOURS;
				break;
			case FIVES:
				scoreType = ScoreType.FIVES;
				break;
			case SIXES:
				scoreType = ScoreType.SIXES;
				break;
			case THREE_KIND:
				scoreType = ScoreType.THREEOAK;
				break;
			case FOUR_KIND:
				scoreType = ScoreType.FOUROAK;
				break;
			case FULL_HOUSE:
				scoreType = ScoreType.FULLHOUSE;
				break;
			case SMALL_STRAIGHT:
				scoreType = ScoreType.SMSTRAIGHT;
				break;
			case LARGE_STRAIGHT:
				scoreType = ScoreType.LGSTRAIGHT;
				break;
			case CHANCE:
				// guess there's no chances with Darryl's ScoreType enum
				// might as well assign it to something since the tracer code
				// just outright breaks otherwise
				scoreType = ScoreType.ACES;
				// that feels better
				break;
			case YAHTZEE:
			case BONUS_1:
			case BONUS_2:
			case BONUS_3:
				scoreType = ScoreType.YAHTZEE;
				break;
			default:
				scoreType = ScoreType.YAHTZEE;
				break;
			}
			
			getTracer().traceScore(state.getRound(), player.getId(), dice.getDice(), pointsFor, scoreType);
		}
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
		PlayerContext playerStateContext = new PlayerContext(player, client, this);
		
		getPlayers().put(sessionID, playerStateContext);
		
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
		log.info("Set ServerState to " + getState());
	}

	public void clearPlayerContexts() {
		players = new HashMap<String, PlayerContext>();
	}

	public void updateClientScoreCards() {
		for (PlayerContext context : getAllPlayerContexts()) {
			try {
				context.getService().updateScoreCard(scoreCard);
			} catch (RemoteException e) {
				// TODO Handle player disconnect
				e.printStackTrace();
			}
		}
	}

	protected Collection<PlayerContext> getAllPlayerContexts() {
		Collection<PlayerContext> playerContext = getPlayers().values();
		return playerContext;
	}

	public YahtzeeTrace getTracer() {
		return tracer;
	}

	public void setTracer(YahtzeeTrace tracer) {
		this.tracer = tracer;
	}

	public ScoreCard getScoreCard() {
		return scoreCard;
	}
	
	

}
