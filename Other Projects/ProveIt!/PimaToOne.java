import java.io.*;
import java.util.*;
 
//Pima - piecewise function... to 1?

public class PimaToOne {
	public void run() throws Exception {
		FastScanner sc = new FastScanner();
		
		for (int q = 0; q<20; q++) {
		int n = sc.nextInt();
		while(n!=1) {
			if (n%2 == 0) n = n/2;
			else {
				String s = Integer.toBinaryString(n);
				String st = s+"0";
				s = '0'+s;
				String sst = "";
			//	System.out.println(s);
			//	System.out.println(st);
				for (int i = s.length()-1; i>=0; i--) {
					int a = (s.charAt(i)-48)^(st.charAt(i)-48);
					sst = ""+a + sst;
				}
			//	System.out.println(sst);
				int a = (s.charAt(sst.length()-1)-48)^1;
				sst = sst.substring(0,s.length()-1)+a;
				n = Integer.parseInt(sst,2);
			}
			System.out.println(n);
		}
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
		new PimaToOne().run();
	}
}