package com.clue.bot;

import java.io.*;
import java.util.*;

public class simulate {
	
	enum CardType {
		CHARACTER,
		WEAPON,
		ROOM
	}
	
	@SuppressWarnings("serial")
	static final HashSet<String> allCards = new HashSet<String>(){{
	    add("green");
	    add("plum");
	    add("mustard");
	    add("peacock");
	    add("scarlet");
	    add("white");
	    
	    add("candlestick");
	    add("knife");
	    add("leadpipe");
	    add("revolver");
	    add("rope");
	    add("wrench");
	    
	    add("kitchen");
	    add("ballroom");
	    add("dining");
	    add("lounge");
	    add("hall");
	    add("conservatory");
	    add("billiard");
	    add("library");
	    add("study");
	}};
	
	static Scanner fileReader;
	static PrintWriter out;
	
	static boolean printNotes;
	static int numPlayers, cardsPerPlayer;
	static String[] seatingArrangement;
	static String[] communityCards;
	static String[] myCards;
	static HashSet<String> characters, weapons, rooms;
	static HashMap<String, HashSet<String>> otherPlayerCards = new HashMap<String, HashSet<String>>();
	static HashMap<String, HashSet<String>> otherPlayerPossible = new HashMap<String, HashSet<String>>();
	static ArrayList<Data> newInformation = new ArrayList<Data>();
	
	@SuppressWarnings("serial")
	public static void setUpInput() throws Exception {
		printNotes = fileReader.nextBoolean();
		numPlayers = fileReader.nextInt();
		seatingArrangement = new String[2 * numPlayers];
		for (int i = 0; i < numPlayers; i++) {
			seatingArrangement[i] = fileReader.next();
		}
		for (int i = numPlayers; i < 2 * numPlayers; i++) {
			seatingArrangement[i] = seatingArrangement[i - numPlayers];
		}
		fileReader.nextLine();
		communityCards = fileReader.nextLine().split(" ");
		myCards = fileReader.nextLine().split(" ");
		cardsPerPlayer = myCards.length;
		for (int i = 0; i < numPlayers; i++) {
			if (!seatingArrangement[i].equalsIgnoreCase("me")) {
				HashSet<String> playerCards = new HashSet<String>();
				playerCards.addAll(allCards);
				playerCards.removeAll(Arrays.asList(communityCards));
				playerCards.removeAll(Arrays.asList(myCards));
				otherPlayerPossible.put(seatingArrangement[i], playerCards);
				HashSet<String> confirmedCards = new HashSet<String>();
				otherPlayerCards.put(seatingArrangement[i], confirmedCards);
			}
		}
		characters = new HashSet<String>(){{
		    add("green");
		    add("plum");
		    add("mustard");
		    add("peacock");
		    add("scarlet");
		    add("white");
		}};
		weapons = new HashSet<String>(){{		    
		    add("candlestick");
		    add("knife");
		    add("leadpipe");
		    add("revolver");
		    add("rope");
		    add("wrench");
		}};
		rooms = new HashSet<String>(){{
		    add("kitchen");
		    add("ballroom");
		    add("dining");
		    add("lounge");
		    add("hall");
		    add("conservatory");
		    add("billiard");
		    add("library");
		    add("study");
		}};
		
		for (String card : myCards) {
			characters.remove(card);
			weapons.remove(card);
			rooms.remove(card);
		}
		
		for (String card : communityCards) {
			characters.remove(card);
			weapons.remove(card);
			rooms.remove(card);
		}
	}
	
	/**
	 * @returns the type of a card
	 */
	public CardType findType(String card) {
		if (card.matches("green|plum|mustard|peacock|scarlet|white")) return CardType.CHARACTER;
		if (card.matches("candlestick|knife|leadpipe|revolver|rope|wrench")) return CardType.WEAPON;
		if (card.matches("kitchen|ballroom|dining|lounge|hall|conservatory|billiard|library|study")) return CardType.ROOM;
		return null;
	}
	
