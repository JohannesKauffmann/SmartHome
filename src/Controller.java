import java.util.ArrayList;
import java.util.HashMap;
import actuators.*;
import facades.Facade;
import sensors.*;
import subscribers.Subscriber;


/**
 * Controller class is the object in the application that holds all the sensors,
 * facades and actuators. *
 */
public class Controller implements Subscriber
{
	private ArrayList<ActuatorWrapper> actuators;
	private ArrayList<Sensor> sensors;
	private HashMap<String, Facade> facades;

	/**
	 * Constructor for controller, this only will initialize the lists.
	 */
	public Controller()
	{
		this.actuators = new ArrayList<>();
		this.sensors = new ArrayList<>();
		this.facades = new HashMap<>();
	}

	/**
	 * This methods will print all sensor that are present in the controller.
	 */
	public void printSensors()
	{
		System.out.println("Sensors: ");
		for (Sensor sensor : this.sensors)
		{
			System.out.println(sensor.getName());
		}
	}

	/**
	 * This method will print all facade that are present in the controller.
	 */
	public void printFacades()
	{
		System.out.println("Facades:");
		for (String facadeName : this.facades.keySet())
		{
			System.out.println(facadeName);
		}
	}

	/**
	 * This method will get a facade by name.
	 * @param facadeName the name of the facade we want to get.
	 * @return the facade with the given name or null if not found.
	 */
	public Facade getFacade(String facadeName)
	{
		return this.facades.get(facadeName);
	}

	/**
	 * This method will get a sensor by name.
	 * @param sensorName the name of the sensor we want to get.
	 * @return the sensor with the given name or null if not found.
	 */
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

	/**
	 * add a sensor to this controller.
	 * @param sensor the sensor to add.
	 */
	public void addSensor(Sensor sensor)
	{
		this.sensors.add(sensor);
	}

	/**
	 * add a given actuator(wrapper) to this controller.
	 * @param actuatorWrapper the actuator to add.
	 */
	public void addActuator(ActuatorWrapper actuatorWrapper)
	{
		this.actuators.add(actuatorWrapper);
	}

	/**
	 * add a facade and it's name to this controller.
	 * @param name the name of the facade.
	 * @param facade The facade to add.
	 */
	public void addFacade(String name, Facade facade)
	{
		this.facades.put(name, facade);
	}

	/**
	 * execute the facade with the given name.
	 * @param facadeName
	 * @return boolean: true if the facade was found and executed, otherwise false.
	 */
	public boolean executeFacade(String facadeName)
	{
		Facade facade = this.facades.get(facadeName);
		//check if facade is null, this can happen when there is no facade with given name.
		if (facade != null)
		{
			facade.doAction();
			return true;
		}
		return false;
	}

	/**
	 * get an actuatorWrapper by the actuator name.
	 * @param actuatorName
	 * @return
	 */
	public ActuatorWrapper getActuator(String actuatorName)
	{
		//loop through actuatorWrappers
		for (ActuatorWrapper actuatorWrapper : this.actuators)
		{
			//if the name of the actuator in the actuatorWrapper equals to the given name, return.
			if (actuatorWrapper.getActuator().getName().equals(actuatorName))
			{
				return actuatorWrapper;
			}
		}
		return null;
	}

	/**
	 * method for printing all actuators in the controller.
	 */
	public void printActuators()
	{
		System.out.println("Actuators:");
		for (ActuatorWrapper actuatorWrapper : this.actuators)
		{
			//Actuator overrides the toString method.
			System.out.println(actuatorWrapper.getActuator());
		}
	}

	/**
	 *Controller is a Subscriber, (observer pattern). Therefore it implements the update method.
	 *
	 *We had to implement some logic what happen when there was a new value of a sensor.
	 *This method will execute a facade based on the type and value of the publisher.
	 */
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
