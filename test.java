import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class test {

	public static void main ( String[] args ) {

		customStack<String> myStack = new customStack<String>("first");
		String aWord = "second";
		myStack.push(aWord);
		myStack.push("third");
		System.out.println("find aWord (= second) gives " + myStack.search(aWord));
		System.out.println(myStack.pop());
		System.out.println(myStack.pop());
		myStack.push("fourth");
		System.out.println(myStack.pop());
		System.out.println(myStack.pop());

		calculator asdf = new calculator();
		System.out.println("2.2 6.6 / 3 + = " + asdf.evaluate("2.2 6.6 / 3 +"));
		System.out.println("senseless stuff: 2 5 4 + = " + asdf.evaluate("2 5 4 +")); 

	}

}