	/**
	 * Determines if any updates can be performed on the data based off current knowledge of all players cards.
	 * 
	 * @returns true or false depending on whether any data was updated during this turn
	 */
	public static boolean update() throws Exception {
		
		// Determine if we can determine a player's complete hand
		for (int i = 0; i < numPlayers; i++) {
			if (!seatingArrangement[i].equalsIgnoreCase("me")) {
				String playerName = seatingArrangement[i];
				
				// Error handling for game logic
				if (otherPlayerCards.get(playerName).size() + otherPlayerPossible.get(playerName).size() < cardsPerPlayer) {
					out.println("Unfortunately, the logic on deleting of player cards has gone wrong");
					return false;
				}
				
				if (otherPlayerPossible.get(playerName).size() > 0 &&
						otherPlayerCards.get(playerName).size() + otherPlayerPossible.get(playerName).size() == cardsPerPlayer) {
					otherPlayerCards.get(playerName).addAll(otherPlayerPossible.get(playerName));
					for (int j = 0; j < numPlayers; j++) {
						if (seatingArrangement[j].equalsIgnoreCase("me")) continue;
						if (!seatingArrangement[j].equalsIgnoreCase(playerName)) {
							otherPlayerPossible.get(seatingArrangement[j]).removeAll(otherPlayerPossible.get(playerName));
						}
					}
					otherPlayerPossible.get(playerName).clear();
					return true;
				}
			}
		}
		
		// Remove any new updated player hands from our solution set
		Iterator<String> iterator = characters.iterator();
		while (iterator.hasNext()) {
		    String element = iterator.next();
		    for (int i = 0; i < numPlayers; i++) {
		    	if (seatingArrangement[i].equalsIgnoreCase("me")) continue;
		    	if (otherPlayerCards.get(seatingArrangement[i]).contains(element)) {
		    		iterator.remove();
		    		return true;
		    	}
		    }
		}
		
		iterator = weapons.iterator();
		while (iterator.hasNext()) {
			String element = iterator.next();
			for (int i = 0; i < numPlayers; i++) {
				if (seatingArrangement[i].equalsIgnoreCase("me")) continue;
				if (otherPlayerCards.get(seatingArrangement[i]).contains(element)) {
					iterator.remove();
					return true;
				}
			}
		}
		
		iterator = rooms.iterator();
		while (iterator.hasNext()) {
			String element = iterator.next();
			for (int i = 0; i < numPlayers; i++) {
				if (seatingArrangement[i].equalsIgnoreCase("me")) continue;
				if (otherPlayerCards.get(seatingArrangement[i]).contains(element)) {
					iterator.remove();
					return true;
				}
			}
		}
		
		// Go through all previous moves and see if we can conclude any data
		Scanner secondRead = new Scanner(new File("game.txt"));
		secondRead.nextLine();
		secondRead.nextLine();
		secondRead.nextLine();
		secondRead.nextLine();
		secondRead.nextLine();
		while (secondRead.hasNext()) {
			String[] move = secondRead.nextLine().split(" ");
			String playerTurn = move[0];
			String character_query = move[1];
			String weapon_query = move[2];
			String room_query = move[3];
			String playerRespond = move[4];
			String response = null;
			if (playerTurn.equalsIgnoreCase("me")) response = move[5];
			
			if (playerTurn.equalsIgnoreCase("me") ||
					playerRespond.equalsIgnoreCase("none") || playerRespond.equalsIgnoreCase("me")) continue;
			
			int known_character = 0;
			int known_weapon = 0;
			int known_room = 0;
			
			for (int j = 0; j < numPlayers; j++) {
				if (seatingArrangement[j].equalsIgnoreCase("me")) continue;
				if (otherPlayerCards.get(seatingArrangement[j]).contains(character_query))
					known_character++;
				if (otherPlayerCards.get(seatingArrangement[j]).contains(weapon_query))
					known_weapon++;
				if (otherPlayerCards.get(seatingArrangement[j]).contains(room_query))
					known_room++;
			}
			
			for (String card : myCards) {
				if (card.equalsIgnoreCase(character_query)) known_character++;
				if (card.equalsIgnoreCase(weapon_query)) known_weapon++;
				if (card.equalsIgnoreCase(room_query)) known_room++;
			}
			
			if (known_character + known_weapon + known_room == 2) {
				if (known_character == 0) {
					otherPlayerCards.get(playerRespond).add(character_query);
					for (int k = 0; k < numPlayers; k++) {
						if (seatingArrangement[k].equalsIgnoreCase("me")) continue;
						otherPlayerPossible.get(seatingArrangement[k]).remove(character_query);
					}
				}
				
				if (known_weapon == 0) {
					otherPlayerCards.get(playerRespond).add(weapon_query);
					for (int k = 0; k < numPlayers; k++) {
						if (seatingArrangement[k].equalsIgnoreCase("me")) continue;
						otherPlayerPossible.get(seatingArrangement[k]).remove(weapon_query);
					}
				}
				
				if (known_room == 0) {
					otherPlayerCards.get(playerRespond).add(room_query);
					for (int k = 0; k < numPlayers; k++) {
						if (seatingArrangement[k].equalsIgnoreCase("me")) continue;
						otherPlayerPossible.get(seatingArrangement[k]).remove(room_query);
					}
				}
				
				return true;
			}
		}
		
		// Update my list
		
		boolean neededUpd = false;
		for (int i = 0; i < numPlayers; i++) {
			if (seatingArrangement[i].equalsIgnoreCase("me")) continue;
			for (String card : otherPlayerCards.get(seatingArrangement[i])) {
				neededUpd |= characters.remove(card);
				neededUpd |= weapons.remove(card);
				neededUpd |= rooms.remove(card);
			}
		}
		
		
		return false;
	}
	
