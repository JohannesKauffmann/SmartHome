package mementos;

import actuators.Heater;
import actuators.HeaterModus;

public class HeaterMemento implements Memento
{
	private Heater originator;
	private HeaterModus state;

	public HeaterMemento(Heater originator, HeaterModus state)
	{
		this.originator = originator;
		this.state = state;
	}

	@Override
	public void restore()
	{
		this.originator.setModus(this.state);
	}
}
