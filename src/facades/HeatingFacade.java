package facades;

import java.util.ArrayList;
import java.util.Arrays;

import actuators.Actuator;
import actuators.Airco;
import actuators.AircoModus;
import actuators.Fan;
import actuators.Heater;
import actuators.HeaterModus;
import actuators.Sprinkler;

public class HeatingFacade implements Facade
{
	private ArrayList<Actuator> heatingDevices;

	public HeatingFacade(Actuator... actuators)
	{
		this.heatingDevices = new ArrayList<Actuator>(Arrays.asList(actuators));
	}

	@Override
	public void doAction()
	{
		for (Actuator actuator : this.heatingDevices)
		{
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
