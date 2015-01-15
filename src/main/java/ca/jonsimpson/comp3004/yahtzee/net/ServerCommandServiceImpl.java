package ca.jonsimpson.comp3004.yahtzee.net;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ca.jonsimpson.comp3004.yahtzee.DiceSet;
import ca.jonsimpson.comp3004.yahtzee.Player;
import ca.jonsimpson.comp3004.yahtzee.PointCategory;

public class ServerCommandServiceImpl extends UnicastRemoteObject implements ServerCommandService {

	private static final long serialVersionUID = 6167309695864789937L;
	Map<String, ClientCommandService> clients = new HashMap<>();
	
	
	public ServerCommandServiceImpl() throws RemoteException {
		super();
	}

	@Override
	public Date getRemoteDate() {
		return new Date();
	}

	@Override
	public void connect(String sessionID, Player player, ClientCommandService client) {
		new Player(player.getId(), player.getName(), sessionID);
		
		clients.put(sessionID, client);
	}

	@Override
	public List<Player> getPlayers() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DiceSet rollDice(String sessionID, DiceSet dice) throws RemoteException,
			NoMoreRollsException {
		// TODO Auto-generated method stub
		return null;
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
	
	
	
}
