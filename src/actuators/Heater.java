package actuators;

import mementos.HeaterMemento;
import mementos.Memento;

public class Heater extends Actuator
{
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

	public void setModus(HeaterModus modus)
	{
		this.modus = modus;
	}

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
