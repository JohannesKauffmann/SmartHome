package sensors;

import java.util.Random;

import subscribers.Subscriber;

/**
 * 
 * Concrete sensor that sends updates in humidity changes to subscribers.
 *
 */
public class HumiditySensor extends Sensor
{
	private int humidity;

	public HumiditySensor(String name)
	{
		super();
		this.name = name;
	}

	/**
	 * Notify the subscribers as part of the observer pattern.
	 */
	@Override
	public void notifySubscribers()
	{
		for (Subscriber subscriber : this.subscribers)
		{
			subscriber.update(this, this.humidity);
		}
	}

	/**
	 * Generate a humidity measurement.
	 */
	@Override
	public void doMeasurement()
	{
		Random random = new Random();
		this.humidity = random.nextInt(100);
		System.out.println("HumiditySensor: " + this.name + " measured the following value: " + this.humidity);
		// notify subscribers with new value.
		this.notifySubscribers();
	}
}
