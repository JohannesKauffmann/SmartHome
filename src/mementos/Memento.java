package mementos;

/**
 * A Memento represents a specific state at a certain point in history. 
 */
public interface Memento
{
	/**
	 * Reset the state of the associated originator to the previous saved state.
	 */
	public void restore();
}
