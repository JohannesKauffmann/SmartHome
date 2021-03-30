package facades;

import java.util.ArrayList;
import java.util.Arrays;

import actuators.Actuator;
import actuators.ActuatorWrapper;
import actuators.Airco;
import actuators.AircoModus;
import actuators.Fan;
import actuators.Heater;
import actuators.HeaterModus;
import actuators.Sprinkler;

/**
 * The HeatingFacade encapsulates all actions related to heating the house.
 */
public class HeatingFacade implements Facade
{
	/**
	 * List of actuators used to heat the SmartHome and reduce cooling capabilities.
	 */
	private ArrayList<ActuatorWrapper> heatingDevices;

	/**
	 * Constructs a new HeatingFacade with 0 or more actuators.
	 * @param actuators Any number of actuators which may be used for heating.
	 */
	public HeatingFacade(ActuatorWrapper... actuators)
	{
		this.heatingDevices = new ArrayList<ActuatorWrapper>(Arrays.asList(actuators));
	}
	
	/**
	 * Executes actions on actuators to warm the SmartHome.
	 */
	@Override
	public void doAction()
	{
		// Loop through device list and check for supported actuators.
		for (ActuatorWrapper actuatorWrapper : this.heatingDevices)
		{
			// If an actuator is not supported/used by this facade, the state will still be
			// saved.
			// We are aware of this limitation of the current implementation of the Memento
			// design pattern.
			Actuator actuator = actuatorWrapper.getActuator();
			actuatorWrapper.saveState();
			
			if (actuator instanceof Fan)
			{
				((Fan) actuator).setRpmLevel(0);
			}
			else if (actuator instanceof Airco)
			{
				((Airco) actuator).setModus(AircoModus.HEAT);
			}
			else if (actuator instanceof Heater)
			{
				((Heater) actuator).setModus(HeaterModus.CREMATORIUM);
			}
			else if (actuator instanceof Sprinkler)
			{
				((Sprinkler) actuator).setSprinklerState(false);
			}
			else
			{
				System.err.println("Invalid actuator!");
				continue;
			}
		}

	}

	@Override
	public String toString()
	{
		return "heating facade";
	}

}
