package ca.jonsimpson.COMP3004.Yahtzee.net;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientCommandService extends Remote {
	
	public void alert(String message) throws RemoteException;
}
