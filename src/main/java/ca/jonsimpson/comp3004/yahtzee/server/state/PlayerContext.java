package ca.jonsimpson.comp3004.yahtzee.server.state;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import ca.jonsimpson.comp3004.yahtzee.Player;
import ca.jonsimpson.comp3004.yahtzee.net.ClientCommandService;

public class PlayerContext {
	
	private static final Log log = LogFactory.getLog(PlayerContext.class);
	
	private Player player;
	private PlayerState state;
	private ClientCommandService service;
	
	public PlayerContext(Player player, ClientCommandService service) {
		this.player = player;
		this.service = service;
		state = new IdleState(this);
		
		log.info("Created a new PlayerContext for " + player + " with state " + state);
	}
	
	public PlayerState getState() {
		return state;
	}
	
	public void setState(PlayerState state) {
		this.state = state;
		log.info("Set new state [" + getState() + "] for Player " + getPlayer());
	}
	
	public Player getPlayer() {
		return player;
	}

	public ClientCommandService getService() {
		return service;
	}

	public void setService(ClientCommandService service) {
		this.service = service;
	}
	
}
