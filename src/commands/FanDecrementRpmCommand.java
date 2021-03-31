package commands;

import actuators.Fan;

/**
 * The FanDecrementsRpmCommand is a concrete Command.
 */
public class FanDecrementRpmCommand implements Command
{
	/**
	 * The receiver is the object which does the actual work
	 */
	private Fan receiver;

	public FanDecrementRpmCommand(Fan fan)
	{
		this.receiver = fan;
	}

	/**
	 * The implementation of the execute method from the Command interface
	 */
	@Override
	public void execute()
	{
		this.receiver.decrementRpmLevel();
		this.receiver.doOperation();
	}
}
