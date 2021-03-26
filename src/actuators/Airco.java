package actuators;

import mementos.AircoMemento;
import mementos.Memento;

public class Airco extends Actuator
{
	private AircoModus modus;

	public Airco(String name)
	{
		this.name = name;
		// set default modus
		this.modus = AircoModus.COOL;
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

	public void setModus(AircoModus state)
	{
		this.modus = state;
	}

	public AircoModus getModus()
	{
		return this.modus;
	}

	@Override
	public void doOperation()
	{
		String operationString = "Airco: " + super.name;
		switch (this.modus)
		{
		case AUTO:
		{
			operationString += " is handling the environment in auto modus!";
			break;
		}
		case COOL:
		{
			operationString += " is freezing the room!";
			break;
		}
		case HEAT:
		{
			operationString += " is cooking the room!";
			break;
		}
		case VENTILATION:
		{
			operationString += " is blowing all the stuff out of your room!";
			break;
		}
		case DRY:
		{
			operationString += " is turning your room in a desert!";
			break;
		}
		default:

		}

		System.out.println(operationString);
	}

	@Override
	public Memento save()
	{
		return new AircoMemento(this, this.modus);
	}

	@Override
	public String toString()
	{
		return "Type: Airco, Name: " + this.name;
	}
}
