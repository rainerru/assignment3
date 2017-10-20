import java.lang.ArithmeticException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class calculator
{

	public double evaluate ( String input )
	{

		Pattern pattern = Pattern.compile("([(\\d\\.\\d)\\d\\+\\*\\-\\/])+");
		Pattern numberPattern = Pattern.compile("[(\\d\\.\\d)\\d)]+");
		Matcher matcher = pattern.matcher(input);
		Matcher numberMatcher;
		customStack<Double> stack = new customStack<Double>();
		String currentExpression;

		while ( matcher.find() )
		{
				currentExpression = matcher.group(0);
				numberMatcher = numberPattern.matcher(currentExpression);
				if ( numberMatcher.matches() ) stack.push( Double.parseDouble(currentExpression) );
				else if ( stack.peek() == null || stack.peekBelow() == null )
				{
					ArithmeticException e = new ArithmeticException(
						"Input is not a valid arithmetic expression in Reverse Polish notation: " + input);
					throw e;
				}	else if ( currentExpression.equals("+") ) stack.push( stack.pop() + stack.pop() );
				else if ( currentExpression.equals("*") ) stack.push( stack.pop() * stack.pop() );
				else if ( currentExpression.equals("-") ) stack.push( stack.pop() - stack.pop() );
				else if ( currentExpression.equals("/") ) stack.push( stack.pop() / stack.pop() );
    }

		if ( stack.peek() == null || !(stack.peekBelow() == null) )
		{
			ArithmeticException e	= new ArithmeticException(
					"Input is not a valid arithmetic expression in Reverse Polish notation: " + input);
			throw e;
		}
		return stack.pop();

	}












}
