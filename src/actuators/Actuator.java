package actuators;

import devices.Device;
import mementos.Memento;

/**
 * The abstract Actuator class describes some methods every actuator has to
 * implement. An actuator is the originator of the memento pattern, and the
 * receiver of the command pattern.
 */
public abstract class Actuator implements Device
{
	protected String name;

	/**
	 * The doOperation method will implement the behavior of the the device. Each
	 * concrete actuator does something else based on it's state.
	 */
	public abstract void doOperation();

	/**
	 * The save method will return a concrete memento with the current state of the
	 * actuator.
	 * 
	 * @return The current state of the actuator in a memento.
	 */
	public abstract Memento save();
}
