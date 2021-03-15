package mementos;

import actuators.Airco;
import actuators.AircoModus;

public class AircoMemento implements Memento
{
	private AircoModus state;
	private Airco originator;
	
	public AircoMemento(Airco originator, AircoModus state)
	{
		this.state = state;
		this.originator = originator;
	}

	@Override
	public void restore()
	{
		this.originator.setModus(this.state);
		
	}

}
