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

public class DryingFacade implements Facade
{
	private ArrayList<ActuatorWrapper> dryingDevices;

	public DryingFacade(ActuatorWrapper... actuators)
	{
		this.dryingDevices = new ArrayList<ActuatorWrapper>(Arrays.asList(actuators));
	}

	@Override
	public void doAction()
	{
		for (ActuatorWrapper actuatorWrapper : this.dryingDevices)
		{
			Actuator actuator = actuatorWrapper.getActuator();
			// if an actuator is not supported/used by this facade, the state will still be
			// saved.
			// We are aware of this limitation of the current implementation of the memento
			// design pattern.
			actuatorWrapper.saveState();
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
