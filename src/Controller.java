import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import actuators.Actuator;
import commands.Command;
import facades.Facade;
import mementos.Memento;
import sensors.Sensor;

public class Controller
{
	private HashMap<Actuator, HashMap<String, Command>> actuators;
	private ArrayList<Sensor> sensors;
	private HashMap<String, Facade> facades;
	private ArrayList<Memento> history;

	public Controller()
	{
		this.actuators = new HashMap<>();
		this.sensors = new ArrayList<>();
		this.facades = new HashMap<>();
		this.history = new ArrayList<>();
		initialize();
	}

	private void addSensor(Sensor s)
	{
		this.sensors.add(s);
	}

	private void addActuator(Actuator a)
	{
		this.actuators.put(a, new HashMap<>());
	}

	private void addCommand(Actuator a, String cName, Command c)
	{
		HashMap<String, Command> commands = this.actuators.get(a);
		if (commands != null)
		{
			commands.put(cName, c);
		}
	}

	public void executeCommand(String actuatorName, String commandName)
	{
		for (Map.Entry<Actuator, HashMap<String, Command>> entry : actuators.entrySet())
		{
			if (entry.getKey().getName().equals(actuatorName))
			{
				Command c = entry.getValue().get(commandName);
				if (c != null)
				{
					c.execute();
				}
				break;
			}
		}
	}

	public void executeFacade(String facadeName)
	{

	}

//	public void undo(String actuatorName) {
////		for (Map.Entry<Actuator, ArrayList<Memento>> entry : history.entrySet())
//		for (int i = this.history.size() - 1; i >= 0; i--)
//		{
//			this.history.get(i).getOr
//			if (a.getName().equals(actuatorName))
//			{
//				ArrayList<Memento> mementos = entry.getValue();
//				mementos.get(mementos.size() - 1).restore();
//				a.restore(m);
//				
//			}
//		}
//	}

	/*
	 * Method for initializing the sensors, facades, commands, actuators, etc.
	 */
	private void initialize()
	{

	}

}
