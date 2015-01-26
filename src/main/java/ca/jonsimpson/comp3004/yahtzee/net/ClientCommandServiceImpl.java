package ca.jonsimpson.comp3004.yahtzee.net;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import ca.jonsimpson.comp3004.yahtzee.Player;
import ca.jonsimpson.comp3004.yahtzee.ScoreCard;
import ca.jonsimpson.comp3004.yahtzee.client.ClientLogic;

public class ClientCommandServiceImpl extends UnicastRemoteObject implements ClientCommandService {
	
	private static final long serialVersionUID = -3619305530827427268L;
	
	private ClientLogic client;
	
	public ClientCommandServiceImpl(ClientLogic client) throws RemoteException {
		super();
		this.client = client;
	}

	@Override
	public void gameStarted() throws RemoteException {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void roundStarted() throws RemoteException {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void roundEnded() throws RemoteException {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void gameEnded() throws RemoteException {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void updateScoreCard(ScoreCard scoreCard) throws RemoteException {
		getClient().getView().updateScoreCard(scoreCard);
	}
	
	@Override
	public List<Player> updatePlayers() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	public ClientLogic getClient() {
		return client;
	}

	public void setClient(ClientLogic client) {
		this.client = client;
	}
	
}
