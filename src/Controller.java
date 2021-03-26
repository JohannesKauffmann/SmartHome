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
			Facade facade = null;

			if (value <= 20)
			{
				facade = this.facades.get("heating");
			}
			else if (value >= 25)
			{
				facade = this.facades.get("cooling");
			}
			else
			{
				// do nothing
			}
			if (facade != null)
			{
				System.out.println();
				System.out.println("Because of new temperature update: executing the " + facade);
				facade.doAction();
				System.out.println("Executed all actions that followed because of an updated temperature!");
				System.out.println();
			}
		}
		else if (publisher instanceof HumiditySensor)
		{
			Facade facade = null;

			if (value <= 40)
			{
				facade = this.facades.get("humidifying");
			}
			else if (value >= 60)
			{
				facade = facades.get("drying");
			}
			else
			{
				// do nothing
			}

			if (facade != null)
			{
				System.out.println();
				System.out.println("Because of new humidity update: executing the " + facade);
				facade.doAction();
				System.out.println("Executed all actions that followed because of an updated humidity!");
				System.out.println();
			}

		}
	}
}
