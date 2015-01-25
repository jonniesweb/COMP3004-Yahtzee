package ca.jonsimpson.comp3004.yahtzee.server.state;

import java.util.List;

import ca.jonsimpson.comp3004.yahtzee.Player;
import ca.jonsimpson.comp3004.yahtzee.net.ClientCommandService;

/**
 * Waits for the first player to connect. Once the first player connects, the
 * state is changed to {@link CountdownUntilStartState}.
 */
public class WaitForFirstPlayerState extends ServerState {

	public WaitForFirstPlayerState(ServerContext context) {
		super(context);
	}

	@Override
	public void connect(String sessionID, Player player,
			ClientCommandService client) {
		getContext().connect(sessionID, player, client);
	}

	@Override
	public List<Player> getPlayers() {
		return getContext().getPlayerList();
	}

}
