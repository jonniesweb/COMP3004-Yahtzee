package ca.jonsimpson.comp3004.yahtzee.server.state;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import ca.jonsimpson.comp3004.yahtzee.Player;
import ca.jonsimpson.comp3004.yahtzee.ScoreCard;
import ca.jonsimpson.comp3004.yahtzee.net.ClientCommandService;

public abstract class ServerState {
	
	private static final Log log = LogFactory.getLog(ServerState.class);
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
	
}
