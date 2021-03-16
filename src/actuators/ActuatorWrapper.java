package actuators;

import java.util.HashMap;
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

	public void deleteCommand(String commandName)
	{
		this.commands.remove(commandName);
	}

	public void executeCommand(String commandName)
	{
		this.commands.get(commandName).execute();
	}

	public void undo()
	{
		Memento lastState = this.history.pop();
		if (lastState != null)
		{
			lastState.restore();
		}
	}
}
