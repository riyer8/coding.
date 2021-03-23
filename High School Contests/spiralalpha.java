import java.io.*;
import java.util.*;
import java.lang.*;
import java.math.*;

public class spiralalpha {
	public void run() throws Exception {
		FastScanner sc = new FastScanner();
		String[] s = sc.nextLine().split(" ");
		for (int i = 0; i<s.length; i++) {
			int n = Integer.parseInt(s[i]);
			if (n == 0) return;
			int len = (int)Math.ceil(Math.sqrt(n));
			char[][] mat = new char[len][len];
			char x = 'A';
			boolean ok = false;
			for (int j = len-1; j>=0; j--) {
				for (int k = len-j-1; k<j; k++) {
					if (!ok) mat[j][k] = x++;
					else mat[j][k] = '*';
					if (x>='A'+n) {
						x = '*'; ok = true;
					}
				}
				for (int k = j; k>len-j-1; k--) {
					if (!ok)mat[k][j] = x++;
					else mat[k][j] = '*';
					if (x>='A'+n) {
						x = '*'; ok = true;
					}
				}
				for (int k = j; k>=len-j; k--) {
					if (!ok)mat[len-j-1][k] = x++;
					else mat[len-j-1][k] = '*';
					if (x>='A'+n) {
						x = '*'; ok = true;
					}
				}
				for (int k = len-j-1; k<j; k++) {
					if (!ok)mat[k][len-j-1] = x++;
					else mat[k][len-j-1] = '*';
					if (x>='A'+n) {
						x = '*'; ok = true;
					}
				}
			}
			if (len%2 == 1 && !(Math.sqrt(n) == len)) {
				mat[len/2][len/2] = '*';
			}
			else mat[len/2][len/2] = (char)('A'+n);
			for (int j =0 ; j<len; j++) {
				for (int k = 0; k<len; k++) {
					System.out.print(mat[j][k]+ " ");
				}
				System.out.println();
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
		new spiralalpha().run();
	}
}