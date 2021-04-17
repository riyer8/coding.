import java.io.*;
import java.util.*;
import java.lang.*;
import java.math.*;

//Lexington High School Problems: Energy

public class energy {
	public void run() throws Exception {
		FastScanner sc = new FastScanner();
		
		int n = sc.nextInt();
		int m = sc.nextInt();
		int t = sc.nextInt();
		int[] cost = new int[n];
		for (int i = 0; i<n; i++) {
			cost[i] = sc.nextInt();
		}
		LinkedList<Pair>[] adj = new LinkedList[n];
		for (int i = 0; i<n; i++) {
			adj[i] = new LinkedList<>();
		}
		for (int i =0; i< m; i++) {
			int a = sc.nextInt()-1, b = sc.nextInt()-1, c = sc.nextInt();
			adj[a].add(new Pair(b,c));
			adj[b].add(new Pair(a,c));
		}
		int[] best = new int[n];
		Arrays.fill(best, Integer.MAX_VALUE);
		PriorityQueue<Pair> pq = new PriorityQueue<>();
		pq.add(new Pair(0,0));
		while(!pq.isEmpty()) {
			Pair p = pq.poll();
			int i = p.a, dist = p.b;
			if (best[i]<=dist) continue;
			best[i] = dist;
			for (Pair nx: adj[i]) {
				pq.add(new Pair(nx.a, dist+nx.b));
			}
			for (int b: best) System.out.println();
		}
		
	}
	public class Pair implements Comparable<Pair>{
		int a, b;
		public Pair(int num, int times) {
			a = num;
			b = times;
		}
		public int compareTo(Pair ok) {
			if (a == ok.a) return Integer.compare(b, ok.b);
			return Integer.compare(a, ok.a);
		}
		public String toString() {
			return b + " " + b;
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
	public static long gcd(long a, long b)   {
		if (a == b) return a;
		if (a > b) return gcd(a-b, b);
		return gcd(a, b-a);
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
		new energy().run();
	}
	public void shuffleArray(long[] arr) {
        int n = arr.length;
        Random rnd = new Random();
        for(int i=0; i<n; ++i){
            long tmp = arr[i];
            int randomPos = i + rnd.nextInt(n-i);
            arr[i] = arr[randomPos];
            arr[randomPos] = tmp;
        }   
	}
}