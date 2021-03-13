package actuators;

import devices.Device;
import mementos.Memento;

public abstract class Actuator implements Device
{
	public abstract void doOperation();
	public abstract Memento save();
}
