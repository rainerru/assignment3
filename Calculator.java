import java.lang.ArithmeticException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calculator
{

	/**
	 * If the input is an expression in Reverse Polish notation, CustomStacks are being
	 * used to calculate said expression.
	 *
	 * @param	input	the string which will be evaluated, if it is in the right notation
	 * @return			the result of the beforementioned evaluation
	 */
	public double evaluate ( String input )
	{

		Pattern pattern = Pattern.compile("([(\\d\\.\\d)\\d\\+\\*\\-\\/])+");
		Pattern numberPattern = Pattern.compile("[(\\d\\.\\d)\\d)]+");
		Matcher matcher = pattern.matcher(input);
		Matcher numberMatcher;
		CustomStack<Double> stack = new CustomStack<Double>();
		String currentExpression;

		// Strategy: Go through all groups in the string
		while ( matcher.find() )
		{
				currentExpression = matcher.group(0);
				numberMatcher = numberPattern.matcher(currentExpression);
				// If it is a number, push it onto the stack
				if ( numberMatcher.matches() ) stack.push( Double.parseDouble(currentExpression) );
				else if ( stack.peek() == null || stack.peekBelow() == null )
				{
					ArithmeticException e = new ArithmeticException(
						"Input is not a valid arithmetic expression in Reverse Polish notation: " + input);
					throw e;
				}
				// If it is an operation, aplly it to the next two numbers on the stack
				else if ( currentExpression.equals("+") ) stack.push( stack.pop() + stack.pop() );
				else if ( currentExpression.equals("*") ) stack.push( stack.pop() * stack.pop() );
				else if ( currentExpression.equals("-") ) stack.push( stack.pop() - stack.pop() );
				else if ( currentExpression.equals("/") ) stack.push( stack.pop() / stack.pop() );
    }

		// Eventually, only one number (the result) should remain on the stack. If not,
		// something is wrong.
		if ( stack.peek() == null || !(stack.peekBelow() == null) )
		{
			ArithmeticException e	= new ArithmeticException(
					"Input is not a valid arithmetic expression in Reverse Polish notation: " + input);
			throw e;
		}
		return stack.pop();

	}

}
