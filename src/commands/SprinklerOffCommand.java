package commands;

import actuators.Sprinkler;

/**
 * The SprinklerOffCommand is a concrete Command.
 */
public class SprinklerOffCommand implements Command
{
	/**
	 * The receiver is the object which does the actual work
	 */
	private Sprinkler receiver;

	public SprinklerOffCommand(Sprinkler sprinkler)
	{
		this.receiver = sprinkler;
	}

	/**
	 *The implementation of the execute method from the Command interface
	 */
	@Override
	public void execute()
	{
		this.receiver.setSprinklerState(false);
		this.receiver.doOperation();
	}

}
