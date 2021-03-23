///////////////////////////////////////////////////////////////////////////
//
//         Write a Java program to determine that a given expression 
//          contains a set of correctly nested brackets of various 
//          kinds ( {,} [,] (,) ). That is, we want to check that:
//            - There are an equal number of right and left parentheses, 
//              curly brackets, and square brackets.
//            - Every right bracket is preceded by a corresponding 
//              left bracket.
//
//          Read a string expression using an input dialog interface.
//
//          For example, your program should print
//              true for [()]{}{[()()]()} and
//              false for [(]).
//
///////////////////////////////////////////////////////////////////////////
//
//          This checker must implement the following algorithm:
//
//          1. Make an empty stack.
//
//          2. Read symbols until the end of the source code file.
//                a.  If the symbol is an opening symbol,
//                      push it onto the stack.
//                b.  If it is a closing symbol, do the following:
//                    i.  If the stack is empty, report an error.
//                    ii. Otherwise, pop the stack.
//                          If the symbol popped is not the
//                          corresponding opening symbol,
//                          report an error.
//
//          3. At the end of the string, if the stack is not empty,
//             report an error.
//
///////////////////////////////////////////////////////////////////////////

/*

SAMPLE DATA
[()]{}{[()()]()} 
[(])
[]
((())

SAMPLE OUTPUT
GOOD
BAD
GOOD
BAD

*/
import java.io.*;
import java.util.*;

public class Stack1 {
	public void run() throws Exception {
		Scanner sc = new Scanner(new File("Stack1.in"));
		while(sc.hasNext()) {
			String s = sc.next();
			boolean ok = true;
			Stack<Character> st = new Stack<Character>();
			for (int i = 0; i<s.length(); i++) {
				char ch = s.charAt(i);
				if (ch == '(' || ch == '{' || ch == '[') st.add(ch);
				else {
					if (st.size() == 0)	ok = false;
					else if (ch == ')' && st.peek() == '(') st.pop();
					else if (ch == '}' && st.peek() == '{') st.pop();
					else if (ch == ']' && st.peek() == '[') st.pop();
					else ok = false;
				}
				if (!ok) break;
			}
			if (st.size()>0 || !ok) System.out.println("BAD");
			else System.out.println("GOOD");
		}
	}
	public static void main(String[] args) throws Exception {
		new Stack1().run();
	}
}