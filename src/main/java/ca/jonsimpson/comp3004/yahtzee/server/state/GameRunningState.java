package ca.jonsimpson.comp3004.yahtzee.server.state;

import java.rmi.RemoteException;



public class GameRunningState extends ServerState {

	public GameRunningState(ServerContext context) {
		super(context);
		
		/*
		 * Set the players state to PlayState and notify the clients
		 */
		for (PlayerContext playerContext : getAllPlayerContexts()) {
			try {
				playerContext.setState(new PlayState(playerContext));
				playerContext.getService().gameStarted();
			} catch (RemoteException e) {
				logPlayerDisconnected(e);
			}
		}
	}

	private void logPlayerDisconnected(RemoteException e) {
		log.error("Player disconnected", e);
	}
	
	@Override
	public void startRound() {
		
		/**
		 * Set each players state to PlayState and notify the clients
		 */
		for (PlayerContext playerContext : getAllPlayerContexts()) {
			try {
				playerContext.setState(new PlayState(playerContext));
				playerContext.getService().roundStarted();
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
		for (PlayerContext playerContext : getAllPlayerContexts()) {
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
}
