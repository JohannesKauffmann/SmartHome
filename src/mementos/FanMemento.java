package mementos;

import actuators.Fan;

/**
 * The FanMemento represents a saved state of the Fan actuator. 
 */
public class FanMemento implements Memento
{
	/**
	 * Actuator (Fan) for which we hold a state. 
	 */
	private Fan originator;
	
	/**
	 * The saved rpm-level of the fan.
	 */
	private int rpmLevel;

	/**
	 * Constructs a new FanMemento.
	 * @param originator    The actuator for which to save the state.
	 * @param rpmLevel      The state (rmp level) to save.
	 */
	public FanMemento(Fan originator, int rpmLevel)
	{
		this.originator = originator;
		this.rpmLevel = rpmLevel;
	}

	/**
	 * Restore the rpm-level to the previous state.
	 */
	@Override
	public void restore()
	{
		this.originator.setRpmLevel(this.rpmLevel);
	}
}
