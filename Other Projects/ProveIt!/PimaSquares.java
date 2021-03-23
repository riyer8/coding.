import java.io.*;
import java.util.*;

//The Pima Squares

public class PimaSquares {
	public void run() throws Exception {
		FastScanner sc = new FastScanner();
		
		for (int q = 0; q<20; q++) {
			int n = sc.nextInt();
			String s = Integer.toBinaryString(n);
			boolean[] ok = new boolean[2*s.length()-1];
			for (int i = 0; i<s.length(); i++) {
				int num = s.charAt(i)-48;
				if (num == 1) {
					for (int j = 0; j<s.length(); j++) {
						if (s.charAt(j)-48 == 1) {
							ok[i+j] = !ok[i+j];
						}
					}
				}
			}
			System.out.println(Arrays.toString(ok));
			int sum = 0;
			for (int i = 0; i<ok.length; i++) {
				if (ok[i]) {
					sum+=Math.pow(2, ok.length-i-1);
					//System.out.println(sum);
				}
				
			}
			System.out.println(sum);
		}
		
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
		new PimaSquares().run();
	}
}