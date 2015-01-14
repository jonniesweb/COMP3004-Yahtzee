package ca.jonsimpson.comp3004.yahtzee;


public class YahtzeeLogic {
	boolean isGameStarted = false;
	boolean isRoundStarted = false;
	
	void startGame() {
		isGameStarted = true;
	}
	
	void stopGame() {
		isGameStarted = false;
	}
	
	void startRound() {
		isRoundStarted = true;
	}
	
	void stopRound() {
		isRoundStarted = false;
	}
	
	
	

}
