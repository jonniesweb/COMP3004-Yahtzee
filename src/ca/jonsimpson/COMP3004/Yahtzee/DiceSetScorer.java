package ca.jonsimpson.COMP3004.Yahtzee;

import java.util.List;

public class DiceSetScorer {
	
	static int getPointsForOnes(DiceSet dice) {
		return getPointsForNumber(1, dice);
	}
	
	static int getPointsForTwos(DiceSet dice) {
		return getPointsForNumber(2, dice);
	}
	
	static int getPointsForThrees(DiceSet dice) {
		return getPointsForNumber(3, dice);
	}
	
	static int getPointsForFours(DiceSet dice) {
		return getPointsForNumber(4, dice);
	}
	
	static int getPointsForFives(DiceSet dice) {
		return getPointsForNumber(5, dice);
	}
	
	static int getPointsForSixes(DiceSet dice) {
		return getPointsForNumber(6, dice);
	}
	
	private static int getPointsForNumber(int number, DiceSet dice) {
		int count = 0;
		for (int integer : dice.getDice()) {
			if (integer == number) {
				count += integer;
			}
		}
		return count;
	}
	
	static int getPointsForFullHouse(DiceSet dice) {
		List<Integer> list = dice.getSortedDice();
		
		int first = list.get(0);
		int second = list.get(4);
		
		if (list.get(1).equals(first) && list.get(3).equals(second)
				&& (list.get(2).equals(first) || list.get(2).equals(second))) {
			return 25;
		}
		return 0;
	}
	
	static int getPointsForSmallFlush(DiceSet dice) {
		List<Integer> list = dice.getUniqueSortedDice();
		
		if (list.size() > 3 && list.get(3).equals(list.get(0) + 3)) {
			return 30;
		}
		
		return 0;
	}
	
	static int getPointsForLargeFlush(DiceSet dice) {
		List<Integer> list = dice.getUniqueSortedDice();
		
		if (list.size() > 3 && list.get(4).equals(list.get(0) + 4)) {
			return 40;
		}
		
		return 0;
	}
	
	static int getPointsForYahtzee(DiceSet dice) {
		List<Integer> list = dice.getDice();
		
		if (list.get(0).equals(list.get(1)) && list.get(0).equals(list.get(2))
				&& list.get(0).equals(list.get(3))
				&& list.get(0).equals(list.get(4))) {
			return 50;
		}
		return 0;
	}
	
	static int getPointsForChance(DiceSet dice) {
		return dice.getDice().stream().reduce(0, (x, y) -> x + y);
	}
}
