package ca.jonsimpson.comp3004.yahtzee.server.state;

import ca.jonsimpson.comp3004.yahtzee.DiceSet;

public class IdleState extends PlayerState {

	private PlayerStateContext context;

	public IdleState(PlayerStateContext context) {
		this.context = context;
	}
	
}
