package actuators;

import devices.Device;
import mementos.Memento;

public abstract class Actuator implements Device
{
	protected String name;
	public abstract void doOperation();
	public abstract Memento save();
}
