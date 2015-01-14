package ca.jonsimpson.COMP3004.Yahtzee.net;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class YahtzeeClient {
	
	private Log log = LogFactory.getLog(YahtzeeClient.class);
	
	private static final String RMI_SERVER_HOSTNAME = "localhost";
	private static final int RMI_SERVER_PORT = 1099;

	public YahtzeeClient() {
		log.info("Connecting to the server");
		
		try {
			// try connecting to the remote registry
			Registry registry = LocateRegistry.getRegistry(RMI_SERVER_HOSTNAME, RMI_SERVER_PORT);

			// get the CommandService interface from the registry
			ServerCommandService service = (ServerCommandService) registry.lookup(ServerCommandService.LOOKUPNAME);
			service.connect(new ClientCommandServiceImpl());
			log.info("Successfully connected to the server");
			
			Date today = service.getRemoteDate();
			
			System.out.println(today);
			
		} catch (RemoteException e) {
			log.error("Unable to connect to the server", e);
		} catch (NotBoundException e) {
			log.error("Unable to connect to the server", e);
		}
		
	}
	
	public static void main(String[] args) {
		new YahtzeeClient();
	}
}
