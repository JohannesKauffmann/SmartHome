package mementos;

import actuators.Fan;

public class FanMemento implements Memento
{
	private Fan originator;
	private int rpmLevel;

	public FanMemento(Fan originator, int rpmLevel)
	{
		this.originator = originator;
		this.rpmLevel = rpmLevel;
	}

	@Override
	public void restore()
	{
		this.originator.setRpmLevel(this.rpmLevel);
	}
}
