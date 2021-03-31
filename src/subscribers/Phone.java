package subscribers;

import devices.Device;
import sensors.Sensor;

/**
 * 
 * A subscriber of sensors using the observer pattern.
 *
 */
public class Phone implements Device, Subscriber
{
	private String name;
	private int subcriberValue;

	public Phone(String name)
	{
		this.name = name;
	}

	/**
	 * Gets the name of the phone.
	 */
	@Override
	public String getName()
	{
		return this.name;
	}

	/**
	 * Sets the name of the phone.
	 */
	@Override
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * Gets called when a sensor updates as part of the observer pattern.
	 */
	@Override
	public void update(Sensor publisher, int value)
	{
		this.subcriberValue = value;
		this.displayValue(publisher);
	}

	/**
	 * Prints the received updated value of a sensor.
	 * @param publisher
	 */
	public void displayValue(Sensor publisher)
	{
		System.out.println("Phone: " + this.name + " got a new value of it's publisher: " + publisher.toString()
				+ "value: " + this.subcriberValue);
	}

}
