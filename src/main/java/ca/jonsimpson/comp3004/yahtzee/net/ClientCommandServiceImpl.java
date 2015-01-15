package ca.jonsimpson.comp3004.yahtzee.net;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import ca.jonsimpson.comp3004.yahtzee.Player;
import ca.jonsimpson.comp3004.yahtzee.ScoreCard;

public class ClientCommandServiceImpl extends UnicastRemoteObject implements ClientCommandService {
	
	private static final long serialVersionUID = -5230221009389671127L;
	
	public ClientCommandServiceImpl() throws RemoteException {
		super();
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
	public List<Player> updatePlayers() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateScoreCard(ScoreCard scoreCard) throws RemoteException {
		// TODO Auto-generated method stub
		
	}
	
}
