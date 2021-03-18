import java.util.ArrayList;
import java.util.HashMap;

import actuators.Actuator;
import actuators.ActuatorWrapper;
import facades.Facade;
import sensors.HumiditySensor;
import sensors.Sensor;
import sensors.TemperatureSensor;
import subscribers.Subscriber;

public class Controller implements Subscriber
{
	private ArrayList<ActuatorWrapper> actuators;
	private ArrayList<Sensor> sensors;
	private HashMap<String, Facade> facades;

	public Controller()
	{
		this.actuators = new ArrayList<>();
		this.sensors = new ArrayList<>();
		this.facades = new HashMap<>();
	}

	private void addSensor(Sensor sensor)
	{
		this.sensors.add(sensor);
	}

	private void addActuator(Actuator actuator)
	{
		this.actuators.add(new ActuatorWrapper(actuator));
	}

	public void executeFacade(String facadeName)
	{
		Facade facade = this.facades.get(facadeName);
		if (facade != null)
		{
			facade.doAction();
		}
	}

	@Override
	public void update(Sensor publisher, int value)
	{
		if (publisher instanceof TemperatureSensor)
		{
			if (value <= 20)
			{
				Facade heating = this.facades.get("heating");
				if (heating != null)
				{
					heating.doAction();
				}
			}
			else if (value >= 25)
			{
				Facade cooling = this.facades.get("cooling");
				if (cooling != null)
				{
					cooling.doAction();
				}
			}
			else
			{
				// do nothing
			}
		}
		else if (publisher instanceof HumiditySensor)
		{
			if (value <= 40)
			{
				Facade humidify = this.facades.get("humidify");
				if (humidify != null)
				{
					humidify.doAction();
				}
			}
			else if (value >= 60)
			{
				Facade dry = this.facades.get("dry");
				if (dry != null)
				{
					dry.doAction();
				}
			}
			else
			{
				// do nothing
			}
		}
	}
}
