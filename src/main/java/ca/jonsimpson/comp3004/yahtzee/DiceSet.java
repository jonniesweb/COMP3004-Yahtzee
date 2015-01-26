package ca.jonsimpson.comp3004.yahtzee;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

import ca.jonsimpson.comp3004.yahtzee.net.NoMoreRollsException;

public class DiceSet implements Serializable {
	private Random random = new Random();
	
	protected ArrayList<Integer> rolledDice = new ArrayList<Integer>(5);
	protected ArrayList<Integer> savedDice = new ArrayList<Integer>(5);
	protected int rollsLeft = 3;
	
	/**
	 * Create a set of dice with no current values and three rolls.
	 */
	public DiceSet() {
	}
	
	/**
	 * Initialize a new set of five dice, all set to one.
	 */
	public void testInit() {
		rolledDice.clear();
		savedDice.clear();
		
		for (int i = 0; i < 5; i++) {
			rolledDice.add(0);
		}
	}
	
	public void rollDice() throws NoMoreRollsException {
		
		// if there are no dice, add 5 dice
		if (rolledDice.size() + savedDice.size() == 0) {
			testInit();
		}
		
		// roll the dice only if rolls are available
		if (getRollsLeft() > 0) {
			setRollsLeft(getRollsLeft() - 1);

			int size = rolledDice.size();
			rolledDice.clear();
			for (int i = 0; i < size; i++) {
				rolledDice.add(getRandomRoll());
			}
		} else
			throw new NoMoreRollsException();
	}
	
	public List<Integer> getRolledDice() {
		return rolledDice;
	}
	
	public List<Integer> getSavedDice() {
		return savedDice;
	}
	
	public List<Integer> getDice() {
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.addAll(rolledDice);
		list.addAll(savedDice);
		return list;
	}
	
	List<Integer> getUniqueDice() {
		Set<Integer> s = new TreeSet<Integer>();
		s.addAll(rolledDice);
		s.addAll(savedDice);
		
		List<Integer> list = new ArrayList<Integer>(5);
		list.addAll(s);
		
		return list;
	}
	
	List<Integer> getSortedDice() {
		List<Integer> dice = getDice();
		Collections.sort(dice);
		return dice;
	}
	
	List<Integer> getUniqueSortedDice() {
		List<Integer> list = getUniqueDice();
		Collections.sort(list);
		return list;
	}
	
	/**
	 * Returns a number between 1 and 6 inclusive
	 * @return
	 */
	private int getRandomRoll() {
		return random.nextInt(6) + 1;
	}
	
	HashMap<Integer, Integer> getDiceAsFrequencyMap() {
		List<Integer> list = getDice();
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>(list.size());
		for (Integer integer : list) {
			Integer mapValue = map.get(integer);
			if (mapValue != null) {
				map.put(integer, mapValue + 1);
			} else {
				map.put(integer, 1);
			}
		}
		return map;
	}
	
	public boolean equalsIgnoreOrder(DiceSet dice) {
		return getSortedDice().equals(dice.getSortedDice())
				&& dice.getRollsLeft() == getRollsLeft();
	}

	public int getRollsLeft() {
		return rollsLeft;
	}

	public void setRollsLeft(int rollsLeft) {
		this.rollsLeft = rollsLeft;
	}

	@Override
	public String toString() {
		return "Dice: " + getDice().toString() + " rolls left: " + getRollsLeft();
	}

	public void moveSavedToRolled(int die) {
		if (savedDice.contains(die)) {
			savedDice.remove((Integer) die);
			rolledDice.add(die);
		}
	}
	
	public void moveRolledToSaved(int die) {
		if (rolledDice.contains(die)) {
			rolledDice.remove((Integer) die);
			savedDice.add(die);
		}
	}
}
