package ca.jonsimpson.comp3004.yahtzee.main;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import ca.jonsimpson.comp3004.yahtzee.Player;
import ca.jonsimpson.comp3004.yahtzee.net.ClientCommandServiceImpl;
import ca.jonsimpson.comp3004.yahtzee.net.ServerCommandService;

public class YahtzeeClient {
	
	private Log log = LogFactory.getLog(YahtzeeClient.class);
	
	private static final String RMI_SERVER_HOSTNAME = "localhost";
	private static final int RMI_SERVER_PORT = 1099;

	private String sessionID;

	public YahtzeeClient() {
		log.info("Connecting to the server");
		
		try {
			// try connecting to the remote registry
			Registry registry = LocateRegistry.getRegistry(RMI_SERVER_HOSTNAME, RMI_SERVER_PORT);

			// get the CommandService interface from the registry
			ServerCommandService service = (ServerCommandService) registry.lookup(ServerCommandService.LOOKUPNAME);
			
			sessionID = UUID.randomUUID().toString();
			
			// TODO add Player instance to the client manager
			Player player = new Player(Integer.toString(new Random().nextInt()), "billy", sessionID);
			
			service.connect(sessionID, player, new ClientCommandServiceImpl());
			log.info("Successfully connected to the server");

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
