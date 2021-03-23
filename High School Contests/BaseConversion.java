import java.io.*;
import java.util.*;
import java.lang.*;
import java.math.*;

public class BaseConversion {
	public void run() throws Exception {
		Scanner sc = new Scanner(System.in);
		
		int test = sc.nextInt();
		outer:
		for (int q = 0; q<test; q++) {
			String n = sc.next();
			int a = sc.nextInt();
			int b = sc.nextInt();
			int c = Integer.parseInt(n,a);
			String d = Integer.toString(c,b);
			System.out.println(n + " in base " + a + " is " + d + " base " + b);
		}
	}
	public static void main (String[] args) throws Exception {
		new BaseConversion().run();
	}
}
