package facades;

import java.util.ArrayList;
import java.util.Arrays;

import actuators.Actuator;
import actuators.Airco;
import actuators.AircoModus;
import actuators.Fan;
import actuators.Heater;
import actuators.HeaterModus;

public class CoolingFacade implements Facade
{
	private ArrayList<Actuator> coolingDevices;

	public CoolingFacade(Actuator... actuators)
	{
		this.coolingDevices = new ArrayList<Actuator>(Arrays.asList(actuators));
	}

	@Override
	public void doAction()
	{
		for (Actuator actuator : this.coolingDevices)
		{
			if (actuator instanceof Fan)
			{
				((Fan) actuator).setRpmLevel(100);
			} else if (actuator instanceof Airco)
			{
				((Airco) actuator).setModus(AircoModus.COOL);
			} else if (actuator instanceof Heater)
			{
				((Heater) actuator).setModus(HeaterModus.POLAR);
			} else
			{
				continue;
			}
			actuator.doOperation();
		}
	}
}
