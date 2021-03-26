package sensors;

import java.util.Random;

import subscribers.Subscriber;

public class TemperatureSensor extends Sensor
{
	private int temperature;

	public TemperatureSensor(String name)
	{
		super();
		this.name = name;
	}

	@Override
	public void notifySubscribers()
	{
		for (Subscriber subscriber : this.subscribers)
		{
			subscriber.update(this, this.temperature);
		}
	}

	@Override
	public void doMeasurement()
	{
		// TODO: measure interval with timer.
		Random random = new Random();
		this.temperature = random.nextInt(40);
		System.out.println("TemperatureSensor: " + this.name + " measured the following value: " + this.temperature);
		// notify all subscriber of new value.
		this.notifySubscribers();
	}
}
