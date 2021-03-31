package mementos;

import actuators.Heater;
import actuators.HeaterModus;

/**
 * The HeaterMemento represents a saved state of the Heater actuator. 
 */
public class HeaterMemento implements Memento
{
	private Heater originator;
	private HeaterModus state;

	/**
	 * Constructor of the HeaterMemento.
	 * @param originator
	 * @param state
	 */
	public HeaterMemento(Heater originator, HeaterModus state)
	{
		this.originator = originator;
		this.state = state;
	}

	/**
	 * Restores the last state of the heater.
	 */
	@Override
	public void restore()
	{
		this.originator.setModus(this.state);
	}
}
