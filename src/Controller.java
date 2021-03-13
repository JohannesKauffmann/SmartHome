import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import actuators.Actuator;
import actuators.ActuatorWrapper;
import commands.Command;
import facades.Facade;
import jdk.jshell.spi.ExecutionControl.NotImplementedException;
import mementos.Memento;
import sensors.Sensor;

public class Controller
{
	private ArrayList<ActuatorWrapper> actuators;
	private ArrayList<Sensor> sensors;
	private HashMap<String, Facade> facades;

	public Controller()
	{
		this.actuators = new ArrayList<>();
		this.sensors = new ArrayList<>();
		this.facades = new HashMap<>();
		initialize();
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

	/*
	 * Method for initializing the sensors, facades, commands, actuators, etc.
	 */
	private void initialize()
	{
		// TODO filling the initialize method.
	}

}
