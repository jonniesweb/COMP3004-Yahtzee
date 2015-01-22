package ca.jonsimpson.comp3004.yahtzee.server.state;

import ca.jonsimpson.comp3004.yahtzee.Player;

public class PlayerStateContext {
	
	PlayerState state;
	private Player player;
	
	public PlayerStateContext(Player player) {
		this.player = player;
		state = new IdleState(this);
	}
	
	public void setState(PlayerState state) {
		this.state = state;
	}
	
	public Player getPlayer() {
		return player;
	}
	
}
