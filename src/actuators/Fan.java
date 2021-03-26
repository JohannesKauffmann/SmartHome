package actuators;

import mementos.FanMemento;
import mementos.Memento;

public class Fan extends Actuator
{
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

	public void setRpmLevel(int rpmLevel)
	{
		this.rpmLevel = rpmLevel;
	}

	public int getRpmLevel()
	{
		return this.rpmLevel;
	}

	public int incrementRpmLevel()
	{
		this.rpmLevel++;
		return this.rpmLevel;
	}

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
	public String toString() {
		return "Type: Fan, Name: " + this.name;
	}
}
