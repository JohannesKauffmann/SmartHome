package subscribers;

import devices.Device;

public class Phone implements Device, Subscriber
{
	private String name;
	private int subcriberValue;
	
	public Phone(String name) {
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
	public void update(int value)
	{
		this.subcriberValue = value;
		this.displayValue();
	}
	
	public void displayValue() {
		System.out.println("Phone: " + this.name + " got a new value of it's observer: " + this.subcriberValue);
	}
	
	
	

}
