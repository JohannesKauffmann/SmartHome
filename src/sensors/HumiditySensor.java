package sensors;

import java.util.Random;

import subscribers.Subscriber;

public class HumiditySensor extends Sensor
{
	private int humidity;
	
	public HumiditySensor(String name)
	{
		super();
		this.name = name;
	}

	@Override
	public void notifySubscribers()
	{
		for (Subscriber subscriber : this.subscribers)
		{
			subscriber.update(this, this.humidity);
		}
	}

	@Override
	public void doMeasurement()
	{
		// TODO: measure interval with timer.
		Random random = new Random();
		this.humidity = random.nextInt(100);
		// notify subscribers with new value.
		this.notifySubscribers();
	}
}
