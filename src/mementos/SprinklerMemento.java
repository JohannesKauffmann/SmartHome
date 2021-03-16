package mementos;

import actuators.Sprinkler;

public class SprinklerMemento implements Memento
{
	private Sprinkler originator;
	private boolean state;

	public SprinklerMemento(Sprinkler originator, boolean state)
	{
		this.originator = originator;
		this.state = state;
	}

	@Override
	public void restore()
	{
		this.originator.setSprinklerState(state);
	}

}
