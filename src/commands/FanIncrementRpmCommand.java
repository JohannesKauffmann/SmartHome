package commands;

import actuators.Fan;

public class FanIncrementRpmCommand implements Command
{
	private Fan receiver;

	public FanIncrementRpmCommand(Fan fan)
	{
		this.receiver = fan;
	}

	@Override
	public void execute()
	{
		this.receiver.incrementRpmLevel();
		this.receiver.doOperation();
	}
}
