import java.io.*;
import java.util.*;

//Gnomes and their Generosity -- sharings

public class Gnome {
	int[] arr;
	public boolean ex() {
		boolean change = false;
		int[] ne = new int[4];
		ne[0] = arr[0]; ne[1] = arr[1]; ne[2] = arr[2]; ne[3] = arr[3];
		if (arr[0] >=2) {
			int val = arr[0]/2;
			ne[0] -=val*2;
			ne[1] += val;
			ne[2] +=val;
			change = true;
		}
		if (arr[1] >=3) {
			int val = arr[1]/3;
			ne[1] = ne[1]-val*3;
			ne[0] = ne[0] + val;
			ne[2] = ne[2]+val;
			ne[3] = ne[3]+val;
			change = true;
		}
		if (arr[3] >=2) {
			int val = arr[3]/2;
			ne[3] = ne[3]-val*2;
			ne[1] = ne[1] + val;
			ne[2] = ne[2]+val;
			change = true;
		}
		if (arr[2] >=3) {
			int val = arr[2]/3;
			ne[2] = ne[2]-val*3;
			ne[0] = ne[0] + val;
			ne[1] = ne[1]+val;
			ne[3] = ne[3]+val;
			change = true;
		}
		arr = ne;
		return change;
	}
	public void run() throws Exception {
		FastScanner sc = new FastScanner();
		for (int q = 0; q<56; q++) {
			int a = sc.nextInt(), b = sc.nextInt(), c = sc.nextInt();
			int d = sc.nextInt();
			arr =  new int[4];
			arr[0] = a; arr[1] = b; arr[2] = c; arr[3] = d;
			for (int i = 0; i<100; i++ ) {
				if (!ex()) {
					System.out.println("STOP!!!");
					break;
				}
				if (arr[0] == a && arr[1] == b && arr[2] == c && arr[3] == d) {
					System.out.println("YES");
					break;
				}
				System.out.println(Arrays.toString(arr));
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
		new Gnome().run();
	}
}
