package ca.jonsimpson.comp3004.yahtzee.server.state;

import java.util.Collection;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import ca.jonsimpson.comp3004.yahtzee.DiceSet;
import ca.jonsimpson.comp3004.yahtzee.Player;
import ca.jonsimpson.comp3004.yahtzee.PointCategory;
import ca.jonsimpson.comp3004.yahtzee.ScoreCard;
import ca.jonsimpson.comp3004.yahtzee.net.ClientCommandService;

public abstract class ServerState {
	
	protected static final Log log = LogFactory.getLog("ServerState");
	private ServerContext context;
	
	public ServerState(ServerContext context) {
		this.context = context;
	}
	
	public void connect(String sessionID, Player player, ClientCommandService client) {}
	
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
	
	public void startRound() {}
	
	public void endRound() {}
	
	public List<Player> getPlayers() {
		return null;
	}

	public ServerContext getContext() {
		return context;
	}

	protected Collection<PlayerContext> getAllPlayerContexts() {
		Collection<PlayerContext> players = getContext().getPlayers().values();
		return players;
	}
	
}
