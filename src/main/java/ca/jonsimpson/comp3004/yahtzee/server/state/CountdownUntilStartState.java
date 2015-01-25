package ca.jonsimpson.comp3004.yahtzee.server.state;

import javax.swing.Timer;

import ca.jonsimpson.comp3004.yahtzee.Player;
import ca.jonsimpson.comp3004.yahtzee.net.ClientCommandService;

/**
 * 
 */
public class CountdownUntilStartState extends ServerState {

	private static final int THIRTY_SECONDS = 30000;

	public CountdownUntilStartState(ServerContext context) {
		super(context);
		log.info("Starting 30 second countdown until the game starts");
		
		// create a new timer to switch the state to GameRunningState after
		// 30 seconds
		Timer timer = new Timer(THIRTY_SECONDS, e -> getContext().setState(
				new GameRunningState(getContext()))); 
		timer.start();
		timer.setRepeats(false);
		
	}
	
	@Override
	public void connect(String sessionID, Player player,
			ClientCommandService client) {
		getContext().connect(sessionID, player, client);
	}

}
