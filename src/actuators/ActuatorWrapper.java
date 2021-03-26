package actuators;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Stack;

import commands.Command;
import mementos.Memento;

public class ActuatorWrapper
{
	private Stack<Memento> history;
	private HashMap<String, Command> commands;
	private Actuator actuator;

	public ActuatorWrapper(Actuator actuator)
	{
		this.actuator = actuator;
		this.history = new Stack<>();
		this.commands = new HashMap<>();
	}

	public void addCommand(String commandName, Command command)
	{
		this.commands.put(commandName, command);
	}

	public Command getCommand(String commandName)
	{
		for (Entry<String, Command> entry : this.commands.entrySet())
		{
			if (entry.getKey().equals(commandName))
			{
				return entry.getValue();
			}
		}
		return null;
	}

	public void deleteCommand(String commandName)
	{
		this.commands.remove(commandName);
	}

	public void executeCommand(String commandName)
	{
		// save the state of the actuator before executing the command.
		saveState();

		this.commands.get(commandName).execute();
	}

	public Actuator getActuator()
	{
		return this.actuator;
	}

	public void printCommands()
	{
		for (String commandName : this.commands.keySet())
		{
			System.out.println("Command: " + commandName);
		}
	}

	public boolean undo()
	{
		if (!this.history.empty())
		{
			Memento lastState = this.history.pop();
			if (lastState != null)
			{
				lastState.restore();
			}
			return true;
		} else
		{
			return false;
		}
	}

	public void saveState()
	{
		this.history.push(this.actuator.save());
	}

}
