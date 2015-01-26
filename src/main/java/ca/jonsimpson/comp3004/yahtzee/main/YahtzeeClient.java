package ca.jonsimpson.comp3004.yahtzee.main;

import ca.jonsimpson.comp3004.yahtzee.client.ClientLogic;
import ca.jonsimpson.comp3004.yahtzee.client.ui.Gameboard;

public class YahtzeeClient {
	
	public static void main(String[] args) {
		new ClientLogic(new Gameboard());
	}
}
