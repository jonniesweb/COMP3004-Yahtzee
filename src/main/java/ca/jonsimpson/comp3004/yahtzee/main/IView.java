package ca.jonsimpson.comp3004.yahtzee.main;

import java.util.Observer;

import ca.jonsimpson.comp3004.yahtzee.DiceSet;
import ca.jonsimpson.comp3004.yahtzee.ScoreCard;

public interface IView {
	
	// update the state of the UI methods
	public void updateDice(DiceSet dice);
	public void updateScoreCard(ScoreCard scoreCard);
	
	
	// Add observables for various UI actions
	public void addRollDiceObserver(Observer observer);
	public void addDiceSwitchFromRollToChosenObserver(Observer observer);
	public void addDiceSwitchFromChosenToRollObserver(Observer observer);
	public void addChooseScoreCategoryObserver(Observer observer);
	
}
