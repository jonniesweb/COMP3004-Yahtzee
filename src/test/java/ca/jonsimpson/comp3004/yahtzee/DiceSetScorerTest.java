package ca.jonsimpson.comp3004.yahtzee;

import static org.junit.Assert.*;

import org.junit.Test;

import ca.jonsimpson.comp3004.yahtzee.DiceSet;
import ca.jonsimpson.comp3004.yahtzee.DiceSetScorer;

public class DiceSetScorerTest {
	
	@Test
	public void test3OfAKind() {
		DiceSet set1 = getSpecificDice(1,2,3,4,5);
		assertEquals(0, DiceSetScorer.getPointsFor3OfAKind(set1));
		
		DiceSet set2 = getSpecificDice(2,3,3,3,1);
		assertEquals(12, DiceSetScorer.getPointsFor3OfAKind(set2));
		
		DiceSet set3 = getSpecificDice(3,3,3,3,3);
		assertEquals(15, DiceSetScorer.getPointsFor3OfAKind(set3));
	}
	
	@Test
	public void test4OfAKind() {
		DiceSet set1 = getSpecificDice(1,2,3,4,5);
		assertEquals(0, DiceSetScorer.getPointsFor4OfAKind(set1));
		
		DiceSet set2 = getSpecificDice(2,3,3,3,1);
		assertEquals(0, DiceSetScorer.getPointsFor4OfAKind(set2));
		
		DiceSet set3 = getSpecificDice(3,3,3,3,1);
		assertEquals(13, DiceSetScorer.getPointsFor4OfAKind(set3));
		
		DiceSet set4 = getSpecificDice(3,3,3,3,3);
		assertEquals(15, DiceSetScorer.getPointsFor4OfAKind(set4));
	}
	
	@Test
	public void testSmallFlush() {
		DiceSet set1 = getSpecificDice(1,2,3,4,5);
		assertEquals(30, DiceSetScorer.getPointsForSmallFlush(set1));

		DiceSet set2 = getSpecificDice(1,2,3,4,3);
		assertEquals(30, DiceSetScorer.getPointsForSmallFlush(set2));

		DiceSet set3 = getSpecificDice(2,3,4,5);
		assertEquals(30, DiceSetScorer.getPointsForSmallFlush(set3));
		
		DiceSet set4 = getSpecificDice(2,3,5,6);
		assertEquals(0, DiceSetScorer.getPointsForSmallFlush(set4));
		
		DiceSet set5 = getSpecificDice(2);
		assertEquals(0, DiceSetScorer.getPointsForSmallFlush(set5));

	}
	
	@Test
	public void testLargeFlush() {
		DiceSet set1 = getSpecificDice(1,2,3,4,5);
		assertEquals(40, DiceSetScorer.getPointsForLargeFlush(set1));
		
		DiceSet set2 = getSpecificDice(2,3,4,5,6);
		assertEquals(40, DiceSetScorer.getPointsForLargeFlush(set2));
		
		DiceSet set3 = getSpecificDice(1,1,1,1,3);
		assertEquals(0, DiceSetScorer.getPointsForLargeFlush(set3));
	}
	
	@Test
	public void testYahtzee() {
		DiceSet set1 = getSpecificDice(1,2,3,4,5);
		assertEquals(0, DiceSetScorer.getPointsForYahtzee(set1));
		
		DiceSet set2 = getSpecificDice(1,1,1,1,1);
		assertEquals(50, DiceSetScorer.getPointsForYahtzee(set2));
		
		DiceSet set3 = getSpecificDice(1,1,1,1,3);
		assertEquals(0, DiceSetScorer.getPointsForYahtzee(set3));
	}
	
	@Test
	public void testChance() {
		DiceSet set1 = getSpecificDice(1,2,3,4,5);
		assertEquals(15, DiceSetScorer.getPointsForChance(set1));
		
		DiceSet set2 = getSpecificDice(1,1,1,1,1);
		assertEquals(5, DiceSetScorer.getPointsForChance(set2));
		
		DiceSet set3 = getSpecificDice(1,1,1,1,3);
		assertEquals(7, DiceSetScorer.getPointsForChance(set3));
	}
	
	@Test
	public void testBonus() {
		assertEquals(0, DiceSetScorer.getPointsForBonus(1,1,1,1,1,1));
		
		assertEquals(35, DiceSetScorer.getPointsForBonus(63, 0, 0, 0, 0, 0));
		
		assertEquals(35, DiceSetScorer.getPointsForBonus(64, 0, 0, 0, 0, 0));
	}
	
	private DiceSet getSpecificDice(int ... dice) {
		DiceSet diceSet = new DiceSet();
		
		for (int i : dice) {
			diceSet.rolledDice.add(i);
		}
		return diceSet;
	}
	
}
