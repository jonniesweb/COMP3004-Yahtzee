package ca.jonsimpson.COMP3004.Yahtzee;


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
