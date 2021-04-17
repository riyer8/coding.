import java.util.*;
import java.io.*;

//Lexington High School Problems: Multiply

public class Multiply {
    static long mod = (long) 1e9 + 7;

    public static void main(String[] args) throws IOException {
        FastScanner in = new FastScanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        int n = in.nextInt();
        long[] prod = new long[n + 2];
        prod[0] = 1;
        prod[1] = 1;

        for (int i = 2; i < n+2; i++) {
            if (i == 2 || i == 3) {
                prod[i] = in.nextLong();
            }
            else {
                prod[i] = (in.nextLong() * prod[i - 2]) % mod;
            }
        }

        int q = in.nextInt();
        for (int i = 0; i < q; i++) {
            int a = in.nextInt()+1;
            int b = in.nextInt()+1;
            if (a % 2 != b % 2) b--;
            long num = prod[b];
            long den = 1;
            if (a>3) den = pow(prod[a - 2], mod-2);
            System.out.println((num * den) % mod);
        }
        out.close();

    }
    static long pow(long a, long b) {
        long id = 1;
        while(b>0) {
            if((b&1)==1) {
                id = (id*a)%mod;
            }
            a = (a*a) %mod;
            b>>=1;
        }
        return id%mod;
    }

    static class FastScanner {
        BufferedReader br;
        StringTokenizer st;
        public FastScanner(InputStream i) {
            br = new BufferedReader(new InputStreamReader(i));
            st = new StringTokenizer("");
        }
        public String next() throws IOException {
            if (st.hasMoreTokens()) {
                return st.nextToken();
            }
            else
                st = new StringTokenizer(br.readLine());
            return next();
        }
        public int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
        public double nextDouble() throws IOException {
            return Double.parseDouble(next());
        }
        public long nextLong() throws IOException {
            return Long.parseLong(next());
        }
        public String nextLine() throws IOException {
            if (!st.hasMoreTokens()) {
                st = new StringTokenizer(br.readLine());
                return nextLine();
            }

            String ret = "";
            while (st.hasMoreTokens()) {
                ret += st.nextToken();
            }
            return ret;
        }
        public int[] nextIntArr(int size) throws IOException {
            int[] arr = new int[size];
            for (int i = 0; i < arr.length; i++) {
                arr[i] = nextInt();
            }
            return arr;
        }
        public long[] nextLongArr(int size) throws IOException {
            long[] arr = new long[size];
            for (int i = 0; i < arr.length; i++) {
                arr[i] = nextLong();
            }
            return arr;
        }
        public double[] nextDoubleArr(int size) throws IOException {
            double[] arr = new double[size];
            for (int i = 0; i < arr.length; i++) {
                arr[i] = nextDouble();
            }
            return arr;
        }
    }
}