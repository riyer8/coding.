import java.io.*;
import java.util.*;
import java.lang.*;
import java.math.*;

//UCF 2020 Suburban

public class suburban {
	public void run() throws Exception {
		FastScanner sc = new FastScanner();
		
		int n  = sc.nextInt();
		for (int i = 0;i<n; i++) {
			long x = sc.nextLong();
			int ans1 = 0;
			int ans2 = 0;
			for (int j= 0; j<=Math.sqrt(x); j++) {
				if (Math.sqrt(x-j*j) == (int)Math.floor(Math.sqrt(x-j*j))) {
					ans1 = (int)Math.floor(Math.sqrt(x-j*j));
					ans2 = j;
				}
			}
			if (ans1 == 0 && ans2 == 0) System.out.println(-1);
			else System.out.println(ans1+ans2);
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
		new suburban().run();
	}
}