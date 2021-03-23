import java.util.*;

public class PostFixSolver_Runner
{
   public static void main(String[] args)
   {
   		//use the tutorial from  http://www.cs.nthu.edu.tw/~wkhon/ds/ds10/tutorial/tutorial2.pdf
   		//there is a copy of the pdf in the folder 
   		
      System.out.println( PostFixSolver.solve( "5 6 +" ) );     												 	//outs 11
      System.out.println( PostFixSolver.solve( "8 6 - 3 4 + *" ) );   											//outs 14
      System.out.println( PostFixSolver.solve( "2 2 * 3 3 * +" ) );    											//outs 13
      System.out.println( PostFixSolver.solve( "44 15 9 3 2 * - / - 1 2 + + 9 +" ) );   					//outs 51
      System.out.println( PostFixSolver.solve( "12 8 7 - 1 + * 4 6 + 2 / -" ) );   							//outs 19
      System.out.println( PostFixSolver.solve( "3 8 + 5 + 10 - 3 / 4 + 5 + 7 -" ) );   					//outs 4
      System.out.println( PostFixSolver.solve( "16 8 10 7 - 3 + 2 * + 10 - 4 2 - 3 * 1 - / -" ) );   	//outs 14
      
           
      //add more test cases - go online and find more test cases
      
      
      //read in an infix expression and convert it to postfix
      //solve your new postfix expression using the solve method
      
      //1 + 2 * 4 / 5 - 7 + 3 / 6   [ infix ]
	  	//1 2 4 * 5 / + 7 - 3 6 / +   [ postfix ]
	  	System.out.println( PostFixSolver.convertToPostfix( "1 + 2 * 4 / 5 - 7 + 3 / 6" ) );   								//outs 1 2 4 * 5 / + 7 - 3 6 / +
      System.out.println( PostFixSolver.solve( PostFixSolver.convertToPostfix( "1 + 2 * 4 / 5 - 7 + 3 / 6" ) ) );   	//outs -5
      
      
	  	System.out.println( PostFixSolver.convertToPostfix( "1 + 2 * 4 / 5 - 7 + 3 / 6 + 9" ) );   										//outs 1 2 4 * 5 / + 7 - 3 6 / + 9 +
      System.out.println( PostFixSolver.solve( PostFixSolver.convertToPostfix( "1 + 2 * 4 / 5 - 7 + 3 / 6 + 9" ) ) );   	//outs 4   
      
      //add more test cases - go online and find more test cases         
   }
}
