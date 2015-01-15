package ca.jonsimpson.comp3004.yahtzee;

public class ScoreCardEntry {
	
	private int points;
	private Player player;
	/**
	 * @param points
	 * @param player
	 */
	public ScoreCardEntry(int points, Player player) {
		this.points = points;
		this.player = player;
	}
	public int getPoints() {
		return points;
	}
	public void setPoints(int points) {
		this.points = points;
	}
	public Player getPlayer() {
		return player;
	}
	public void setPlayer(Player player) {
		this.player = player;
	}
	@Override
	public String toString() {
		return "Points [" + points + "] Player [" + player.getName() + "]";
	}
	
	
}
