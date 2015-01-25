package ca.jonsimpson.comp3004.yahtzee.net;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;

import ca.jonsimpson.comp3004.yahtzee.DiceSet;
import ca.jonsimpson.comp3004.yahtzee.Player;
import ca.jonsimpson.comp3004.yahtzee.PointCategory;
import ca.jonsimpson.comp3004.yahtzee.server.state.CheatingException;

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
	
	/**
	 * Get a list of all players and their {@link DiceSet}'s
	 * @return
	 * @throws RemoteException
	 */
	public List<Player> getPlayers() throws RemoteException;
	
	/**
	 * Roll the dice for the player
	 * @param sessionID
	 * @return The {@link DiceSet} after being rolled
	 * @throws RemoteException
	 * @throws NoMoreRollsException
	 * @throws IllegalAccessException
	 */
	public DiceSet rollDice(String sessionID) throws RemoteException, NoMoreRollsException, IllegalAccessException;
	
	/**
	 * Reorganize the {@link DiceSet} between the rolling dice and the keeping
	 * dice
	 * 
	 * @param sessionID
	 * @param dice
	 * @throws RemoteException
	 * @throws CheatingException
	 */
	public void moveDice(String sessionID, DiceSet dice) throws RemoteException, CheatingException;
	
	/**
	 * Set the name of the player given by parameter sessionID
	 * @param sessionID
	 * @param playerName
	 * @throws RemoteException
	 */
	public void updatePlayerName(String sessionID, String playerName) throws RemoteException;
	
	/**
	 * Select a category to put the current DiceSet in for the player
	 * represented by sessionID
	 * 
	 * @param sessionID
	 * @param category
	 * @throws RemoteException
	 * @throws PointCategoryAlreadyTakenException
	 */
	public void chooseCategory(String sessionID, PointCategory category) throws RemoteException, PointCategoryAlreadyTakenException;
	
}
