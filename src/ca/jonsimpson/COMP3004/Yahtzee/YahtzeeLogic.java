package ca.jonsimpson.COMP3004.Yahtzee;

import java.util.Random;

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
