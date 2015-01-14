package ca.jonsimpson.COMP3004.Yahtzee.net;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public interface CommandService extends Remote {
	
	public static final String LOOKUPNAME = "CommandService";
	
	public Date getRemoteDate() throws RemoteException;
}
