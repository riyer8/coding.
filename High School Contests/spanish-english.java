import java.io.*;
import java.util.*;
import java.lang.*;
import java.math.*;

public class spanish-english{
	public void run() throws Exception {
		Scanner sc = new Scanner(new File("que.dat"));
		int n = sc.nextInt();
		Map<String, String> ok = new HashMap<String, String>();
		for (int i = 0; i<n; i++) {
			ok.put(sc.next(), sc.next());
		}
		sc.nextLine();
		while(sc.hasNextLine()) {
			String[] tot = sc.nextLine().split(" ");
			String ans = "";
			for (int i = 0; i<tot.length; i++) {
				ans+= ok.get(tot[i])+" ";
			}
			System.out.println(ans);
		}
	}
	public static void main (String[] args) throws Exception {
		new spanish-english().run();
	}
}