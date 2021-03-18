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

public class HumidifyFacade implements Facade
{
	private ArrayList<Actuator> humidifyDevices;

	public HumidifyFacade(Actuator... actuators)
	{
		this.humidifyDevices = new ArrayList<Actuator>(Arrays.asList(actuators));
	}

	@Override
	public void doAction()
	{
		for (Actuator actuator : this.humidifyDevices)
		{
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
