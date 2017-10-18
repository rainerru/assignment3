import java.util.EmptyStackException;
import java.util.NoSuchElementException;

public class customStack<T> {

	protected T onTop;
	protected customStack<T> below;

	public customStack () {}

	public customStack ( T firstElement )
	{
		this.onTop = firstElement;
	}

	public customStack ( T firstElement, customStack<T> theStackBelow )
	{
		this.onTop = firstElement;
		this.below = theStackBelow;
	}

	public boolean isEmpty () {
		return onTop == null;
	}

	public T peek ()
	{
		if ( this.isEmpty() )
		{
			EmptyStackException e = new EmptyStackException();
			throw e;
		}
		return this.onTop;
	}

	public customStack<T> peekBelow ()
	{
		return this.below;
	}

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

	public void push ( T newItem )
	{
		if ( this.onTop == null )	this.onTop = newItem;
		else 
		{
			customStack<T> newStack = new customStack<T>(this.onTop,this.below);
			this.below = newStack;
			this.onTop = newItem;
		}
	}

	int search ( T item )
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
