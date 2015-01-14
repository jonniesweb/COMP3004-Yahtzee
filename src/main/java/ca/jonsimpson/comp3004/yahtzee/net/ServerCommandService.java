package ca.jonsimpson.comp3004.yahtzee.net;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;

public interface ServerCommandService extends Remote {
	
	public static final String LOOKUPNAME = "CommandService";
	
	public void connect(ClientCommandService client) throws RemoteException;
	
	public Date getRemoteDate() throws RemoteException;
}
