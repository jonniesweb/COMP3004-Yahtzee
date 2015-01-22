package ca.jonsimpson.comp3004.yahtzee.server.state;

import ca.jonsimpson.comp3004.yahtzee.Player;

/**
 * Waits for the first player to connect. Once the first player connects, the
 * state is changed to {@link CountdownUntilStartState}.
 *
 */
public class WaitForFirstPlayerState extends ServerStatelike {

	@Override
	public void connect(Player player) {
		// TODO Auto-generated method stub
		super.connect(player);
		
		log.info("Player connected: " + player);
		
	}
	
}
