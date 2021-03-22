package commands;

import actuators.Sprinkler;

public class SprinklerOnCommand implements Command
{
	private Sprinkler receiver;
	
	public SprinklerOnCommand(Sprinkler sprinkler)
	{
		this.receiver = sprinkler;
	}

	@Override
	public void execute()
	{
		this.receiver.setSprinklerState(true);
		this.receiver.doOperation();
	}

}