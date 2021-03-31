package commands;

import actuators.Sprinkler;

/**
 * The SprinklerOnCommand is a concrete Command.
 */
public class SprinklerOnCommand implements Command
{
	/**
	 * The receiver is the object which does the actual work
	 */
	private Sprinkler receiver;

	public SprinklerOnCommand(Sprinkler sprinkler)
	{
		this.receiver = sprinkler;
	}

	/**
	 * The implementation of the execute method from the Command interface
	 */
	@Override
	public void execute()
	{
		this.receiver.setSprinklerState(true);
		this.receiver.doOperation();
	}

}