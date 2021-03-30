package actuators;

import mementos.HeaterMemento;
import mementos.Memento;

/**
 * Concrete implementation of the actuator class.
 */
public class Heater extends Actuator
{
	
	/**
	 * The modus/state of this Heater.
	 */
	private HeaterModus modus;

	public Heater(String name)
	{
		this.name = name;
		this.modus = HeaterModus.POLAR;
	}

	@Override
	public String getName()
	{
		return this.name;
	}

	@Override
	public void setName(String name)
	{
		this.name = name;
	}

	@Override
	public void doOperation()
	{
		System.out.println("Heater " + this.name + " is heating at modus: " + this.modus.toString());
	}

	/**
	 * Setter for the modus of this heater
	 * @param modus the new HeaterModus
	 */
	public void setModus(HeaterModus modus)
	{
		this.modus = modus;
	}

	/**
	 * Getter for the modus of this heater
	 * @return the current modus.
	 */
	public HeaterModus getModus()
	{
		return this.modus;
	}

	@Override
	public Memento save()
	{
		return new HeaterMemento(this, this.modus);
	}

	@Override
	public String toString()
	{
		return "Type: Heater, Name: " + this.name;
	}
}
