package facades;

import java.util.ArrayList;
import java.util.Arrays;

import actuators.Actuator;
import actuators.Airco;
import actuators.AircoModus;
import actuators.Fan;
import actuators.Heater;
import actuators.HeaterModus;

public class DryingFacade implements Facade
{
	private ArrayList<Actuator> dryingDevices;

	public DryingFacade(Actuator... actuators)
	{
		this.dryingDevices = new ArrayList<Actuator>(Arrays.asList(actuators));
	}

	@Override
	public void doAction()
	{
		for (Actuator actuator : this.dryingDevices)
		{
			if (actuator instanceof Fan)
			{
				((Fan) actuator).setRpmLevel(100);
			}
			else if (actuator instanceof Heater)
			{
				((Heater) actuator).setModus(HeaterModus.CREMATORIUM);
			}
			else if(actuator instanceof Airco){
				((Airco) actuator).setModus(AircoModus.DRY);
			}
			else
			{
				continue;
			}
			actuator.doOperation();
		}
		
	}

}
