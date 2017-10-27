import java.lang.ArithmeticException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calculator
{

	public double evaluate ( String input ) // throws Exception
	{
		CustomStack<String> expression = getGroups( input );
		checkIfValidArithmeticExpression( expression );
		expression = expression.reverse();
		return evaluate(expression);
	}

	public double evaluate ( CustomStack<String> stack )
	{
		if ( !stack.isEmpty() )
		{
			String s = stack.pop();
			if ( isNumber(s) ) return Double.parseDouble(s);
			else return useOperation(s,evaluate(stack),evaluate(stack));
		}
		else throw new ArithmeticException(
			"Input is not a valid arithmetic expression in Reverse Polish notation");
	}

	public CustomStack<String> getGroups ( String input )
	{
		Pattern pattern = Pattern.compile("([(\\d\\.\\d)\\d\\+\\*\\-\\/ ])+");
		Matcher matcher = pattern.matcher(input);

		if ( !matcher.matches() )
		{
			ArithmeticException e = new ArithmeticException(
			"Input is not a valid arithmetic expression in Reverse Polish notation: " + input);
			throw e;
		}

		pattern = Pattern.compile("([(\\d\\.\\d)\\d\\+\\*\\-\\/])+");
		matcher = pattern.matcher(input);

		CustomStack<String> stack = new CustomStack<String>();

		while ( matcher.find() )
			stack.push(matcher.group(0));

		return stack;

	}

	public void checkIfValidArithmeticExpression ( CustomStack<String> expression )
	{
		int numbersOnStack = 0;
		String currentExpr;
		CustomStack<String> stack = expression;

		while ( stack != null && !stack.isEmpty() )
		{
			currentExpr = stack.peek();
			if ( isNumber( currentExpr ) ) numbersOnStack++;
			else if ( isOperation( currentExpr ) ) numbersOnStack -= 1;
			else
			{
				ArithmeticException e = new ArithmeticException(
				"Input is not a valid arithmetic expression in Reverse Polish notation");
				throw e;
			}
			stack = stack.peekBelow();
		}

		if ( numbersOnStack != 1 )
			throw new ArithmeticException(
				"Input is not a valid arithmetic expression in Reverse Polish notation");
	}

	public boolean isNumber ( String s )
	{
		Pattern numberPattern = Pattern.compile("[(\\d\\.\\d)\\d)]+");
		Matcher numberMatcher = numberPattern.matcher(s);
		return numberMatcher.matches();
	}

	public boolean isOperation ( String s )
	{
		return ( s.equals("+") ||  s.equals("*") || s.equals("-")  ||  s.equals("/") );
	}

	public double useOperation ( String operation, double numberA, double numberB )
	{
		if ( !isOperation(operation) )
		{
			ArithmeticException e = new ArithmeticException(
			"Operator expected, but got " + operation);
			throw e;
		}

		if ( operation.equals("+") ) return numberA + numberB;
		else if ( operation.equals("*") ) return numberA * numberB;
		else if ( operation.equals("-") ) return numberA - numberB;
		else if ( operation.equals("/") ) return numberA / numberB;

		return 0;
	}

}
