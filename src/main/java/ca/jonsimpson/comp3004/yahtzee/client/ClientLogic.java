package ca.jonsimpson.comp3004.yahtzee.client;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;
import java.util.UUID;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import ca.jonsimpson.comp3004.yahtzee.DiceSet;
import ca.jonsimpson.comp3004.yahtzee.Player;
import ca.jonsimpson.comp3004.yahtzee.client.ui.Gameboard;
import ca.jonsimpson.comp3004.yahtzee.main.IView;
import ca.jonsimpson.comp3004.yahtzee.net.ClientCommandServiceImpl;
import ca.jonsimpson.comp3004.yahtzee.net.NoMoreRollsException;
import ca.jonsimpson.comp3004.yahtzee.net.ServerCommandService;

public class ClientLogic extends Observable {
	
	private static final String RMI_SERVER_HOSTNAME = "localhost";
	private static final int RMI_SERVER_PORT = 1099;
	
	private static final Log log = LogFactory.getLog(ClientLogic.class);
	private String sessionID;
	private ServerCommandService service;
	private IView view;
	
	public ClientLogic(IView view) {
		this.view = view;
		
		try {
			connect();
		} catch (RemoteException | NotBoundException e ) {
			log.fatal("Unable to connect to the server. Is it not running?", e);
		}
		
		addObservers();
		
	}

	private void addObservers() {
		view.addRollDiceObserver(new Observer() {
			
			@Override
			public void update(Observable o, Object arg) {
				try {
					rollDice();
				} catch (RemoteException | NoMoreRollsException e) {
					e.printStackTrace();
				}
			}
		});
		
	}

	public void rollDice() throws RemoteException, NoMoreRollsException {
		DiceSet dice = service.rollDice(sessionID);
		view.updateDice(dice);
		log.info("rolled dice: " + dice.getDice());
	}

	private void connect() throws RemoteException, NotBoundException {
		// try connecting to the remote registry
		Registry registry = LocateRegistry.getRegistry(RMI_SERVER_HOSTNAME, RMI_SERVER_PORT);
		
		service = (ServerCommandService) registry.lookup(ServerCommandService.LOOKUPNAME);
		
		// create unique passphrase for authentication
		sessionID = UUID.randomUUID().toString();
		
		Player player = new Player(Integer.toString(new Random().nextInt()), "billy", sessionID);
		
		service.connect(sessionID, player, new ClientCommandServiceImpl());
		log.info("Successfully connected to the server");
		
	}
	
	public static void main(String[] args) {
		new ClientLogic(new Gameboard());
	}
	
}
