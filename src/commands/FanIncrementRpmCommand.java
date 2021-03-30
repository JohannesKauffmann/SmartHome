package commands;

import actuators.Fan;

/**
 * The FanIncrementRpmCommand is a concrete Command.
 */
public class FanIncrementRpmCommand implements Command
{
	/**
	 * The receiver is the object which does the actual work
	 */
	private Fan receiver;

	public FanIncrementRpmCommand(Fan fan)
	{
		this.receiver = fan;
	}

	/**
	 *The implementation of the execute method from the Command interface
	 */
	@Override
	public void execute()
	{
		this.receiver.incrementRpmLevel();
		this.receiver.doOperation();
	}
}
