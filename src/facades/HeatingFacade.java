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

public class HeatingFacade implements Facade
{
	private ArrayList<ActuatorWrapper> heatingDevices;

	public HeatingFacade(ActuatorWrapper... actuators)
	{
		this.heatingDevices = new ArrayList<ActuatorWrapper>(Arrays.asList(actuators));
	}

	@Override
	public void doAction()
	{
		for (ActuatorWrapper actuatorWrapper : this.heatingDevices)
		{
			Actuator actuator = actuatorWrapper.getActuator();
			// if an actuator is not supported/used by this facade, the state will still be
			// saved.
			// We are aware of this limitation of the current implementation of the memento
			// design pattern.
			actuatorWrapper.saveState();
			if (actuator instanceof Fan)
			{
				((Fan) actuator).setRpmLevel(0);
			} else if (actuator instanceof Airco)
			{
				((Airco) actuator).setModus(AircoModus.HEAT);
			} else if (actuator instanceof Heater)
			{
				((Heater) actuator).setModus(HeaterModus.CREMATORIUM);
			} else if (actuator instanceof Sprinkler)
			{
				((Sprinkler) actuator).setSprinklerState(false);
			} else
			{
				continue;
			}
			actuator.doOperation();
		}

	}

}
