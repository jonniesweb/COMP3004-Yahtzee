package ca.jonsimpson.comp3004.yahtzee;

import java.io.Serializable;

public class Player implements Serializable {

	private static final long serialVersionUID = -1033104103607892630L;

	
	/**
	 * Unique identifier for the client and server to use for the length of the
	 * game
	 */
	private int id;
	
	/**
	 * A user-given name for their player
	 */
	private String name;
	
	/**
	 * A secret used for authenticating the server calls. Must be unique for
	 * each client. Needed since someone could impersonate a player. Also
	 * referred to as "sessionID".
	 */
	private transient String secret;
	
	/**
	 * The current {@link DiceSet} of the player
	 */
	private DiceSet dice;
	

	/**
	 * Create a player with the given name.
	 * @param name
	 */
	public Player(int id, String name, String secret) {
		this.id = id;
		this.name = name;
		this.secret = secret;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public String getSecret() {
		return secret;
	}
	
	public void setSecret(String secret) {
		this.secret = secret;
	}

	public DiceSet getDice() {
		return dice;
	}

	public void setDice(DiceSet dice) {
		this.dice = dice;
	}

	@Override
	public String toString() {
		return "Player id [" + getId() + "] name [" + getName() + "]";
	}
}
