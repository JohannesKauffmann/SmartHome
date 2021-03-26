package actuators;

import mementos.Memento;
import mementos.SprinklerMemento;

public class Sprinkler extends Actuator
{
	private boolean isSprinkling;

	public Sprinkler(String name)
	{
		this.name = name;
		this.isSprinkling = false;
	}

	@Override
	public String getName()
	{
		return name;
	}

	@Override
	public void setName(String name)
	{
		this.name = name;
	}

	public boolean isSprinkling()
	{
		return this.isSprinkling;
	}

	public void setSprinklerState(boolean state)
	{
		this.isSprinkling = state;
	}

	public void switchSprinklingState()
	{
		if (this.isSprinkling)
		{
			this.isSprinkling = false;
		}
		else
		{
			this.isSprinkling = true;
		}
	}

	@Override
	public void doOperation()
	{
		if (this.isSprinkling)
		{
			System.out.println("Sprinkler: " + this.name + " is sprinkling everything wet!");
		}
	}

	@Override
	public Memento save()
	{
		return new SprinklerMemento(this, this.isSprinkling);
	}
	
	@Override
	public String toString() {
		return "Type: Sprinkler, Name: " + this.name;
	}
}
