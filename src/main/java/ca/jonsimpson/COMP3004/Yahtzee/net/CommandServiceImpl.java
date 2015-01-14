package ca.jonsimpson.COMP3004.Yahtzee.net;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;

public class CommandServiceImpl extends UnicastRemoteObject implements CommandService {

	private static final long serialVersionUID = 6167309695864789937L;

	protected CommandServiceImpl() throws RemoteException {
		super();
	}

	@Override
	public Date getRemoteDate() throws RemoteException {
		System.out.println("ermagerd");
		return new Date();
	}
	
	
	
}
