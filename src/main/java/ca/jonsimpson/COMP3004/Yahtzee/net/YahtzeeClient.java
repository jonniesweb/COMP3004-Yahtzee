package ca.jonsimpson.COMP3004.Yahtzee.net;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Date;

public class YahtzeeClient {
	
	private static final String RMI_SERVER_HOSTNAME = "localhost";
	private static final int RMI_SERVER_PORT = 1099;

	public static void main(String[] args) throws Exception {
		
		// try connecting to the remote registry
		Registry registry = LocateRegistry.getRegistry(RMI_SERVER_HOSTNAME, RMI_SERVER_PORT);
		
		// get the CommandService interface from the registry
		CommandService service = (CommandService) registry.lookup(CommandService.LOOKUPNAME);
		Date today = service.getRemoteDate();
		
		System.out.println(today);
	}
}
