package ca.jonsimpson.comp3004.yahtzee.server.state;

import javax.swing.Timer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import ca.jonsimpson.comp3004.yahtzee.Player;
import ca.jonsimpson.comp3004.yahtzee.net.ClientCommandService;
import ca.jonsimpson.comp3004.yahtzee.server.YahtzeeConfig;

/**
 * 
 */
public class CountdownUntilStartState extends ServerState {
	
	private static final Log log = LogFactory.getLog(CountdownUntilStartState.class);


	public CountdownUntilStartState(ServerContext context) {
		super(context);
		log.info("Starting 30 second countdown until the game starts");
		
		// create a new timer to switch the state to GameRunningState after
		// 30 seconds
		int timeout = YahtzeeConfig.getInstance().getInteger("GameStartWaitPeriodMillis", 30000);
		Timer timer = new Timer(timeout, e -> getContext().setState(
				new GameRunningState(getContext()))); 
		timer.start();
		timer.setRepeats(false);
		
	}
	
	@Override
	public void connect(String sessionID, Player player,
			ClientCommandService client) {
		getContext().connect(sessionID, player, client);
	}
	
	@Override
	public String toString() {
		return "CountdownUntilStartState";
	}

}
