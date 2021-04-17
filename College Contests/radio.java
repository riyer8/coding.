import java.io.*;
import java.util.*;
import java.lang.*;
import java.math.*;

//UCF 2020 Radio

public class radio {
	public void run() throws Exception {
		FastScanner sc = new FastScanner();
		int d = sc.nextInt();
		for (int i = 0; i<d; i++) {
			int n = sc.nextInt();
			int p = sc.nextInt();
			int s = sc.nextInt();
			Map<Double,Integer> map = new HashMap<>();
			LinkedList<Pair>[] adj = new LinkedList[n];
			for (int j = 0; j<n; j++) {
				map.put(sc.nextDouble(), j);
				adj[j] = new LinkedList<Pair>();
			}
			double[] present = new double[p];
			Set<Integer> set = new HashSet<Integer>();
			for (int j = 0; j<p; j++) {
				present[j] = sc.nextDouble();
				set.add(map.get(present));
			}
			for (int j = 0; j<p-1; j++) {
				for (int q = 0; q<n; q++) {
					if (q!=j) {
						adj[q].add(new Pair(map.get(present[j]),1));
					}
					
				}
			}
			for (int j  = 0; j<n-1; j++) {
				if (set.contains(j)) continue;
				for (int q = j+1; q<n; q++) {
					if (set.contains(q)) continue;
					adj[j].add(new Pair(q,Math.min(q-j,n-q+j)));
					adj[q].add(new Pair(j,Math.min(q-j,n-q+j)));
				}
			}
			System.out.println("Day #" + (i+1)+":");
			for (int j = 0; j<s; j++) {
				int a = map.get(sc.nextDouble());
				int b = map.get(sc.nextDouble());
				int[] best = new int[n];
				Arrays.fill(best, Integer.MAX_VALUE);
				PriorityQueue<Pair> pq = new PriorityQueue<>();
				pq.add(new Pair(a,0));
				while(!pq.isEmpty()) {
					Pair pv = pq.poll();
					int li = pv.a, dist = pv.b;
					if (best[li]<=dist) continue;
					best[li] = dist;
					for (Pair nx: adj[li]) {
						pq.add(new Pair(nx.a, dist+nx.b));
					}
				}
				System.out.println(best[b]);
			}
			System.out.println();
		}
	}
	public class Pair implements Comparable<Pair> {
		int a, b;
		public Pair(int a, int b) {
			this.a = a;
			this.b = b;
		}
		public int compareTo(Pair p) {
			return Double.compare(b, p.b);
		}
		public String toString() {
			return a + " " + b;
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
		new radio().run();
	}
}