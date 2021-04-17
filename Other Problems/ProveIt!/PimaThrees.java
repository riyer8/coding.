import java.io.*;
import java.util.*;

//Pima Multiplication by 3

public class PimaThrees {
	public void run() throws Exception {
		FastScanner sc = new FastScanner();
		
		for (int q = 0; q<20;q++) {
			int n = sc.nextInt();
			String s = Integer.toBinaryString(n);
			String st = s+"0";
			boolean[] ok = new boolean[st.length()];
			ok[0] = true;
			for (int i = 0; i<s.length(); i++) {
				int schar = s.charAt(i)-48;
				int stchar = st.charAt(i+1)-48;
				if (schar == stchar) {
					ok[i+1] = false;
				}
				else ok[i+1] = true;
			}
			System.out.println(Arrays.toString(ok));
			int sum = 0;
			for (int i = 0; i<ok.length; i++) {
				if (ok[i]) {
					sum+=Math.pow(2, ok.length-i-1);
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
		new PimaThrees().run();
	}
}