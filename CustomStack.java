import java.util.EmptyStackException;
import java.util.NoSuchElementException;

public class CustomStack<T> {

	protected T onTop;
	protected CustomStack<T> below;

	/**
	 * creates an empty stack
	 */
	public CustomStack () {}

	/**
	 * creates a stack containing a single element
	 *
	 * @param	firstElement	the element which will be the only element on the stack
	 */
	public CustomStack ( T firstElement )
	{
		this.onTop = firstElement;
	}

	/**
	 * creates a stack with at least two elements
	 *
	 * @param	firstElement	the element which will be on top of the stack
	 * @param	theStackBelow	the stack which will be below the top of the stack
	 */
	public CustomStack ( T firstElement, CustomStack<T> theStackBelow )
	{
		this.onTop = firstElement;
		this.below = theStackBelow;
	}

	/**
	 * return true if the stack contains no elements
	 *
	 * @return	true if the stack has no elements, false otherwise
	 */
	public boolean isEmpty () {
		return onTop == null;
	}

	/**
	 * returns the object that is on top of the stack
	 *
	 * @return	the object on top of the stack
	 */
	public T peek ()
	{
		if ( this.isEmpty() )
		{
			EmptyStackException e = new EmptyStackException();
			throw e;
		}
		return this.onTop;
	}

	/**
	 * returns the stack that is below the top of the stack
	 *
	 * @return	the stack below the top of the stack
	 */
	public CustomStack<T> peekBelow ()
	{
		return this.below;
	}

	/**
	 * removes the object on top of the stack and returns it
	 *
	 * @return	the object on top of the stack
	 */
	public T pop ()
	{
		if ( this.isEmpty() )
		{
			EmptyStackException e = new EmptyStackException();
			throw e;
		}
		T returnObject = this.onTop;
		if ( this.below != null )
		{
			this.onTop = this.below.peek();
			this.below = this.below.peekBelow();
		}
		else this.onTop = null;

		return returnObject;
	}

	/**
	 * adds an element to the stack
	 *
	 * @param	newItem	the object which will be added on top of the stack
	 */
	public void push ( T newItem )
	{
		if ( this.onTop == null )	this.onTop = newItem;
		else 
		{
			CustomStack<T> newStack = new CustomStack<T>(this.onTop,this.below);
			this.below = newStack;
			this.onTop = newItem;
		}
	}

	/**
	 * empties the current stack and putting the elements in another stack, effectively reversing it.
	 *
	 * @return						the original stack in reversed order
	 */
	public CustomStack<T> reverse ()
	{
		CustomStack<T> outputStack = new CustomStack<T>();
		while ( !this.isEmpty() )
			outputStack.push( this.pop() );
		return outputStack;
	}

	/**
	 * searches for an item in the stack and returns the position of the item counting
	 * from the top down, if this item is indeed in the stack. Otherwise, a RunTimeExcpetion
	 * (more specifically, a NoSuchElementException) occurs.
	 *
	 * @param	item	the item which will be searched
	 * @return			the position of the item counting from the top down
	 */
	public int search ( T item )
	{
		int counter = 0;
		if ( this.onTop.equals(item) ) return counter;
		if ( this.below == null )
		{
			NoSuchElementException e = new NoSuchElementException();
			throw e;
		}
		return ( this.below.search(item) + 1 );
	}

}
