package ca.jonsimpson.comp3004.yahtzee.net;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;

import ca.jonsimpson.comp3004.yahtzee.DiceSet;
import ca.jonsimpson.comp3004.yahtzee.Player;
import ca.jonsimpson.comp3004.yahtzee.PointCategory;

public interface ServerCommandService extends Remote {
	
	public static final String LOOKUPNAME = "CommandService";
	
	/**
	 * Adds the {@link ClientCommandService} to the list of currently connected
	 * clients. This allows the server to update the client on various business
	 * events aka Yahtzee events.
	 * @param client
	 * @throws RemoteException
	 */
	public void connect(String sessionID, Player player, ClientCommandService client) throws RemoteException;
	
	public List<Player> getPlayers() throws RemoteException;
	public DiceSet rollDice(String sessionID) throws RemoteException, NoMoreRollsException, IllegalAccessException;
	public void moveDice(String sessionID, DiceSet dice) throws RemoteException;
	public void updatePlayer(String sessionID, Player player) throws RemoteException, PlayerAlreadyExistsException;
	public void chooseCategory(String sessionID, PointCategory category) throws RemoteException, PointCategoryAlreadyTakenException;
	
	public Date getRemoteDate() throws RemoteException;
	
}
