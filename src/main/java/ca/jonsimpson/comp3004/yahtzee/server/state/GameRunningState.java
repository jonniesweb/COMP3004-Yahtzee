package ca.jonsimpson.comp3004.yahtzee.server.state;

import java.rmi.RemoteException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import ca.jonsimpson.comp3004.yahtzee.Player;



public class GameRunningState extends ServerState {
	
	private static final Log log = LogFactory.getLog(GameRunningState.class);
	
	private int playersLeft = 0;

	public GameRunningState(ServerContext context) {
		super(context);
		
		
		/*
		 * Set the players state to PlayState and notify the clients
		 */
		log.info("Notifying all players that the game has started");
		for (PlayerContext playerContext : getContext().getAllPlayerContexts()) {
			try {
				playerContext.setState(new PlayState(playerContext));
				playerContext.getService().gameStarted();
				playersLeft++;
			} catch (RemoteException e) {
				logPlayerDisconnected(e);
			}
		}
	}

	private void logPlayerDisconnected(RemoteException e) {
		log.error("Player disconnected", e);
		playersLeft--;
	}
	
	@Override
	public void startRound() {
		
		/**
		 * Set each players state to PlayState and notify the clients
		 */
		for (PlayerContext playerContext : getContext().getAllPlayerContexts()) {
			try {
				playerContext.setState(new PlayState(playerContext));
				playerContext.getService().roundStarted();
				playersLeft++;
			} catch (RemoteException e) {
				logPlayerDisconnected(e);
			}
		}
	}

	@Override
	public void endGame() {
		
		/**
		 * Notify the clients that the game is over and destroy all
		 * PlayerContexts
		 */
		for (PlayerContext playerContext : getContext().getAllPlayerContexts()) {
			try {
				playerContext.setState(new IdleState(playerContext));
				playerContext.getService().gameEnded();
			} catch (RemoteException e) {
				logPlayerDisconnected(e);
			}
		}
		getContext().clearPlayerContexts();
		getContext().setState(new WaitForFirstPlayerState(getContext()));
	}
	
	@Override
	public String toString() {
		return "GameRunningState";
	}
	
	@Override
	public void finishTurn(Player player) {
		playersLeft--;
		if (playersLeft <= 0) {
			startRound();
		}
	}
}
