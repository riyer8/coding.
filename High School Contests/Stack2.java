///////////////////////////////////////////////////////////////////////////
//
//          Write a program that will check a text in a markup language
//          (such as HTML or XML) to see if it is well-formed.
//
//          The list text is a list of String. Some strings are
//          markup tags, such as "<TT>" or "</TT>", and other strings
//          are ignored for checking purposes.
//
//          You may assume that any string that begins with < is a tag.
//          Every opening tag should be matched by a closing tag,
//          indicated by /.
//
//          We will assume that tag pairs may be nested to a
//          depth up to 100.
//
//          If a list is well-formed, return true;
//          If there is a tag out of place,
//              print a message with the offending tag,
//              its numeric position in the list (starting with 0),
//              the correct tag that was expected,
//              and return false.
//
//
///////////////////////////////////////////////////////////////////////////
//

/*SAMPLE INPUT - DATA

<HTML>
<HEAD>
<TITLE>HTML Checker File</TITLE>
</HEAD>
<BODY>
<H2>One Liner</H2>
If we aren't supposed to eat animals, why are they made with meat?
</BODY>
</HTML>

SAMPLE OUTPUT

PASS

*/
//
///////////////////////////////////////////////////////////////////////////
import java.io.*;
import java.util.*;

public class Stack2 {
	public void run() throws Exception {
		Scanner sc = new Scanner(new File("Stack1.in"));
		Stack<String> stack = new Stack<String>();
		while(sc.hasNext()) {
			String s = sc.next();
			int fbr = -1;
			int bbr = -1;
			for (int i = 0; i<s.length(); i++) {
				if (s.charAt(i) == '<') {
					fbr = i;
				}
				else if (s.charAt(i) == '>') {
					String tag = s.substring(fbr, i+1);
					if (tag.charAt(1) == '/') {
						if(stack.size() > 0 && tag.substring(2,tag.length()-1).equals(stack.peek())) stack.pop();
						else {
							if (stack.size()<1) {
								System.out.println("Printed: " + tag);
								System.out.println("Expected Tag: <"+tag.substring(2) + ">");
								System.out.println("FALSE");
							}
							else {
								System.out.println("Printed: " + tag);
								System.out.println("Expected Tag: </" + stack.peek() + ">");
								System.out.println("FALSE");
							}
						}
					}
					else {
						stack.add(tag.substring(fbr+1,i));
					}
					bbr = -1;
					fbr = -1;
				}
			}
		}
		System.out.println("PASS");
	}
	public static void main(String[] args) throws Exception {
		new Stack2().run();
	}
}
