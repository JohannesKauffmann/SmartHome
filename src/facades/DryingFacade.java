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
 * The DryingFacade encapsulates all actions related to turning the SmartHome into a small desert.
 */
public class DryingFacade implements Facade
{
	/**
	 * List of actuators used to de-vaporize the SmartHome.
	 */
	private ArrayList<ActuatorWrapper> dryingDevices;

	/**
	 * Constructs a new DryingFacade with 0 or more actuators.
	 * @param actuators Any number of actuators which may be used for drying.
	 */
	public DryingFacade(ActuatorWrapper... actuators)
	{
		this.dryingDevices = new ArrayList<ActuatorWrapper>(Arrays.asList(actuators));
	}

	/**
	 * Executes actions on actuators to decrease humidity in the SmartHome.
	 */
	@Override
	public void doAction()
	{
		// Loop through device list and check for supported actuators.
		for (ActuatorWrapper actuatorWrapper : this.dryingDevices)
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
			else if (actuator instanceof Heater)
			{
				((Heater) actuator).setModus(HeaterModus.CREMATORIUM);
			}
			else if (actuator instanceof Airco)
			{
				((Airco) actuator).setModus(AircoModus.DRY);
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
		return "drying facade";
	}

}
