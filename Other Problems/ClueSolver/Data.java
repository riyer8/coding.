package com.clue.bot;

/**
 * 
 * Data acquired during a turn.
 * 
 * @player is either player name if card is identified or 'Found' if part of solution set is identified
 * @card is the card associated with the player described above
 *
 */
public class Data {
	String player;
	String card;
	
	public Data(String player, String card) {
		this.player = player;
		this.card = card;
	}
	
	public String toString() {
		return player + " " + card;
	}
}


// should have new info at line 8