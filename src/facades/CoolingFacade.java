package facades;

import java.util.ArrayList;
import java.util.Arrays;

import actuators.Actuator;
import actuators.Airco;
import actuators.AircoModus;
import actuators.Fan;

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
		for (Actuator actuator : coolingDevices)
		{
			if (actuator instanceof Fan)
			{
				((Fan) actuator).setRpmLevel(100);
			}
			else if (actuator instanceof Airco)
			{
				((Airco) actuator).setModus(AircoModus.COOL);
			}
			else
			{
				continue;
			}
			actuator.doOperation();
		}
	}
}
