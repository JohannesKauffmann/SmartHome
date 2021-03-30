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
 * The HumidifyFacade encapsulates all actions related to humidifying the house. 
 */
public class HumidifyFacade implements Facade
{
	/**
	 * List of actuators used to increase the humidity level in the SmartHome.
	 */
	private ArrayList<ActuatorWrapper> humidifyDevices;

	/**
	 * Constructs a new HumidityFacade with 0 or more actuators.
	 * @param actuators Any number of actuators which may be used to increae humidity levels.
	 */
	public HumidifyFacade(ActuatorWrapper... actuators)
	{
		this.humidifyDevices = new ArrayList<ActuatorWrapper>(Arrays.asList(actuators));
	}

	/**
	 * Executes actions on actuators to make sure the air stays humid.
	 */
	@Override
	public void doAction()
	{
		// Loop through device list and check for supported actuators.
		for (ActuatorWrapper actuatorWrapper : this.humidifyDevices)
		{
			// If an actuator is not supported/used by this facade, the state will still be
			// saved.
			// We are aware of this limitation of the current implementation of the Memento
			// design pattern.
			Actuator actuator = actuatorWrapper.getActuator();
			actuatorWrapper.saveState();
			
			if (actuator instanceof Fan)
			{
				((Fan) actuator).setRpmLevel(10);
			}
			else if (actuator instanceof Airco)
			{
				((Airco) actuator).setModus(AircoModus.COOL);
			}
			else if (actuator instanceof Heater)
			{
				((Heater) actuator).setModus(HeaterModus.POLAR);
			}
			else if (actuator instanceof Sprinkler)
			{
				((Sprinkler) actuator).setSprinklerState(true);
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
		return "humidify facade";
	}

}
