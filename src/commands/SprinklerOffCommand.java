package commands;

import actuators.Sprinkler;

public class SprinklerOffCommand implements Command
{
	private Sprinkler receiver;

	public SprinklerOffCommand(Sprinkler sprinkler)
	{
		this.receiver = sprinkler;
	}

	@Override
	public void execute()
	{
		this.receiver.setSprinklerState(false);
		this.receiver.doOperation();
	}

}
