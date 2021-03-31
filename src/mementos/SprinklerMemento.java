package mementos;

import actuators.Sprinkler;

/**
 * The SprinklerMemento represents a saved state of the Sprinkler actuator. 
 */
public class SprinklerMemento implements Memento
{
	/**
	 * Actuator (Sprinkler) for which we hold a state. 
	 */
	private Sprinkler originator;
	
	/**
	 * The saved state of the sprinkler.
	 */
	private boolean state;

	/**
	 * Constructs a new FanMemento.
	 * @param originator    The actuator for which to save the state.
	 * @param state         The state (state, on/off) to save.
	 */
	public SprinklerMemento(Sprinkler originator, boolean state)
	{
		this.originator = originator;
		this.state = state;
	}

	/**
	 * Restore the on/off-state to the previous state.
	 */
	@Override
	public void restore()
	{
		this.originator.setSprinklerState(state);
	}
}
