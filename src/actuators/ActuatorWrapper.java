package actuators;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Stack;

import commands.Command;
import mementos.Memento;

/**
 * The actuatorWrapper is a class that wraps all the functionality of an
 * actuator. It holds all the possible commands for this actuator and is the
 * care taker of the actuator(memento pattern).
 *
 */
public class ActuatorWrapper
{

	/**
	 * The stack with the previous states (memento pattern).
	 */
	private Stack<Memento> history;

	/**
	 * all possible commands for this actuator with corresponding names.
	 */
	private HashMap<String, Command> commands;
	private Actuator actuator;

	public ActuatorWrapper(Actuator actuator)
	{
		this.actuator = actuator;
		this.history = new Stack<>();
		this.commands = new HashMap<>();
	}

	/**
	 * add a command to the actuator wrapper.
	 * 
	 * @param commandName
	 * @param command
	 */
	public void addCommand(String commandName, Command command)
	{
		this.commands.put(commandName, command);
	}

	/**
	 * Getter for a specific command.
	 * 
	 * @param commandName the name of the command.
	 * @return the Command with the given name, or null if not found.
	 */
	public Command getCommand(String commandName)
	{
		return this.commands.get(commandName);
	}

	public void executeCommand(String commandName)
	{
		Command command = this.commands.get(commandName);

		if (command != null)
		{
			// save the state of the actuator before executing the command (memento
			// pattern).
			saveState();

			this.commands.get(commandName).execute();
		}
	}

	/**
	 * getter of the actuator which is stored in this actuatorWrapper.
	 * 
	 * @return the actuator of this wrapper.
	 */
	public Actuator getActuator()
	{
		return this.actuator;
	}

	/**
	 * print function for all commands in this wrapper.
	 */
	public void printCommands()
	{
		for (String commandName : this.commands.keySet())
		{
			System.out.println("Command: " + commandName);
		}
	}

	/**
	 * The undo action of the memento pattern. This method will restore the actuator
	 * to an earlier state.
	 * 
	 * @return boolean True if last state was restored, false if there was no history to undo.
	 */
	public boolean undo()
	{
		if (!this.history.empty())
		{
			// get last memento from stack
			Memento lastState = this.history.pop();
			if (lastState != null)
			{
				lastState.restore();
			}
			return true;
		} else
		{
			System.err.println("No states to undo!");
			return false;
		}
	}

	/**
	 * method for saving the state of the actuator (memento pattern).
	 */
	public void saveState()
	{
		this.history.push(this.actuator.save());
	}

}
