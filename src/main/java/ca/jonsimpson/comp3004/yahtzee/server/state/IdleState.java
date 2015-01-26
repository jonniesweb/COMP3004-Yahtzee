package ca.jonsimpson.comp3004.yahtzee.server.state;


public class IdleState extends PlayerState {

	public IdleState(PlayerContext context) {
		setContext(context);
	}
	
	@Override
	public String toString() {
		return "IdleState";
	}
}
