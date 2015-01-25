package ca.jonsimpson.comp3004.yahtzee.net;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import ca.jonsimpson.comp3004.yahtzee.DiceSet;
import ca.jonsimpson.comp3004.yahtzee.Player;
import ca.jonsimpson.comp3004.yahtzee.PointCategory;
import ca.jonsimpson.comp3004.yahtzee.server.state.CheatingException;
import ca.jonsimpson.comp3004.yahtzee.server.state.PlayerContext;
import ca.jonsimpson.comp3004.yahtzee.server.state.ServerContext;

public class ServerCommandServiceImpl extends UnicastRemoteObject implements ServerCommandService {

	private static final long serialVersionUID = 6167309695864789937L;
	
	private static final Log log = LogFactory.getLog(ServerCommandServiceImpl.class);
	
	/**
	 * Server context for the state pattern
	 */
	private ServerContext serverContext = new ServerContext();
	
	public ServerCommandServiceImpl() throws RemoteException {
		super();
	}

	@Override
	public List<Player> getPlayers() throws RemoteException {
		return getServerContext().getState().getPlayers();
	}

	@Override
	public DiceSet rollDice(String sessionID) throws RemoteException,
			NoMoreRollsException, IllegalAccessException {
		
		return getPlayerFromSessionID(sessionID).getState().rollDice();
	}

	private PlayerContext getPlayerFromSessionID(String sessionID) {
		return getServerContext().getPlayers().get(sessionID);
	}

	@Override
	public void updatePlayerName(String sessionID, String playerName) throws RemoteException {
		getPlayerFromSessionID(sessionID).getPlayer().setName(playerName);
		
	}

	@Override
	public void chooseCategory(String sessionID, PointCategory category) throws RemoteException,
			PointCategoryAlreadyTakenException {
		getPlayerFromSessionID(sessionID).getState().chooseCategory(category);
		
	}

	@Override
	public void moveDice(String sessionID, DiceSet dice) throws RemoteException, CheatingException {
		getPlayerFromSessionID(sessionID).getState().moveDice(dice);
	}

	public ServerContext getServerContext() {
		return serverContext;
	}

	@Override
	public void connect(String sessionID, Player player,
			ClientCommandService client) throws RemoteException {
		getServerContext().getState().connect(sessionID, player, client);
	}
	
	
}
