package ca.jonsimpson.comp3004.yahtzee;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import ca.jonsimpson.comp3004.yahtzee.net.PointCategoryAlreadyTakenException;

public class ScoreCard implements Serializable {
	
	private static final Log log = LogFactory.getLog(ScoreCard.class);
	private static final int MAX_CATEGORIES = 13;
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
		if (! scores.containsKey(pointCategory)) {
			scores.put(pointCategory, entry);
			log.info(entry.getPlayer() + " scored [" + entry.getPoints() + "] points on category " + pointCategory);
		} else {
			throw new PointCategoryAlreadyTakenException();
		}
	}
	
	public Collection<ScoreCardEntry> getScores() {
		return scores.values();
	}

	@Override
	public String toString() {
		String result = "";
		for (PointCategory entry : scores.keySet()) {
			result += entry + " " + scores.get(entry) + "\n";
		}
		
		return result;
	}


	public boolean isSpotsLeft() {
		return scores.size() >= MAX_CATEGORIES ? false : true;

	}
}
