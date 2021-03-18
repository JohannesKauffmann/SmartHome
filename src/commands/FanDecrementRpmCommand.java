package commands;

import actuators.Fan;

public class FanDecrementRpmCommand implements Command
{
	private Fan receiver;
	
	public FanDecrementRpmCommand(Fan fan)
	{
		this.receiver = fan;
	}

	@Override
	public void execute()
	{
		this.receiver.decrementRpmLevel();
		this.receiver.doOperation();
	}
}
