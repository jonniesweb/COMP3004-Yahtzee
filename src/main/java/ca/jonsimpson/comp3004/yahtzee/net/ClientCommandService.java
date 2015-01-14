package ca.jonsimpson.comp3004.yahtzee.net;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientCommandService extends Remote {
	
	public void alert(String message) throws RemoteException;
}
