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

public class HumidifyFacade implements Facade
{
	private ArrayList<ActuatorWrapper> humidifyDevices;

	public HumidifyFacade(ActuatorWrapper... actuators)
	{
		this.humidifyDevices = new ArrayList<ActuatorWrapper>(Arrays.asList(actuators));
	}

	@Override
	public void doAction()
	{
		for (ActuatorWrapper actuatorWrapper : this.humidifyDevices)
		{
			Actuator actuator = actuatorWrapper.getActuator();
			// if an actuator is not supported/used by this facade, the state will still be
			// saved.
			// We are aware of this limitation of the current implementation of the memento
			// design pattern.
			actuatorWrapper.saveState();
			if (actuator instanceof Fan)
			{
				((Fan) actuator).setRpmLevel(10);
			} else if (actuator instanceof Airco)
			{
				((Airco) actuator).setModus(AircoModus.COOL);
			} else if (actuator instanceof Heater)
			{
				((Heater) actuator).setModus(HeaterModus.POLAR);
			} else if (actuator instanceof Sprinkler)
			{
				((Sprinkler) actuator).setSprinklerState(true);
			} else
			{
				continue;
			}
			actuator.doOperation();
		}

	}

}
