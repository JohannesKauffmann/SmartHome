package sensors;

import java.util.Random;

import subscribers.Subscriber;

public class TemperatureSensor extends Sensor
{
	private int temperature;

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
		// notify all subscriber of new value.
		this.notifySubscribers();
	}
}
