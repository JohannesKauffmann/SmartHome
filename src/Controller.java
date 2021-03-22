import java.util.ArrayList;
import java.util.HashMap;

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
	
	public HashMap<String, Facade> getFacades(){
		return this.facades;
	}

	public void addSensor(Sensor sensor)
	{
		this.sensors.add(sensor);
	}

	public void addActuator(ActuatorWrapper actuatorWrapper)
	{
		this.actuators.add(actuatorWrapper);
	}

	public void addFacade(String name, Facade facade)
	{
		this.facades.put(name, facade);
	}

	public void executeFacade(String facadeName)
	{
		Facade facade = this.facades.get(facadeName);
		if (facade != null)
		{
			facade.doAction();
		}
	}
	
	public ArrayList<ActuatorWrapper> getActuators(){
		return this.actuators;
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
			} else if (value >= 25)
			{
				Facade cooling = this.facades.get("cooling");
				if (cooling != null)
				{
					cooling.doAction();
				}
			} else
			{
				// do nothing
			}
		} else if (publisher instanceof HumiditySensor)
		{
			if (value <= 40)
			{
				Facade humidify = this.facades.get("humidifying");
				if (humidify != null)
				{
					humidify.doAction();
				}
			} else if (value >= 60)
			{
				Facade dry = this.facades.get("drying");
				if (dry != null)
				{
					dry.doAction();
				}
			} else
			{
				// do nothing
			}
		}
	}
}
