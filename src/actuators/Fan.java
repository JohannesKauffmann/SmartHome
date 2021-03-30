package actuators;

import mementos.FanMemento;
import mementos.Memento;

/**
 * Concrete implementation of the actuator class.
 */
public class Fan extends Actuator
{
	
	/**
	 * state of this fan.
	 */
	private int rpmLevel;

	public Fan(String name)
	{
		this.name = name;
		this.rpmLevel = 0;
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
		this.rpmLevel = 0;
	}

	/**
	 * setter for the state of this fan.
	 * @param rpmLevel the new state
	 */
	public void setRpmLevel(int rpmLevel)
	{
		this.rpmLevel = rpmLevel;
	}

	/**
	 * getter for the current state of this fan.
	 * @return the rpmLevel of this fan (state).
	 */
	public int getRpmLevel()
	{
		return this.rpmLevel;
	}

	/**
	 * Increment the rpmLevel of this fan.
	 * @return new rpmLevel
	 */
	public int incrementRpmLevel()
	{
		this.rpmLevel++;
		return this.rpmLevel;
	}

	/**
	 * Decrement the rpmLevel of this fan.
	 * @return new rpmLevel
	 */
	public int decrementRpmLevel()
	{
		this.rpmLevel--;
		return this.rpmLevel;
	}

	@Override
	public void doOperation()
	{
		System.out.println("Fan: " + this.name + " is blowing at this level: " + this.rpmLevel);
	}

	@Override
	public Memento save()
	{
		return new FanMemento(this, this.rpmLevel);
	}

	@Override
	public String toString()
	{
		return "Type: Fan, Name: " + this.name;
	}
}
