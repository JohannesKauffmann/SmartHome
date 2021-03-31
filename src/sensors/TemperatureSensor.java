package sensors;

import java.util.Random;

import subscribers.Subscriber;

/**
 * 
 * Concrete sensor class extends sensor and updates subscribers on temperature change.
 *
 */
public class TemperatureSensor extends Sensor
{
	private int temperature;

	public TemperatureSensor(String name)
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
			subscriber.update(this, this.temperature);
		}
	}

	/**
	 * Generate a humidity measurement.
	 */
	@Override
	public void doMeasurement()
	{
		Random random = new Random();
		this.temperature = random.nextInt(40);
		// notify all subscriber of new value.
		this.notifySubscribers();
	}
}
