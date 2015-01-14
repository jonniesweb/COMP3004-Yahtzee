package ca.jonsimpson.comp3004.yahtzee.net;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ClientCommandServiceImpl extends UnicastRemoteObject implements ClientCommandService {
	
	private static final long serialVersionUID = -5230221009389671127L;
	
	public ClientCommandServiceImpl() throws RemoteException {
		super();
	}

	@Override
	public void alert(String message) throws RemoteException {
		System.out.println("message from server: " + message);
	}
	
}
