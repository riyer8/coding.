import java.io.*;
import java.util.*;
import java.lang.*;
import java.math.*;

public class adagears {
	static boolean[] visited;
	static ArrayList<Integer>[] adjList;
	static int[] names;
	static boolean ok;
	public static void floodfill(int node, int flag) { 
		if (names[node] != -1) { //check for inconsistencies of names
			if (flag != names[node]) { //entire selection is false if both names don't match.
				ok = false;
			}
			return;
		}
		names[node] = flag; //give name if no name is already given.
		for (int i = 0; i<adjList[node].size(); i++) {
			floodfill(adjList[node].get(i),(flag+1)%2); //clockwise or counterclockwise
		}
	}
	public void run() throws Exception {
		FastScanner sc = new FastScanner();
		
		int n = sc.nextInt();
		int k = sc.nextInt();
		adjList = new ArrayList[n];
		visited = new boolean[n];
		names = new int[n];
		ok = true;
		for (int i = 0; i<n; i++) {
			adjList[i] = new ArrayList<Integer>();
			visited[i] = false;
			names[i] = -1;
		}
		for (int i = 0; i<k; i++) {
			int a = sc.nextInt()-1;
			int b = sc.nextInt()-1;
			adjList[a].add(b); //undirected map
			adjList[b].add(a);
		}
		floodfill(0, 0);
		if (!ok) System.out.println("Help me Ada");
		else {
			System.out.println("OK");
			for (int i = 0; i<names.length; i++) {
				if (names[i] == 0) System.out.println("CW");
				else if (names[i] == 1) System.out.println("CCW");
				else System.out.println("ST");
			}
		}
	}
	public class Pair implements Comparable<Pair>{
		int num, times;
		public Pair(int a, int b) {
			num = a;
			times = b;
		}
		public int compareTo(Pair a) {
			if (num == a.num) return Integer.compare(a.times, times);
			return Integer.compare(num, a.num);
		}
		public String toString() {
			return num + " " + times;
		}
	}
	public int[] shuffleArray(int[] arr) {
        int n = arr.length;
        Random rnd = new Random();
        for(int i=0; i<n; ++i){
        	int tmp = arr[i];
            int randomPos = i + rnd.nextInt(n-i);
            arr[i] = arr[randomPos];
            arr[randomPos] = tmp;
        }
        return arr;
	}
	public boolean isPrime(long n) {
		for (long i = 2; i<=Math.sqrt(n); i++) {
			if (n%i == 0) return false;
		}
		return true;
	}
	public long gcd(long a, long b)   {
		if (a == b) return a;
		if (a > b) return gcd(a-b, b);
		return gcd(a, b-a);
    }
	public ArrayList<Pair> together(String s) {
		ArrayList<Pair> cost = new ArrayList<Pair>();
		char num = '!'; int times = 0;
		for (int i = 0; i<s.length(); i++) {
			if (i == 0) {
				num = s.charAt(i);
				times = 1;
			}
			else if (num != s.charAt(i)) {
				cost.add(new Pair(num,times));
				num = s.charAt(i);
				times = 1;
			}
			else times++;
		}
		cost.add(new Pair(num,times));
		return cost;
	}
	static class FastScanner {
		public BufferedReader reader;
		public StringTokenizer tokenizer;
 
		public FastScanner() {
			reader = new BufferedReader(new InputStreamReader(System.in), 32768);
			tokenizer = null;
		}
 
		public String next() {
			while (tokenizer == null || !tokenizer.hasMoreTokens()) {
				try {
					tokenizer = new StringTokenizer(reader.readLine());
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			}
			return tokenizer.nextToken();
		}
 
		public int nextInt() {
			return Integer.parseInt(next());
		}
 
		public long nextLong() {
			return Long.parseLong(next());
		}
 
		public double nextDouble() {
			return Double.parseDouble(next());
		}
 
		public String nextLine() {
			try {
				return reader.readLine();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
 
	}
	public static void main (String[] args) throws Exception {
		new adagears().run();
	}
}