package ca.jonsimpson.comp3004.yahtzee.server.state;

import ca.jonsimpson.comp3004.yahtzee.Player;
import ca.jonsimpson.comp3004.yahtzee.net.ClientCommandService;

public class PlayerContext {
	
	private Player player;
	private PlayerState state;
	private ClientCommandService service;
	
	public PlayerContext(Player player, ClientCommandService service) {
		this.player = player;
		this.service = service;
		state = new IdleState(this);
	}
	
	public PlayerState getState() {
		return state;
	}
	
	public void setState(PlayerState state) {
		this.state = state;
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
