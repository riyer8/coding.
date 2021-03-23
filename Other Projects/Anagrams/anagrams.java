import java.util.*;
import java.io.*;
import java.text.*;
import java.math.*;
import java.lang.*;

// Problem Statement: http://web.stanford.edu/class/archive/cs/cs106x/cs106x.1174/assn/anagrams.html
// used backtracking to find the possible anagrams

public class anagrams {
	static ArrayList<ArrayList<String>> workingCombinations = new ArrayList<ArrayList<String>>();
	static boolean[] used;
	static int maxWords;
	
	public static void findAnagrams(ArrayList<Character> charList, int charsUsed, Set<String> dict, ArrayList<String> list) {
		if (list.size() > maxWords) return;
		if (charList.size() == charsUsed) {
			ArrayList<String> found = (ArrayList) list.clone();
			workingCombinations.add(found);
			return;
		}
		
		for (String word: dict) {
			if (word.length() + charsUsed > charList.size()) continue;
			
			ArrayList<Integer> idx = new ArrayList<Integer>();
			for (int i = 0; i < word.length(); i++) {
				for (int j = 0; j < charList.size(); j++) {
					if (word.charAt(i) == charList.get(j) && !used[j]) {
						idx.add(j);
						used[j] = true;
						break;
					}
				}
			}
			
			if (idx.size() == word.length()) {
				System.out.println(word + " " + Arrays.toString(used));
				for (int i = 0; i < idx.size(); i++) {
					used[idx.get(i)] = true;
				}
				list.add(word);
				
				findAnagrams(charList, charsUsed + word.length(), dict, list);
				
				// backtrack
				list.remove(list.size() - 1);
			}
			
			for (int i = 0; i < idx.size(); i++) {
				used[idx.get(i)] = false;
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		System.out.println("Welcome to the CS 106x Anagram Solver!");
		
		System.out.println("Dictionary file name: ");
		String fileName = sc.next();
		Scanner fileReader = new Scanner(new File(fileName));
		Set<String> wordsInDictionary = new HashSet<String>();
		while(fileReader.hasNextLine()) wordsInDictionary.add(fileReader.nextLine());
		
		System.out.println("Phrase to scramble: ");
		String scrambledPhrase = sc.next();
		scrambledPhrase.toLowerCase();
		
		ArrayList<Character> charsToUse = new ArrayList<Character>();
		for (int i = 0; i<scrambledPhrase.length(); i++) {
			if (Character.isLowerCase(scrambledPhrase.charAt(i))) 
				charsToUse.add(scrambledPhrase.charAt(i));
		}
		
		System.out.println("Max words to include: ");
		maxWords = sc.nextInt();
		System.out.println(wordsInDictionary);
		
		used = new boolean[charsToUse.size()];
		
		ArrayList<String> curListOfWords = new ArrayList<String>();
		findAnagrams(charsToUse, 0, wordsInDictionary, curListOfWords);
		
		for (ArrayList<String> combos : workingCombinations) {
			System.out.println(combos);
		}
		System.out.println(workingCombinations.size());
	}	
}
