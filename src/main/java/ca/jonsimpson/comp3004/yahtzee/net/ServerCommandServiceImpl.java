package ca.jonsimpson.comp3004.yahtzee.net;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import ca.jonsimpson.comp3004.yahtzee.DiceSet;
import ca.jonsimpson.comp3004.yahtzee.Player;
import ca.jonsimpson.comp3004.yahtzee.PointCategory;
import ca.jonsimpson.comp3004.yahtzee.server.state.PlayState;
import ca.jonsimpson.comp3004.yahtzee.server.state.PlayerStateContext;

public class ServerCommandServiceImpl extends UnicastRemoteObject implements ServerCommandService {

	private static final long serialVersionUID = 6167309695864789937L;
	
	private static final Log log = LogFactory.getLog(ServerCommandServiceImpl.class);
	
	/**
	 * Players, mapped by their sessionID.
	 */
	Map<String, PlayerStateContext> players = new HashMap<>();
	
	
	public ServerCommandServiceImpl() throws RemoteException {
		super();
	}

	@Override
	public Date getRemoteDate() {
		return new Date();
	}

	@Override
	public void connect(String sessionID, Player player, ClientCommandService client) {
		log.info("Player id [" + player.getId() + "] connected");
		
		player.setSecret(sessionID);
		
		// create a new state context for the user. This could be extended to
		// provide spectators to join
		PlayerStateContext playerStateContext = new PlayerStateContext(player, client);
		
		players.put(sessionID, playerStateContext);
		
		// XXX testing
		playerStateContext.setState(new PlayState(playerStateContext));
	}

	@Override
	public List<Player> getPlayers() throws RemoteException {
		ArrayList<Player> list = new ArrayList<Player>(players.size());
		for (PlayerStateContext context : players.values()) {
			list.add(context.getPlayer());
		}
		return list;
	}

	@Override
	public DiceSet rollDice(String sessionID) throws RemoteException,
			NoMoreRollsException, IllegalAccessException {
		
		PlayerStateContext player = players.get(sessionID);
		return player.getState().rollDice();
		
//		DiceSet diceSet = new DiceSet();
//		diceSet.init();
//		diceSet.rollDice();
//		log.info("rolled dice: " + diceSet.getDice());
//		return diceSet;
	}

	@Override
	public void updatePlayer(String sessionID, Player player) throws RemoteException,
			PlayerAlreadyExistsException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void chooseCategory(String sessionID, PointCategory category) throws RemoteException,
			PointCategoryAlreadyTakenException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void moveDice(String sessionID, DiceSet dice) throws RemoteException {
//		Player player = players.get(sessionID);
//		player.set
	}
	
	
}
