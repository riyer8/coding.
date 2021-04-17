import java.io.*;
import java.util.*;
import java.lang.*;
import java.math.*;

//UCF 2020 Sniper

public class sniper {
	public void run() throws Exception {
		FastScanner sc = new FastScanner();
		
		int n = sc.nextInt();
		for (int i = 0; i<n;i++) {
			long t = sc.nextInt();
			long k = sc.nextInt();
			Map<Long, Long> map = new HashMap<Long, Long>();
			long possum = 0;
			long negsum = 0;
			long pos = 0;
			long neg = 0;
			for (long j = 0; j<t; j++) {
				long h = sc.nextLong();
				if (h>0) {
					possum+=h;
					pos++;
				}
				else {
					negsum+=Math.abs(h);
					neg++;
				}
				if (pos>neg) map.put(j+1, pos-neg);
			}
			long sum = possum-negsum;
			for (long j = 0; j<k; j++) {
				long b = sc.nextLong();
				if (map.containsKey(b)) sum+=map.get(b);
			}
			System.out.println(sum);
		}
	}
	public class Pair implements Comparable<Pair>{
		int num, times;
		public Pair(int a, int b) {
			num = a;
			times = b;
		}
		public int compareTo(Pair a) {
			if (num == a.num) return times-a.times;
			return num-a.num;
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
		new sniper().run();
	}
}