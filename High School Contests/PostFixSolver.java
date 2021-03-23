import java.util.*;

public class PostFixSolver
{
	public static int solve( String s )
	{
		String[] arr = s.split(" ");
		int value = 0;
		Stack<Integer> stack = new Stack<Integer>();
		for (int i = 0 ; i<arr.length; i++) {
			if (arr[i].charAt(0) == '+') {
				int a = stack.pop();
				int b = stack.pop();
				value = a+b;
				stack.add(value);
			}
			else if (arr[i].charAt(0) == '-') {
				int a = stack.pop();
				int b = stack.pop();
				value = b-a;
				stack.add(value);
			}
			else if (arr[i].charAt(0) == '/') {
				int a = stack.pop();
				int b = stack.pop();
				value = b/a;
				stack.add(value);
			}
			else if (arr[i].charAt(0) == '*') {
				int a = stack.pop();
				int b = stack.pop();
				value = b*a;
				stack.add(value);
			}
			else {
				stack.add(Integer.parseInt(arr[i]));
			}
		}
		return value;
	}
	
	public static String convertToPostfix( String s )
	{
		String[] arr = s.split(" ");
		String value = "";
		Stack<Character> stack = new Stack<Character>();
		for (int i = 0 ; i<arr.length; i++) {
		//	System.out.println(value);
		//	System.out.println(stack);
			if (arr[i].charAt(0) == '+') {
				while(!stack.empty()) {
					value+=stack.pop()+" ";
				}
				stack.add(arr[i].charAt(0));
			}
			else if (arr[i].charAt(0) == '-') {
				while(!stack.empty()) {
					value+=stack.pop()+" ";
				}
				stack.add(arr[i].charAt(0));
			}
			else if (arr[i].charAt(0) == '/') {
				while(!stack.empty()) {
					if (stack.peek() == '+' || stack.peek() == '-') {
						break;
					}
					else {
						value+=stack.pop()+" ";
					}
				}
				stack.add(arr[i].charAt(0));
			}
			else if (arr[i].charAt(0) == '*') {
				while(!stack.empty()) {
					if (stack.peek() == '+' || stack.peek() == '-') {
						break;
					}
					else {
						value+=stack.pop()+" ";
					}
				}
				stack.add(arr[i].charAt(0));
			}
			else {
				value+=arr[i] + " ";
			}
		}
		while(!stack.empty()) value+=stack.pop() + " ";
		return value.trim();
	}	
}
