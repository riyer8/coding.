import java.io.*;
import java.util.*;
import java.lang.*;
import java.math.*;

//Lexington High School Problems: Waiting
 
public class Waiting {
	public void run() throws Exception {
		FastScanner sc = new FastScanner();
		
		int n = sc.nextInt();
		int m = sc.nextInt();
		int l = sc.nextInt();
		ArrayDeque<Integer> qu = new ArrayDeque<Integer>(); //ppl outside
		int max = 0;
		for (int i =0 ; i<n; i++) {
			qu.add(sc.nextInt());
			if (i == n-1) {
				max = qu.peekLast()+m;
			}
		}
		int small = qu.peekFirst();
		ArrayDeque<Integer> room = new ArrayDeque<Integer>(); //ppl in room
		int[] times = new int[max];
		int people = 0;
		int smalltime = qu.peekFirst()+m;
		for (int i = 0; i<n; i++) {
			if (room.size()>1 && qu.size()>1 && room.peekFirst()+m<qu.peekFirst()) {
				room.poll();
			}
			if (room.size()<l) {
				int x = qu.poll();
				room.add(x);
				for (int j = x; j<x+m; j++) {
					times[j]++;
				}
			}
			else {
				for (int j = Math.max(room.peekFirst()+m,qu.peek()); j<Math.max(room.peekFirst()+2*m,qu.peek()+m); j++  ) {
					times[j]++;
				}
				room.add(room.pollFirst()+m);
				qu.poll();
			}
		}
		int count = 0;
		for (int i = small; i<max; i++) {
			if (times[i] == 0) {
				count++;
			}
		}
		System.out.println(count);
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
		new Waiting().run();
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