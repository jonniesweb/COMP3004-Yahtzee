package ca.jonsimpson.comp3004.yahtzee;

import java.util.HashMap;
import java.util.Map;

import ca.jonsimpson.comp3004.yahtzee.net.PointCategoryAlreadyTakenException;

public class ScoreCard {
	
	private Map<PointCategory, ScoreCardEntry> scores = new HashMap<>();
	
	/**
	 * Get the points and player for the given category for a single game
	 * @param pointCategory
	 * @return
	 */
	public ScoreCardEntry getScoreCardEntry(PointCategory pointCategory) {
		return scores.get(pointCategory);
	}
	
	
	/**
	 * Set a {@link ScoreCardEntry} to a point category. Throws a
	 * {@link PointCategoryAlreadyTakenException} if the point category is
	 * already taken.
	 * 
	 * @param pointCategory
	 * @param entry
	 * @throws PointCategoryAlreadyTakenException
	 */
	public void addScoreCardEntry(PointCategory pointCategory, ScoreCardEntry entry) throws PointCategoryAlreadyTakenException {
		if (! scores.containsKey(pointCategory))
			scores.put(pointCategory, entry);
		else
			throw new PointCategoryAlreadyTakenException();
	}

	@Override
	public String toString() {
		String result = "";
		for (PointCategory entry : scores.keySet()) {
			result += entry + " " + scores.get(entry) + "\n";
		}
		
		return result;
	}
}
