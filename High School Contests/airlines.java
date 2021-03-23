import java.io.*;
import java.util.*;
import java.lang.*;
import java.math.*;

//Used a variation of Union-Find

public class airlines {
	Map<String, Integer> map;
	
	public boolean connected(String p, String q) { 
		return map.get(p) == map.get(q);
	}
	public void union(String p, String q) {
		int pid = map.get(p);
		int qid = map.get(q);
		for (String s: map.keySet()) {
			if (map.get(s) == pid) map.put(s,qid);
		}
	}
	
	public void run() throws Exception {
		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();
		map = new HashMap<String, Integer>();
		for (int i =0; i<n; i++) {
			map.put(sc.next(),i);
		}
		n = sc.nextInt();
		for (int i = 0; i<n; i++) {
			union(sc.next(), sc.next());
		}
		n = sc.nextInt();
		for (int i = 0; i<n; i++) {
			if (connected(sc.next(),sc.next())) System.out.println("PARTNERS");
			else System.out.println("No miles for you");
		}
	}
	public static void main (String[] args) throws Exception {
		new airlines().run();
	}
}