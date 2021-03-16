package subscribers;

import devices.Device;
import sensors.Sensor;

public class Phone implements Device, Subscriber
{
	private String name;
	private int subcriberValue;

	public Phone(String name)
	{
		this.name = name;
	}

	@Override
	public String getName()
	{
		return this.name;
	}

	@Override
	public void setName(String name)
	{
		this.name = name;
	}

	@Override
	public void update(Sensor publisher, int value)
	{
		this.subcriberValue = value;
		this.displayValue(publisher);
	}

	public void displayValue(Sensor publisher)
	{
		System.out.println("Phone: " + this.name + " got a new value of it's publisher: " + publisher.toString()
				+ "value: " + this.subcriberValue);
	}

}
