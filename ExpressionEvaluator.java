package solution;

import java.util.EmptyStackException;
import java.util.Scanner;
import java.util.Stack;
import java.util.regex.Pattern;



public class ExpressionEvaluator
{

    public static final Pattern UNSIGNED_DOUBLE =
        Pattern.compile("((\\d+\\.?\\d*)|(\\.\\d+))([Ee][-+]?\\d+)?.*?");
    public static final Pattern CHARACTER = Pattern.compile("\\S.*?");

    /**
     * Takes an infix expression and converts it to postfix.
    */ 
    
    public String toPostfix(String expression)
    {
     // ... other local variables
        Scanner input = new Scanner(expression);
        String next;
        char symbol;
        String postfixExpression = "";
        Stack<Character> stack = new Stack<Character>();

        while (input.hasNext())
        {
            
            if (input.hasNext(UNSIGNED_DOUBLE))
            {
                
                next = input.findInLine(UNSIGNED_DOUBLE);
                postfixExpression += next + " ";
            }
            else if (input.hasNext(CHARACTER))
            {
                next = input.findInLine(CHARACTER);
                symbol = next.charAt(0);
				
                if (symbol == '(')
                {
                    stack.push(symbol);
                }
                else if (symbol == '+' || symbol == '-'
                    || symbol == '*' || symbol == '/')
                {
                    while (!stack.isEmpty() && stack.peek() != '(' 
                        && priority(stack.peek()) >= priority(symbol))
                    {
                        postfixExpression += stack.pop() + " ";
                    }
                    stack.push(symbol);
                }
                
                else if (symbol == ')')
                {
                    while (!stack.isEmpty() && stack.peek() != '(')
                    {
                        postfixExpression += stack.pop() + " ";
                    }
                    stack.pop();
                }
                else
                {
                    throw new EmptyStackException();
                }
            }
        }

        
        while (!stack.isEmpty())
        {
            postfixExpression += stack.pop() + " ";
        }
        input.close();
        return postfixExpression;
    }


    /**
     * Evaluates a postfix expression and returns the result.
     */
    public double evaluate(String postfixExpression)
    {  
        Scanner input = new Scanner(postfixExpression);
        String next;
        char operator;
        double answer = Double.NaN;
        Stack<Double> stack = new Stack<Double>();

        while (input.hasNext())
        {
            if (input.hasNext(UNSIGNED_DOUBLE))
            {
                next = input.findInLine(UNSIGNED_DOUBLE);
                double n = Double.parseDouble(next);
                stack.push(n);
            }
            else
            {
                next = input.findInLine(CHARACTER);
                operator = next.charAt(0);
				
                if (operator == '*' && stack.size() > 1)
                {
                    double right = stack.pop();
                    double left = stack.pop();
                    answer = left * right;
                    stack.push(answer);
                }
                else if (operator == '/' && stack.size() > 1)
                {
                    double right = stack.pop();
                    double left = stack.pop();
                    answer = left / right;
                    stack.push(answer);
                }
                else if (operator == '-' && stack.size() > 1)
                {
                    double right = stack.pop();
                    double left = stack.pop();
                    answer = left - right;
                    stack.push(answer);
                }
                else if (operator == '+' && stack.size() > 1)
                {
                    double right = stack.pop();
                    double left = stack.pop();
                    answer = left + right;
                    stack.push(answer);
                }
                else
                {
                    return -9999;
                }
            }
        }
        input.close();
        return answer = stack.pop();
    }
  
  
    /**
     * helps determine priority
     */
    public int priority(Character c)
    {

        if (c == '*' || c == '/')
        {
            return 10;
        }
        else if (c == '+' || c == '-')
        {
            return 1;
        }
        else
        {
            return 0;

        }

    } 

}


