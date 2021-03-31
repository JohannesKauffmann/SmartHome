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

/**
 * The CoolingFacade encapsulates all actions related to cooling the house.
 */
public class CoolingFacade implements Facade
{
	/**
	 * List of actuators used to cool the SmartHome and reduce heating capabilities.
	 */
	private ArrayList<ActuatorWrapper> coolingDevices;

	/**
	 * Constructs a new CoolingFacade with 0 or more actuators.
	 * @param actuators Any number of actuators which may be used for cooling.
	 */
	public CoolingFacade(ActuatorWrapper... actuators)
	{
		this.coolingDevices = new ArrayList<ActuatorWrapper>(Arrays.asList(actuators));
	}

	/**
	 * Executes actions on actuators to cool the SmartHome.
	 */
	@Override
	public void doAction()
	{
		// Loop through device list and check for supported actuators.
		for (ActuatorWrapper actuatorWrapper : this.coolingDevices)
		{
			// If an actuator is not supported/used by this facade, the state will still be
			// saved.
			// We are aware of this limitation of the current implementation of the Memento
			// design pattern.
			Actuator actuator = actuatorWrapper.getActuator();
			actuatorWrapper.saveState();

			// Set the modus of supported actuators.
			if (actuator instanceof Fan)
			{
				((Fan) actuator).setRpmLevel(100);
			}
			else if (actuator instanceof Airco)
			{
				((Airco) actuator).setModus(AircoModus.COOL);
			}
			else if (actuator instanceof Heater)
			{
				((Heater) actuator).setModus(HeaterModus.POLAR);
			}
			else
			{
				System.err.println("Invalid actuator!");
				continue;
			}
			
			// Execute the operation associated with the actuator.
			actuator.doOperation();
		}
	}

	@Override
	public String toString()
	{
		return "cooling facade";
	}
}
