package ca.jonsimpson.comp3004.yahtzee.net;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import ca.jonsimpson.comp3004.yahtzee.Player;
import ca.jonsimpson.comp3004.yahtzee.ScoreCard;

public interface ClientCommandService extends Remote {
	
	public void gameStarted() throws RemoteException;
	public void roundStarted() throws RemoteException;
	public void roundEnded() throws RemoteException;
	public void gameEnded() throws RemoteException;
	
	/**
	 * Update the global score card on the client
	 * @throws RemoteException
	 */
	public void updateScoreCard(ScoreCard scoreCard) throws RemoteException;
	/**
	 * Update the list of players on the client
	 * @return
	 * @throws RemoteException
	 */
	public List<Player> updatePlayers() throws RemoteException;
	
}
