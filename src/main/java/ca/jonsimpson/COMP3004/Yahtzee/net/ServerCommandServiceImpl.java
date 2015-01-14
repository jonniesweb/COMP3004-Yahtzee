package ca.jonsimpson.COMP3004.Yahtzee.net;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Date;

public class ServerCommandServiceImpl extends UnicastRemoteObject implements ServerCommandService {

	private static final long serialVersionUID = 6167309695864789937L;
	private ArrayList<ClientCommandService> clientList = new ArrayList<ClientCommandService>();

	protected ServerCommandServiceImpl() throws RemoteException {
		super();
	}

	@Override
	public Date getRemoteDate() throws RemoteException {
		System.out.println("ermagerd");
		return new Date();
	}

	@Override
	public void connect(ClientCommandService client) throws RemoteException {
		clientList.add(client);
		try {
			client.alert("whazzup!");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
}
