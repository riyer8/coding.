import java.io.*;
import java.util.*;
import java.lang.*;
import java.math.*;

#used an adjacency matrix and union-find

public class AirlinesMatrix {
	Map<String, Integer> map;
	int[][] adjmat;
	public boolean connected(int p, int q) { 
		return adjmat[p][q] == 1;
	}
	public void union(int p, int q) {
		adjmat[p][q] = 1;
		adjmat[q][p] = 1;
		for (int i = 0; i<adjmat[p].length; i++) {
			if (adjmat[p][i] == 1 && adjmat[q][i] == 0) {
				union(i,q);
			}
		}
	}
	
	public void run() throws Exception {
		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();
		map = new HashMap<String, Integer>();
		for (int i =0; i<n; i++) {
			map.put(sc.next(),i);
		}
		adjmat = new int[map.size()][map.size()];
		for (int i = 0; i<n; i++) {
			adjmat[i][i] = 1;
		}
		n = sc.nextInt();
		for (int i = 0; i<n; i++) {
			union(map.get(sc.next()), map.get(sc.next()));
		}
		n = sc.nextInt();
		for (int i = 0; i<n; i++) {
			if (connected(map.get(sc.next()),map.get(sc.next()))) System.out.println("PARTNERS");
			else System.out.println("No miles for you");
		}
	}
	public static void main (String[] args) throws Exception {
		new AirlinesMatrix().run();
	}
}