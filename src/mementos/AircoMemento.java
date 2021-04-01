package mementos;

import actuators.Airco;
import actuators.AircoModus;

/**
 * The AircoMemento represents a saved state of the Airco actuator. 
 */
public class AircoMemento implements Memento
{
	private AircoModus state;
	private Airco originator;

	/**
	 * Constructor of the AircoMemento.
	 * @param originator
	 * @param state
	 */
	public AircoMemento(Airco originator, AircoModus state)
	{
		this.state = state;
		this.originator = originator;
	}
	
	/**
	 * Restores the last state of the airco.
	 */
	@Override
	public void restore()
	{
		this.originator.setModus(this.state);
	}
}
