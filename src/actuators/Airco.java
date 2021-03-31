package actuators;

import mementos.AircoMemento;
import mementos.Memento;

/**
 * Concrete implementation of the actuator class.
 */
public class Airco extends Actuator
{

	/**
	 * modus/state of this airco.
	 */
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

	/**
	 * setter for the airco modus of this airco.
	 * 
	 * @param state The new aircoModus
	 */
	public void setModus(AircoModus state)
	{
		this.modus = state;
	}

	/**
	 * getter for the airco modus of this airco.
	 * 
	 * @return the current aircoModus.
	 */
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
