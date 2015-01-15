package ca.jonsimpson.comp3004.yahtzee;

import java.io.Serializable;

public class Player implements Serializable {

	private static final long serialVersionUID = -1033104103607892630L;

	
	/**
	 * Unique identifier for the client and server to use for the length of the
	 * game
	 */
	private String id;
	
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
	 * Create a player with the given name.
	 * @param name
	 */
	public Player(String id, String name, String secret) {
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

	public String getId() {
		return id;
	}

	public String getSecret() {
		return secret;
	}

}
