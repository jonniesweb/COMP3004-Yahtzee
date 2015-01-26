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
import ca.jonsimpson.comp3004.yahtzee.InvalidPointCategoryException;
import ca.jonsimpson.comp3004.yahtzee.Player;
import ca.jonsimpson.comp3004.yahtzee.PointCategory;
import ca.jonsimpson.comp3004.yahtzee.client.ui.Gameboard;
import ca.jonsimpson.comp3004.yahtzee.main.IView;
import ca.jonsimpson.comp3004.yahtzee.net.ClientCommandServiceImpl;
import ca.jonsimpson.comp3004.yahtzee.net.NoMoreRollsException;
import ca.jonsimpson.comp3004.yahtzee.net.PointCategoryAlreadyTakenException;
import ca.jonsimpson.comp3004.yahtzee.net.ServerCommandService;
import ca.jonsimpson.comp3004.yahtzee.server.state.CheatingException;

public class ClientLogic extends Observable {
	
	private static final String RMI_SERVER_HOSTNAME = "localhost";
	private static final int RMI_SERVER_PORT = 1099;
	
	private static final Log log = LogFactory.getLog(ClientLogic.class);
	private String sessionID;
	private ServerCommandService service;
	private IView view;
	private DiceSet currentDice;
	
	public ClientLogic(IView view) {
		this.view = view;
		
		try {
			connect();
		} catch (RemoteException | NotBoundException e ) {
			log.fatal("Unable to connect to the server. Is it running?", e);
		}
		
		addObservers();
		
	}

	private void addObservers() {
		view.addRollDiceObserver(new Observer() {
			
			@Override
			public void update(Observable o, Object arg) {
				log.info("rolling dice");
				try {
					rollDice();
				} catch (RemoteException | NoMoreRollsException | IllegalAccessException e) {
					log.error("Unable to roll dice", e);
				}
			}
		});
		
		view.addDiceSwitchFromSavedToRolledObserver(new Observer() {
			@Override
			public void update(Observable o, Object arg) {
				if (arg instanceof String) {
					int die = Integer.parseInt((String) arg);
					log.info("Moving die [" + die + "] from saved to rolled");
					
					// move the dice
					getCurrentDice().moveSavedToRolled(die);
					
					// update the server and view
					moveDice();
				}
			}
		});
		
		view.addDiceSwitchFromRolledToSavedObserver(new Observer() {
			@Override
			public void update(Observable o, Object arg) {
				if (arg instanceof String) {
					int die = Integer.parseInt((String) arg);
					log.info("Moving die [" + die + "] from rolled to saved");
					
					// move the dice
					getCurrentDice().moveRolledToSaved(die);
					
					// update the server and view
					moveDice();
				}
			}
		});
		
		/*
		 * When a category is clicked, notify the server of the choice.
		 */
		view.addScoreCategoryObserver(new Observer() {
			@Override
			public void update(Observable o, Object arg) {
				if (arg instanceof PointCategory) {
					PointCategory category = (PointCategory) arg;
					
					try {
						service.chooseCategory(sessionID, category);
					} catch (RemoteException | PointCategoryAlreadyTakenException | InvalidPointCategoryException e) {
						log.error("Unable to choose category for points", e);
					}
				}
			}
		});
	}
	
	/**
	 * Takes the moved dice and updates the server
	 */
	private void moveDice() {
		try {
			// tell the server of the new dice
			DiceSet serverDice = service.moveDice(sessionID, getCurrentDice());
			
			// get the server version of the dice to stay
			// synchronized just in case
			view.updateDice(serverDice);
		} catch (RemoteException | CheatingException e) {
			log.error("Unable to switch dice", e);
		}
	}

	public void rollDice() throws RemoteException, NoMoreRollsException, IllegalAccessException {
		setCurrentDice(service.rollDice(sessionID));
		view.updateDice(getCurrentDice());
		log.info("rolled dice: " + getCurrentDice().getDice());
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

	public DiceSet getCurrentDice() {
		return currentDice;
	}

	public void setCurrentDice(DiceSet currentDice) {
		this.currentDice = currentDice;
	}
	
}
