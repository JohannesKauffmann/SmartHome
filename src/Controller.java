import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import actuators.*;
import facades.Facade;
import sensors.*;
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

	public void printSensors()
	{
		System.out.println("Sensors: ");
		for (Sensor sensor : this.sensors)
		{
			System.out.println(sensor.getName());
		}
	}

	public void printFacades()
	{
		System.out.println("Facades:");
		for (String facadeName : this.facades.keySet())
		{
			System.out.println(facadeName);
		}
	}

	public Facade getFacade(String FacadeName)
	{
		for (Entry<String, Facade> entry : this.facades.entrySet())
		{
			if (entry.getKey().equals(FacadeName))
			{
				return entry.getValue();
			}
		}
		return null;
	}

	public Sensor getSensor(String sensorName)
	{
		for (Sensor sensor : this.sensors)
		{
			if (sensor.getName().equals(sensorName))
			{
				return sensor;
			}
		}
		return null;
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

	public ActuatorWrapper getActuator(String actuatorName)
	{
		for (ActuatorWrapper actuatorWrapper : this.actuators)
		{
			if (actuatorWrapper.getActuator().getName().equals(actuatorName))
			{
				return actuatorWrapper;
			}
		}
		return null;
	}

	public void printActuators()
	{
		System.out.println("Actuators:");
		for (ActuatorWrapper actuatorWrapper : this.actuators)
		{
			System.out.println(actuatorWrapper.getActuator());
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
				Facade humidify = this.facades.get("humidifying");
				if (humidify != null)
				{
					humidify.doAction();
				}
			}
			else if (value >= 60)
			{
				Facade dry = this.facades.get("drying");
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
