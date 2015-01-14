package ca.jonsimpson.comp3004.yahtzee;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class DiceSet {
	private Random random = new Random();
	
	ArrayList<Integer> rolledDice = new ArrayList<Integer>(5);
	ArrayList<Integer> savedDice = new ArrayList<Integer>(5);
	
	DiceSet() {
//		reset();
	}
	
	void reset() {
		rolledDice.clear();
		savedDice.clear();
		
		for (int i = 0; i < 5; i++) {
			rolledDice.add(1);
		}
	}
	
	void rollDice() {
		int size = rolledDice.size();
		rolledDice.clear();
		for (int i = 0; i < size; i++) {
			rolledDice.add(getRandomRoll());
		}
	}
	
	List<Integer> getDice() {
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

	public static void main(String[] args) {
		DiceSet diceSet = new DiceSet();
		test(diceSet);
		test(diceSet);
		test(diceSet);
		test(diceSet);
		test(diceSet);
		test(diceSet);
		test(diceSet);
		test(diceSet);
		test(diceSet);
		test(diceSet);
		test(diceSet);
		test(diceSet);
		test(diceSet);
		test(diceSet);
		test(diceSet);
		
	}

	private static void test(DiceSet diceSet) {
		diceSet.rollDice();
		for (Integer integer : diceSet.rolledDice) {
			System.out.print(integer);
		}
		System.out.println();
	}
}