	public static boolean solvedClues() {
		return characters.size() == 1 && weapons.size() == 1 && rooms.size() == 1;
	}
	
	@SuppressWarnings("serial")
	public static void simulateGame() throws Exception {
		while (fileReader.hasNextLine()) {
			newInformation.clear();
			
			String[] movePlayed = fileReader.nextLine().split(" ");
			String playerTurn = movePlayed[0];
			String character_query = movePlayed[1];
			String weapon_query = movePlayed[2];
			String room_query = movePlayed[3];
			String playerRespond = movePlayed[4];
			String response = null;
			if (playerTurn.equalsIgnoreCase("me")) response = movePlayed[5];
			
			int turn_index = -1, respond_index = -1;
			for (int i = 0; i < seatingArrangement.length; i++) {
				if (seatingArrangement[i].equalsIgnoreCase(playerTurn)) {
					turn_index = i;
					break;
				}
			}
			for (int i = turn_index + 1; i < seatingArrangement.length; i++) {
				if (seatingArrangement[i].equalsIgnoreCase(playerRespond)) {
					respond_index = i;
					break;
				}
			}
			if (playerRespond.equalsIgnoreCase("none")) respond_index = turn_index + numPlayers;
			
			// Remove query cards from players that passed their turn			
			for (int j = turn_index + 1; j < respond_index; j++) {
				String playerName = seatingArrangement[j];
				if (playerName.equalsIgnoreCase("me")) continue;
				otherPlayerPossible.get(playerName).remove(character_query);
				otherPlayerPossible.get(playerName).remove(weapon_query);
				otherPlayerPossible.get(playerName).remove(room_query);
			}
			
			// If it was my turn just now
			if (playerTurn.equalsIgnoreCase("me")) {
				// If everyone passes my turn then something can be determined
				if (playerRespond.equalsIgnoreCase("none")) {
					boolean character_exists = false, weapon_exists = false, room_exists = false;
					for (int i = 0; i < myCards.length; i++) {
						if (myCards[i].equalsIgnoreCase(character_query)) character_exists = true;
						if (myCards[i].equalsIgnoreCase(weapon_query)) weapon_exists = true;
						if (myCards[i].equalsIgnoreCase(room_query)) room_exists = true;
					}
					if (!character_exists) {
						characters = new HashSet<String>(){{ add(character_query); }};
					}
					if (!weapon_exists) {
						weapons = new HashSet<String>(){{ add(weapon_query); }};
					}
					if (!room_exists) {
						rooms = new HashSet<String>(){{ add(room_query); }};
					}
				}
				// We now know that the other player has this card confirmed
				else {
					otherPlayerPossible.get(playerRespond).remove(response);
					otherPlayerCards.get(playerRespond).add(response);
				}
			}
			
			boolean updateData = true;
			
			while (updateData) {
				updateData = update();
			}
			
			if (characters.size() == 1) {
				for (String c : characters)
					newInformation.add(new Data("Found", c));
			}
			if (weapons.size() == 1) {
				for (String w : weapons)
					newInformation.add(new Data("Found", w));
			}
			if (rooms.size() == 1) {
				for (String r : rooms)
					newInformation.add(new Data("Found", r));
			}
			
			System.out.println("Turn");
			System.out.println("Characters Left: " + characters);
			System.out.println("Weapons Left: " + weapons);
			System.out.println("Rooms Left: " + rooms);
			
			System.out.println();
			
			for (int i = 0; i < numPlayers; i++) {
				if (seatingArrangement[i].equalsIgnoreCase("me")) continue;
				System.out.println("Name: " + seatingArrangement[i]);
				System.out.println("Cards: " + otherPlayerCards.get(seatingArrangement[i]));
				System.out.println("Possible: " + otherPlayerPossible.get(seatingArrangement[i]));
				System.out.println();
			}
		}
		
		out.println("New Data Acquired during this turn:");
		for (Data d : newInformation) {
			out.println(d);
		}
		
		out.println();
		
		if (solvedClues()) {
			out.println("Solved the mystery!");
			for (String c : characters) out.print(c + " ");
			for (String w : weapons) out.print(w + " ");
			for (String r : rooms) out.println(r);
			return;
		}
	}
	
	public static void main(String[] args) throws Exception {
		fileReader = new Scanner(new File("game.txt"));
		out = new PrintWriter(new FileWriter("output.txt"));
		
		setUpInput();
		
		simulateGame();
		
		
		fileReader.close();
		out.close();
	}
}