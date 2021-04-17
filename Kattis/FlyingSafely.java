import java.io.*;
import java.util.*;
import java.math.*;
import java.awt.*;
import java.lang.*;

//Flying Safely 1.7

public class Kattis {
    public void run() throws Exception {
        Scanner sc = new Scanner(System.in);
        
        int cases = sc.nextInt();
        for (int i = 0; i<cases; i++) {
        	int n = sc.nextInt();
        	int m = sc.nextInt();
        	int[][] adjmat = new int[n][n];
        	boolean[] visited = new boolean[n];
        	for (int j = 0; j<m; j++) {
        		int a = sc.nextInt()-1;
        		int b = sc.nextInt()-1;
        		adjmat[a][b] = 1;
        		adjmat[b][a] = 1;
        	}
        	visited[0] = true;
        	ArrayList<Integer> path = new ArrayList<Integer>();
        	path.add(0);
        	int count = 0;
        	while (!path.isEmpty()) {
        		int node = path.remove(0);
        		for (int j = 1; j < adjmat.length; j++) {
        			if (!visited[j] && adjmat[node][j] == 1) {
        				path.add(j);
        				count++;
        				visited[j] = true;
        			}
        		}
        	}
        	System.out.println(count);
        }
        
    }
    public static void main(String[] args) throws Exception {
        new Kattis().run();
    }
}