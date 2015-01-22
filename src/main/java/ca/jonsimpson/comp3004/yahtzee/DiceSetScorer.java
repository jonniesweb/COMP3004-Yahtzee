package ca.jonsimpson.comp3004.yahtzee;

import java.util.HashMap;
import java.util.List;

public final class DiceSetScorer {
	
	private static final int POINTS_FULL_HOUSE = 25;
	private static final int POINTS_SMALL_FLUSH = 30;
	private static final int POINTS_LARGE_FLUSH = 40;
	private static final int POINTS_BONUS = 35;
	private static final int POINTS_YAHTZEE = 50;
	private static final int POINTS_YAHTZEE_BONUS = 100;

	public static int getPointsForOnes(DiceSet dice) {
		return getPointsForNumber(1, dice);
	}
	
	public static int getPointsForTwos(DiceSet dice) {
		return getPointsForNumber(2, dice);
	}
	
	public static int getPointsForThrees(DiceSet dice) {
		return getPointsForNumber(3, dice);
	}
	
	public static int getPointsForFours(DiceSet dice) {
		return getPointsForNumber(4, dice);
	}
	
	public static int getPointsForFives(DiceSet dice) {
		return getPointsForNumber(5, dice);
	}
	
	public static int getPointsForSixes(DiceSet dice) {
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
	
	public static int getPointsFor3OfAKind(DiceSet dice) {
		HashMap<Integer, Integer> map = dice.getDiceAsFrequencyMap();
		FrequentElementCount mostFrequentElement = getMostFrequentElement(map);
		
		if (mostFrequentElement.getFrequency() >= 3)
			return getPointsForChance(dice);
		return 0;
	}
	
	public static int getPointsFor4OfAKind(DiceSet dice) {
		HashMap<Integer, Integer> map = dice.getDiceAsFrequencyMap();
		FrequentElementCount mostFrequentElement = getMostFrequentElement(map);
		
		if (mostFrequentElement.getFrequency() >= 4)
			return getPointsForChance(dice);
		return 0;
	}

	private static FrequentElementCount getMostFrequentElement(HashMap<Integer, Integer> map) {
		Integer mostCommonDie = null;
		Integer maxFrequency = 0;
		
		for (Integer key : map.keySet()) {
			if (map.get(key) > maxFrequency) {
				mostCommonDie = key;
				maxFrequency = map.get(key);
			}
		}
		return new DiceSetScorer().new FrequentElementCount(mostCommonDie, maxFrequency);
	}
	
	private class FrequentElementCount {
		private final Integer element;
		private final Integer frequency;
		
		/**
		 * @param element
		 * @param frequency
		 */
		public FrequentElementCount(Integer element, Integer frequency) {
			this.element = element;
			this.frequency = frequency;
		}
		public Integer getFrequency() {
			return frequency;
		}
		public Integer getElement() {
			return element;
		}
	}
	
	public static int getPointsForFullHouse(DiceSet dice) {
		List<Integer> list = dice.getSortedDice();
		
		int first = list.get(0);
		int second = list.get(4);
		
		if (list.get(1).equals(first) && list.get(3).equals(second)
				&& (list.get(2).equals(first) || list.get(2).equals(second))) {
			return POINTS_FULL_HOUSE;
		}
		return 0;
	}
	
	public static int getPointsForSmallFlush(DiceSet dice) {
		List<Integer> list = dice.getUniqueSortedDice();
		
		if (list.size() > 3 && list.get(3).equals(list.get(0) + 3)) {
			return POINTS_SMALL_FLUSH;
		}
		
		return 0;
	}
	
	public static int getPointsForLargeFlush(DiceSet dice) {
		List<Integer> list = dice.getUniqueSortedDice();
		
		if (list.size() > 3 && list.get(4).equals(list.get(0) + 4)) {
			return POINTS_LARGE_FLUSH;
		}
		
		return 0;
	}
	
	public static int getPointsForYahtzee(DiceSet dice) {
		List<Integer> list = dice.getDice();
		
		if (list.get(0).equals(list.get(1)) && list.get(0).equals(list.get(2))
				&& list.get(0).equals(list.get(3))
				&& list.get(0).equals(list.get(4))) {
			return POINTS_YAHTZEE;
		}
		return 0;
	}
	
	/**
	 * Rewards bonus points if the sum of all six dice categories is at least 63.
	 * @param ones
	 * @param twos
	 * @param threes
	 * @param fours
	 * @param fives
	 * @param sixes
	 * @return
	 */
	public static int getPointsForBonus(int ones, int twos, int threes, int fours, int fives, int sixes) {
		
		if (ones + twos + threes + fours + fives + sixes >= 63)
			return POINTS_BONUS;
		return 0;
	}
	
	/**
	 * Returns the sum of all the dice.
	 * @param dice
	 * @return
	 */
	public static int getPointsForChance(DiceSet dice) {
		return dice.getDice().stream().reduce(0, (x, y) -> x + y);
	}
	
	public static int getPointsForYahtzeeBonus(DiceSet dice) {
		if (getPointsForYahtzee(dice) != 0)
			return POINTS_YAHTZEE_BONUS;
		return 0;
	}

	public static int getPointsFor(PointCategory category, DiceSet dice) throws InvalidPointCategoryException {
		switch (category) {
		case ONES:
			return getPointsForOnes(dice);
		case TWOS:
			return getPointsForTwos(dice);
		case THREES:
			return getPointsForThrees(dice);
		case FOURS:
			return getPointsForFours(dice);
		case FIVES:
			return getPointsForFives(dice);
		case SIXES:
			return getPointsForSixes(dice);
		case THREE_KIND:
			return getPointsFor3OfAKind(dice);
		case FOUR_KIND:
			return getPointsFor4OfAKind(dice);
		case FULL_HOUSE:
			return getPointsForFullHouse(dice);
		case SMALL_STRAIGHT:
			return getPointsForSmallFlush(dice);
		case LARGE_STRAIGHT:
			return getPointsForLargeFlush(dice);
		case YAHTZEE:
			return getPointsForYahtzee(dice);
		case CHANCE:
			return getPointsForChance(dice);
		case BONUS_1:
		case BONUS_2:
		case BONUS_3:
			return getPointsForYahtzeeBonus(dice);
		default:
			throw new InvalidPointCategoryException();
		}
	}
}
